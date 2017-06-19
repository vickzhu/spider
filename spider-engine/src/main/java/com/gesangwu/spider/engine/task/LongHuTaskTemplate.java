package com.gesangwu.spider.engine.task;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.SecDept;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.biz.service.LongHuTypeService;
import com.gesangwu.spider.biz.service.SecDeptService;

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

	public void execute(String tradeDate){
		long start = System.currentTimeMillis();
		if(StringUtil.isBlank(tradeDate)){
			Date now = new Date();
			tradeDate = sdf.format(now);
		}
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
		if(lhdList.size() == 0 || lhdList.size() < 5){
			logger.error("Can't find longhu detail for stock:"+longHu.getSymbol());
			return;
		}
		lhService.insert(longHu, lhdList);
		lhService.analyzeClique(longHu);
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