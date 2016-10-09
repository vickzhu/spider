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
		for (LongHu longHu : lhList) {//每天龙虎榜
			Date now = new Date();
			List<List<LongHuDetail>> resultList = getLongHuDetailList(longHu);
			for (List<LongHuDetail> detailList : resultList) {
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
				//TODO clique_stock数据没有保存
				for(Map.Entry<Long,List<LongHuDetail>> entry : detailMap.entrySet()){//遍历所有数据，统计帮派
					if(entry.getValue().size() > 2){//同一帮派大于两个营业部介入才算
						//XXX 有可能两个帮派同时大于3个营业部介入，先不管这种情况
						long cliqueId = entry.getKey();
						List<LongHuDetail> lhdList = entry.getValue();
						longHu.setCliqueId(cliqueId);
						longHu.setSecDeptRelation(lhdList.size());
						longHu.setGmtUpdate(now);
						lhService.updateByPrimaryKey(longHu);
						for (LongHuDetail longHuDetail : lhdList) {
							longHuDetail.setCliqueId(cliqueId);
							longHuDetail.setGmtUpdate(now);
							lhdService.updateByPrimaryKey(longHuDetail);
						}
						detailList.removeAll(lhdList);
						calcOtherDept(cliqueId, detailList);
						break;
					}
				}
			}
		}
	}
	
	
	/**
	 * 判断其他未加入帮派的营业部是否也是帮派成员
	 * @param cliqueId
	 * @param lhdList
	 */
	private void calcOtherDept(long cliqueId, List<LongHuDetail> lhdList){
		Date now = new Date();
		for (LongHuDetail longHuDetail : lhdList) {
			if(!longHuDetail.getSecDeptCode().startsWith("8")){
				continue;
			}
			String deptCode = longHuDetail.getSecDeptCode();
			String tradeDate = longHuDetail.getTradeDate();
			String startDate = getStartDate(tradeDate);
			List<LongHuDetail> detailList = lhdService.selectDetail(deptCode, cliqueId, startDate, tradeDate);
			if(CollectionUtils.isNotEmpty(detailList)){//之前也操作过这个帮派的股票
				longHuDetail.setCliqueId(cliqueId);
				longHuDetail.setGmtUpdate(now);
				lhdService.updateByPrimaryKey(longHuDetail);
				for (LongHuDetail detailHistory : detailList) {
					detailHistory.setCliqueId(cliqueId);
					detailHistory.setGmtUpdate(now);
					lhdService.updateByPrimaryKey(detailHistory);
				}
				CliqueDept cliqueDept = new CliqueDept();
				cliqueDept.setCliqueId(cliqueId);
				cliqueDept.setDeptType(2);
				cliqueDept.setGmtCreate(now);
				cliqueDept.setSecDeptCode(deptCode);
//				cliqueDept.setSecDeptName("");
				cliqueDeptService.insert(cliqueDept);
			}
		}
		
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private static String getStartDate(String tradeDate){
		try {
			Date date = sdf.parse(tradeDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.MONTH, c.get(Calendar.MONTH)-3);
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
		criteria.andTradeDateGreaterThan("2015-03-01");
		criteria.andCliqueIdIsNull();
		return lhService.selectByExample(lhExample);
	}
	
	/**
	 * 返回一日，二日，三日龙虎榜
	 * @param longHu
	 * @return
	 */
	private List<List<LongHuDetail>> getLongHuDetailList(LongHu longHu){
		List<List<LongHuDetail>> resultList = new ArrayList<List<LongHuDetail>>();
		String symbol = longHu.getSymbol();
		String tradeDate = longHu.getTradeDate();
		resultList.add(getLongHuDetailList(symbol, tradeDate, 1));
		if(StringUtil.isNotBlank(longHu.getErType())){
			resultList.add(getLongHuDetailList(symbol, tradeDate, 2));
		}
		if(StringUtil.isNotBlank(longHu.getSrType())){
			resultList.add(getLongHuDetailList(symbol, tradeDate, 3));
		}
		return resultList;
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
