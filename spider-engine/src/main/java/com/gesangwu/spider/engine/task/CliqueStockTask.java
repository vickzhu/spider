package com.gesangwu.spider.engine.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.DeptCliqueType;
import com.gesangwu.spider.biz.dao.model.CliqueDept;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.LongHuExample;
import com.gesangwu.spider.biz.service.CliqueDeptService;
import com.gesangwu.spider.biz.service.CliqueService;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;

/**
 * 查找帮派操作的股票
 * <pre>
 * 1、根据帮派成员，查找帮派操作的股票
 * 2、如果查找到帮派操作的股票，判断不属于当前帮派的营业部是否是帮派成员
 * </pre>
 * @author zhuxb
 *
 */
@Component
public class CliqueStockTask {
	
	@Resource
	private CliqueService cliqueService;
	@Resource
	private CliqueDeptService cliqueDeptService;
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhdService;
	
	
	public void execute(){
		List<LongHu> lhList = getLongHuList();
		calc(lhList);
	}
	
	public void calc(LongHu longHu){
		Map<Integer,List<LongHuDetail>> resultMap = getLongHuDetailList(longHu);
		for (Map.Entry<Integer,List<LongHuDetail>> detailEntry : resultMap.entrySet()) {//不同日期类型的龙虎榜详情(一日，二日，三日)
			int dateType = detailEntry.getKey();
			List<LongHuDetail> detailList = detailEntry.getValue();
			Map<Long,List<LongHuDetail>> detailMap = listToCliqueMap(detailList);
			//TODO clique_stock数据没有保存
			long cliqueId = 0;
			int cliqueSize = 0;
			for(Map.Entry<Long,List<LongHuDetail>> entry : detailMap.entrySet()){//遍历所有数据，统计帮派
				int size = entry.getValue().size();
				if(size > cliqueSize){
					cliqueSize = size;
					cliqueId = entry.getKey();
				}
			}
			if(cliqueSize > 3){//已成帮派
				List<LongHuDetail> lhdList = detailMap.get(cliqueId);
				updateCliqueOperate(cliqueId, longHu, lhdList);
				detailList.removeAll(lhdList);
				if(dateType == 1){
					calcOtherDept(cliqueId, detailList);
				}
			} else if(cliqueSize > 0) {//判断其他营业部是否操作过相应的帮派标的，但是还没有加入到该帮派里
				if(dateType == 1){//单日行情才计算其他帮派
					List<LongHuDetail> lhdList = detailMap.get(cliqueId);
					detailList.removeAll(lhdList);
					List<LongHuDetail> tmpList = new ArrayList<LongHuDetail>();
					for (LongHuDetail detail : detailList) {
						String deptCode = detail.getSecDeptCode();
						String tradeDate = detail.getTradeDate();
						String startDate = getStartDate(tradeDate, 3);
						int cliqueCount = lhdService.count4Clique(deptCode, detail.getSymbol(), cliqueId, startDate, tradeDate);
						int upCount = countDept(deptCode, tradeDate, 3);
						if(cliqueCount > 0 && cliqueCount * 2 >= upCount){//跟帮操作次数占总上榜次数50%及以上
							tmpList.add(detail);
						}
					}
					if(cliqueSize + tmpList.size() > 3){//可以判断为一个帮派操作
						lhdList.addAll(tmpList);
						updateCliqueOperate(cliqueId, longHu, lhdList);
						detailList.removeAll(tmpList);
						calcOtherDept(cliqueId, detailList);
					} else {
						longHu.setSecDeptRelation(cliqueSize);
						longHu.setGmtUpdate(new Date());
						lhService.updateByPrimaryKey(longHu);
					}
				} else {
					longHu.setSecDeptRelation(cliqueSize);
					longHu.setGmtUpdate(new Date());
					lhService.updateByPrimaryKey(longHu);
				}
			} else {
				continue;
			}
		}
	}
	
	public void calc(List<LongHu> lhList){
		for (LongHu longHu : lhList) {//每天龙虎榜
			calc(longHu);
		}
	}
	
	private void updateCliqueOperate(long cliqueId, LongHu longHu, List<LongHuDetail> lhdList){
		Date now = new Date();
		longHu.setOperateClique(cliqueId);
		longHu.setSecDeptRelation(lhdList.size());
		longHu.setGmtUpdate(now);
		lhService.updateByPrimaryKey(longHu);
		for (LongHuDetail longHuDetail : lhdList) {
			longHuDetail.setCliqueId(cliqueId);
			longHuDetail.setGmtUpdate(now);
			lhdService.updateByPrimaryKey(longHuDetail);
		}
	}
	
