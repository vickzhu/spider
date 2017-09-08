package com.gesangwu.spider.engine.task;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.mybatis.KeyValue;
import com.gandalf.framework.util.CalculateUtil;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.dao.model.ActiveDeptOperation;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.SecDept;
import com.gesangwu.spider.biz.dao.model.SecDeptExample;
import com.gesangwu.spider.biz.service.ActiveDeptOperationService;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.biz.service.LongHuTypeService;
import com.gesangwu.spider.biz.service.SecDeptService;
import com.gesangwu.spider.biz.service.SynergyDetailService;

public abstract class LongHuTaskTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(LongHuTaskTemplate.class);

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhdService;
	@Resource
	private LongHuTypeService typeService;
	@Resource
	private SecDeptService deptService;
	@Resource
	private SynergyDetailService sdService;
	@Resource
	private ActiveDeptOperationService adoService;
	
	private Map<String, String> seatMap = null;

	public void execute(String tradeDate){
		long start = System.currentTimeMillis();
		if(StringUtil.isBlank(tradeDate)){
			Date now = new Date();
			tradeDate = sdf.format(now);
		}
		cacheMainForce();
		Set<String> lhSet = completedLongHuMap(tradeDate);
		List<LongHu> longHuList = getLongHuList(tradeDate);
		for (LongHu longHu : longHuList) {
			if(lhSet.contains(longHu.getSymbol())){
				continue;
			}
			fetchDetail(longHu);
		}
		long end = System.currentTimeMillis();
		logger.info("Fetch LongHu used:" + (end-start) + "ms!");
	}
	
	private void staticAdo(String tradeDate){
		ActiveDeptOperation ado = adoService.selectByTradeDate(tradeDate);
		if(ado == null){
			ado = new ActiveDeptOperation();
			ado.setGmtCreate(new Date());
		}
		List<LongHuDetail> lhdList = lhdService.selectByActiveDept(tradeDate);
		BigDecimal totalBuyAmount = new BigDecimal(0);
		BigDecimal totalSellAmount = new BigDecimal(0);
		HashSet<String> buySymbolSet = new HashSet<String>();
		HashSet<String> sellSymbolSet = new HashSet<String>();
		HashSet<String> buyCodeSet = new HashSet<String>();
		HashSet<String> sellCodeSet = new HashSet<String>();
		for (LongHuDetail lhd : lhdList) {
			if(lhd.getBuyAmt().doubleValue() > 100){
				totalBuyAmount = totalBuyAmount.add(lhd.getBuyAmt());
				buySymbolSet.add(lhd.getSymbol());
				buyCodeSet.add(lhd.getSecDeptCode());
			}
			if(lhd.getSellAmt().doubleValue() > 100){
				totalSellAmount = totalSellAmount.add(lhd.getSellAmt());
				sellSymbolSet.add(lhd.getSymbol());
				sellCodeSet.add(lhd.getSecDeptCode());
			}
		}
		double tba = totalBuyAmount.setScale(2).doubleValue();
		double tsa = totalSellAmount.setScale(2).doubleValue();
		double net = CalculateUtil.sub(tba, tsa, 2);
		int totalBuyStock = buySymbolSet.size();
		int totalSellStock = sellSymbolSet.size();
		int buyDept = buyCodeSet.size();
		int sellDept = sellCodeSet.size();
	}

	/**
	 * 判断主力
	 * @param lhdList
	 * @return
	 */
	private void cacheMainForce(){
		seatMap = new HashMap<String, String>();
		SecDeptExample example = new SecDeptExample();
		SecDeptExample.Criteria criteria = example.createCriteria();
		criteria.andDeptAddrShortIsNotNull();
		List<SecDept> list = deptService.selectByExample(example);
		for (SecDept secDept : list) {
			seatMap.put(secDept.getCode(), secDept.getDeptAddrShort());
		}	
	}
	
	private String judgeMainForce(List<LongHuDetail> lhdList){
		Set<String> set = new HashSet<String>();
		for (LongHuDetail lhd : lhdList) {
			String deptCode = lhd.getSecDeptCode();
			String mainForce = seatMap.get(deptCode);
			if(StringUtil.isNotBlank(mainForce)){
				set.add(mainForce);
			}
		}
		return StringUtil.join(set, SymbolConstant.COMMA);
	}
	
	protected abstract List<LongHu> getLongHuList(String tradeDate);
	
	public abstract List<LongHuDetail> getLongHuDetailList(int dateType, String lhType, LongHu longHu);
	
	/**
	 * 已完成的龙虎榜
	 * @return
	 */
	private Set<String> completedLongHuMap(String tradeDate){
		Set<String> lhSet = new HashSet<String>();
		List<LongHu> lhList = lhService.selectByTradeDate(tradeDate);
		for (LongHu longHu : lhList) {
			lhSet.add(longHu.getSymbol());
		}
		return lhSet;
	}
	
	public void fetchDetail(LongHu longHu){
		List<LongHuDetail> lhdList = new ArrayList<LongHuDetail>();
		if(StringUtil.isNotBlank(longHu.getYrType())){
			lhdList.addAll(getLongHuDetailList(1, longHu.getYrType(), longHu));
		}
		if(StringUtil.isNotBlank(longHu.getErType())){
			lhdList.addAll(getLongHuDetailList(2, longHu.getErType(), longHu));
		}
		if(StringUtil.isNotBlank(longHu.getSrType())){
			lhdList.addAll(getLongHuDetailList(3, longHu.getSrType(), longHu));
		}
		if(lhdList.size() == 0 || lhdList.size() < 4){
			logger.error("Can't find longhu detail for stock:"+longHu.getSymbol());
			return;
		}
		String mainForce = judgeMainForce(lhdList);
		if(StringUtil.isNotBlank(mainForce)){
			longHu.setMainForce(mainForce);
		}
//		synergyDept(longHu.getTradeDate(), lhdList);
		lhService.insert(longHu, lhdList);
		lhService.analyzeClique(longHu);
	}
	
	public void synergyDept(String tradeDate, List<LongHuDetail> lhdList){
		List<String> depts = new ArrayList<String>();
		for (LongHuDetail longHuDetail : lhdList) {
			depts.add(longHuDetail.getSecDeptCode());
		}
		List<KeyValue<Integer, Integer>> list = sdService.relateDept(depts);
		for(KeyValue<Integer, Integer> kv: list){
			System.out.println(kv.getKey() + ":" + kv.getValue());
			String beginDate = getBeginDate(tradeDate);
			List<KeyValue<String, String>> tmpList = lhdService.selectRelationStock(beginDate, tradeDate, kv.getKey());
			for (KeyValue<String, String> tmpKv : tmpList) {
				System.out.println(tmpKv.getKey()+"---"+tmpKv.getValue());
			}
			System.out.println("***************");
		}
	}
	
	private String getBeginDate(String tradeDate){
		Calendar c = Calendar.getInstance();
		try {
			Date date = sdf.parse(tradeDate);
			c.setTime(date);
			c.set(Calendar.MONTH, c.get(Calendar.MONTH) - 3);
		} catch (ParseException e) {
			logger.error("Parse date failed when synergy dept!");
		}
		return sdf.format(c.getTime());
	}
	
	protected String buildAmt(BigDecimal buyTotal, BigDecimal sellTotal){
		BigDecimal netBuy = buyTotal.subtract(sellTotal);
		StringBuilder sb = new StringBuilder();
		sb.append(buyTotal.toString());
		sb.append(SymbolConstant.COMMA);
		sb.append(sellTotal.toString());
		sb.append(SymbolConstant.COMMA);
		sb.append(netBuy.toString());
		return sb.toString();
	}
	
	/**
	 * 保存或更新营业部信息
	 * @param secCode
	 * @param secName
	 */
	protected void souDept(String deptCode, String deptName){
		SecDept dept = deptService.selectByCode(deptCode);
		if(dept == null){
			dept = new SecDept();
			dept.setCode(deptCode);
			dept.setDeptAddr(deptName);
			dept.setGmtCreate(new Date());
			deptService.insert(dept);
		} else {
			dept.setDeptAddr(deptName);
			dept.setGmtUpdate(new Date());
			deptService.updateByPrimaryKey(dept);
		}
	}
	
}
