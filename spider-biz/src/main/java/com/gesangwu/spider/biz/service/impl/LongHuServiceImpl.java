package com.gesangwu.spider.biz.service.impl;

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
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gandalf.framework.util.CalculateUtil;
import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.common.DeptCliqueType;
import com.gesangwu.spider.biz.dao.mapper.LongHuMapper;
import com.gesangwu.spider.biz.dao.model.CliqueDept;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.LongHuExample;
import com.gesangwu.spider.biz.service.CliqueDeptService;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.biz.service.SynergyDetailService;

@Service
public class LongHuServiceImpl extends BaseServiceImpl<LongHu, LongHuExample>
		implements LongHuService {
	
	@Resource
	private LongHuMapper mapper;
	@Resource
	private LongHuDetailService detailService;
	@Resource
	private CliqueDeptService cdService;
	@Resource
	private SynergyDetailService sdService;

	@Override
	protected BaseMapper<LongHu, LongHuExample> getMapper() {
		return mapper;
	}

	@Override
	public List<LongHu> selectByTradeDate(String tradeDate) {
		return mapper.selectByTradeDate(tradeDate);
	}

	@Override
	public LongHu selectBySymbolAndTradeDate(String symbol, String tradeDate) {
		LongHuExample example = new LongHuExample();
		LongHuExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateEqualTo(tradeDate);
		List<LongHu> longHuList = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(longHuList)?null:longHuList.get(0);
	}

	@Override
	public List<String> selectTradeDate(String symbol) {
		return mapper.selectTradeDate(symbol);
	}

	@Override
	public LongHu selectLatestBySymbol(String symbol) {
		LongHuExample example = new LongHuExample();
		example.setOffset(0);
		example.setRows(1);
		example.setOrderByClause("trade_date desc");
		LongHuExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		List<LongHu> longHuList = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(longHuList) ? null : longHuList.get(0);
	}

	@Override
	public void insertBatch(List<LongHu> longHuList) {
		mapper.insertBatch(longHuList);
	}

	@Override
	public void insert(LongHu longHu, List<LongHuDetail> detailList) {
		mapper.insert(longHu);
		for (LongHuDetail detail : detailList) {
			detail.setLongHuId(longHu.getId());
		}
		detailService.batchInsert(detailList);
	}

	@Override
	public void analyzeClique(LongHu longhu) {
		longhu.setOperateClique(null);
		Map<Integer,List<LongHuDetail>> resultMap = getLongHuDetailList(longhu);
		for (Map.Entry<Integer,List<LongHuDetail>> detailEntry : resultMap.entrySet()) {//不同日期类型的龙虎榜详情(一日，二日，三日)
			int dateType = detailEntry.getKey();
			List<LongHuDetail> detailList = detailEntry.getValue();
			if(isOrg(detailList)){//机构
				longhu.setOperateClique(1000l);
			}
			Map<Long,List<LongHuDetail>> detailMap = listToCliqueMap(detailList);
			
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
				updateCliqueOperate(dateType, cliqueId, longhu, lhdList);
				detailList.removeAll(lhdList);
				if(dateType == 1){
					calcOtherDept(cliqueId, detailList);
				}
			} else if(cliqueSize > 0) {//判断其他营业部是否操作过相应的帮派标的，但是还没有加入到该帮派里
				if(dateType == 1){//单日行情才计算其他帮派
					List<LongHuDetail> lhdList = detailMap.get(cliqueId);
					detailList.removeAll(lhdList);
					//注意，这里虽然通过计算将营业部本次操作归结为某个帮派，但是并没有将该营业部加入到相应帮派中
					List<LongHuDetail> tmpList = new ArrayList<LongHuDetail>();
					for (LongHuDetail detail : detailList) {
						String deptCode = detail.getSecDeptCode();
						String tradeDate = detail.getTradeDate();
						String startDate = getStartDate(tradeDate, 3);
						int cliqueCount = detailService.count4Clique(deptCode, detail.getSymbol(), cliqueId, startDate, tradeDate);
						int upCount = countDept(deptCode, tradeDate, 3);
						if(upCount > 4 && cliqueCount > 1 && cliqueCount * 2 >= upCount){//跟帮操作次数占总上榜次数50%及以上
							tmpList.add(detail);
						}
					}
					if(cliqueSize + tmpList.size() > 3){//可以判断为一个帮派操作
						lhdList.addAll(tmpList);
						updateCliqueOperate(dateType, cliqueId, longhu, lhdList);
						detailList.removeAll(tmpList);
						calcOtherDept(cliqueId, detailList);
					} else {
						longhu.setSecDeptRelation(cliqueSize);
						longhu.setGmtUpdate(new Date());
						updateByPrimaryKey(longhu);
					}
				} else {
					longhu.setSecDeptRelation(cliqueSize);
					longhu.setGmtUpdate(new Date());
					updateByPrimaryKey(longhu);
				}
			} else {
				mapper.updateByPrimaryKey(longhu);//在更新的情况下，可能以前有值
			}
			
		}
	}
	
	/**
	 * 判断是否为机构
	 * @param lhdList
	 * @return
	 */
	public boolean isOrg(List<LongHuDetail> lhdList){
		double total = 0;
		double orgTotal = 0;
		for (LongHuDetail lhd : lhdList) {
			total = CalculateUtil.add(total, lhd.getBuyAmt().doubleValue());
			long secDeptCode = Long.valueOf(lhd.getSecDeptCode());
			if(secDeptCode > 20000000 || secDeptCode < 19000000){
				continue;
			}
			if(lhd.getBuyAmt().doubleValue() < 100){//小于100万的不予考虑
				continue;
			}
			if("80603180".equals(secDeptCode) || "80508836".equals(secDeptCode)){//沪、深股通专用
				continue;
			}
			orgTotal = CalculateUtil.add(orgTotal, lhd.getBuyAmt().doubleValue());
		}
		return CalculateUtil.mul(orgTotal, 5) > CalculateUtil.mul(total, 2);
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
		return detailService.selectByExample(lhdExample);
	}
	
	/**
	 * 根据营业部所属帮派讲详情转换为map，key为帮派，value为详情中该帮派操作过的记录
	 * @param detailList
	 * @return
	 */
	private Map<Long,List<LongHuDetail>> listToCliqueMap(List<LongHuDetail> detailList){
		Map<Long,List<LongHuDetail>> detailMap = new HashMap<Long,List<LongHuDetail>>();
		for (LongHuDetail longHuDetail : detailList) {//每条龙虎详情
			List<CliqueDept> cliqueDeptList = cdService.selectByDeptCode(longHuDetail.getSecDeptCode());
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
	
	private void updateCliqueOperate(int dateType, long cliqueId, LongHu longHu, List<LongHuDetail> lhdList){
		Date now = new Date();
		if(longHu.getOperateClique() == null || dateType == 1){			
			longHu.setOperateClique(cliqueId);
		}
		if(longHu.getSecDeptRelation() == null || lhdList.size() > longHu.getSecDeptRelation()){
			longHu.setSecDeptRelation(lhdList.size());
		}
		longHu.setGmtUpdate(now);
		updateByPrimaryKey(longHu);
		for (LongHuDetail longHuDetail : lhdList) {
			longHuDetail.setCliqueId(cliqueId);
			longHuDetail.setGmtUpdate(now);
			detailService.updateByPrimaryKey(longHuDetail);
		}
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
				detailService.updateByPrimaryKey(detail);
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
				cdService.insert(cliqueDept);
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
		int cliqueCount = detailService.count4Clique(deptCode, excludeSymbol, cliqueId, startDate, tradeDate);
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
		return detailService.selectDetail(deptCode, cliqueId, startDate, tradeDate);
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
		return detailService.countByExample(example);
	}

	@Override
	public void selectByPagination(LongHuExample example, Page<LongHu> page) {
		example.setOffset(page.getOffset());
        example.setRows(page.getPageSize());
        int totalCounts = mapper.countByExample(example);
        page.setTotalCounts(totalCounts);
        List<LongHu> companyList = mapper.selectByExample(example);
        page.setRecords(companyList);
	}

}