	/**
	 * 根据营业部所属帮派讲详情转换为map，key为帮派，value为详情中该帮派操作过的记录
	 * @param detailList
	 * @return
	 */
	private Map<Long,List<LongHuDetail>> listToCliqueMap(List<LongHuDetail> detailList){
		Map<Long,List<LongHuDetail>> detailMap = new HashMap<Long,List<LongHuDetail>>();
		for (LongHuDetail longHuDetail : detailList) {//每条龙虎详情
			List<CliqueDept> cliqueDeptList = cliqueDeptService.selectByDeptCode(longHuDetail.getSecDeptCode());
			for (CliqueDept cliqueDept : cliqueDeptList) {//每个营业部属于哪些帮派
				List<LongHuDetail> cliqueDetailList = detailMap.get(cliqueDept.getCliqueId());
				if(CollectionUtils.isEmpty(cliqueDetailList)){
					cliqueDetailList = new ArrayList<LongHuDetail>();
					detailMap.put(cliqueDept.getCliqueId(), cliqueDetailList);
				}
				cliqueDetailList.add(longHuDetail);
			}
		}
		return detailMap;
	}
	
	
	/**
	 * 判断其他未加入帮派的营业部是否也是帮派成员
	 * @param cliqueId
	 * @param lhdList
	 */
	public void calcOtherDept(long cliqueId, List<LongHuDetail> lhdList){
		Date now = new Date();
		for (LongHuDetail detail : lhdList) {
			if(!detail.getSecDeptCode().startsWith("8")){//排除机构
				continue;
			}
			String deptCode = detail.getSecDeptCode();
			String tradeDate = detail.getTradeDate();
			List<LongHuDetail> detailList = cliqueOperateList(cliqueId, deptCode, tradeDate, detail.getSymbol());
			if(CollectionUtils.isNotEmpty(detailList)){//之前也操作过这个帮派的股票
				detail.setCliqueId(cliqueId);
				detail.setGmtUpdate(now);
				lhdService.updateByPrimaryKey(detail);
//				for (LongHuDetail detailHistory : detailList) {//把找出来的历史数据也更新为相应帮派
//					detailHistory.setCliqueId(cliqueId);
//					detailHistory.setGmtUpdate(now);
//					lhdService.updateByPrimaryKey(detailHistory);
//				}
				CliqueDept cliqueDept = new CliqueDept();
				cliqueDept.setCliqueId(cliqueId);
				cliqueDept.setDeptType(DeptCliqueType.ASSIST.getCode());//暂定所有筛选出来的营业部都为辅营部
				cliqueDept.setGmtCreate(now);
				cliqueDept.setSecDeptCode(deptCode);
				cliqueDeptService.insert(cliqueDept);
			}
		}
		
	}
	
	/**
	 * 返回指定营业部指定时间内操作过的帮派其他股票
	 * @param cliqueId
	 * @param deptCode
	 * @param tradeDate
	 * @param excludeSymbol	要排除的股票
	 * @return
	 */
	private List<LongHuDetail> cliqueOperateList(long cliqueId, String deptCode, String tradeDate, String excludeSymbol){
		String startDate = getStartDate(tradeDate, 3);
		int cliqueCount = lhdService.count4Clique(deptCode, excludeSymbol, cliqueId, startDate, tradeDate);
		int upCount = countDept(deptCode, tradeDate, 3);
		if(upCount > 60){//三个月内上榜次数频繁，为一线游资或敢死队
			return null;
		} else if(upCount > 15) {
			int least = upCount / 5;
			if(cliqueCount < least) {//三个月之内操作帮派股票过少
				return null;
			}
		} else {
			if(cliqueCount < 2){
				return null;
			}
		}
		return lhdService.selectDetail(deptCode, cliqueId, startDate, tradeDate);
	}
	
	/**
	 * 查找指定时间段内上榜次数
	 * @param deptCode
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private int countDept(String deptCode, String tradeDate, int gap){
		String startDate = getStartDate(tradeDate, gap);
		LongHuDetailExample example = new LongHuDetailExample();
		LongHuDetailExample.Criteria criteria = example.createCriteria();
		criteria.andSecDeptCodeEqualTo(deptCode);
		criteria.andTradeDateGreaterThan(startDate);
		criteria.andTradeDateLessThan(tradeDate);
		return lhdService.countByExample(example);
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private static String getStartDate(String tradeDate, int gap){
		try {
			Date date = sdf.parse(tradeDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) - gap);
			return sdf.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<LongHu> getLongHuList(){
		LongHuExample lhExample = new LongHuExample();
		lhExample.setOrderByClause("trade_date asc");
		LongHuExample.Criteria criteria = lhExample.createCriteria();
		criteria.andTradeDateGreaterThan("2016-06-01");
		criteria.andOperateCliqueIsNull();
		return lhService.selectByExample(lhExample);
	}
	
	/**
	 * 返回一日，二日，三日龙虎榜
	 * @param longHu
	 * @return
	 */
	private Map<Integer,List<LongHuDetail>> getLongHuDetailList(LongHu longHu){
		Map<Integer,List<LongHuDetail>> resultMap = new HashMap<Integer, List<LongHuDetail>>();
		String symbol = longHu.getSymbol();
		String tradeDate = longHu.getTradeDate();
		if(StringUtil.isNotBlank(longHu.getYrType())){
			resultMap.put(1, getLongHuDetailList(symbol, tradeDate, 1));
		}
		if(StringUtil.isNotBlank(longHu.getErType())){
			resultMap.put(2, getLongHuDetailList(symbol, tradeDate, 2));
		}
		if(StringUtil.isNotBlank(longHu.getSrType())){
			resultMap.put(3, getLongHuDetailList(symbol, tradeDate, 3));
		}
		return resultMap;
	}
	
	private List<LongHuDetail> getLongHuDetailList(String symbol, String tradeDate, int dateType){
		LongHuDetailExample lhdExample = new LongHuDetailExample();
		LongHuDetailExample.Criteria criteria = lhdExample.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateEqualTo(tradeDate);
		criteria.andDateTypeEqualTo(dateType);
		return lhdService.selectByExample(lhdExample);
	}

}
