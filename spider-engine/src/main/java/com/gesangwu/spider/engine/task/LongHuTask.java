package com.gesangwu.spider.engine.task;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.biz.service.LongHuTypeService;
import com.gesangwu.spider.biz.service.SecDeptService;
import com.gesangwu.spider.engine.util.TradeTimeUtil;
import com.gesangwu.spider.engine.util.WangYiLongHuTool;

/**
 * 1、从东方财富获得龙虎列表，但东方财富无龙虎类型
 * 2、从网易获取所有龙虎类型
 * 3、根据从东财获得的列表及从网易获取的龙虎类型，抓取龙虎详情
 * <ul>
 * 	<li>东方财富</li>
 *  <li>http://data.eastmoney.com/stock/tradedetail.html</li>
 * </ul>
 * <ul>
 * 	<li>网易</li>
 * 	<li>http://quotes.money.163.com/old/#query=MRLHB&DataType=lhb&sort=TDATE&order=desc&count=150&page=0&$3xa05</li>
 * </ul>
 * @author zhuxb
 *
 */
@Component
public class LongHuTask {
	
	private static final Logger logger = LoggerFactory.getLogger(LongHuTask.class);

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhdService;
	@Resource
	private LongHuTypeService typeService;
	@Resource
	private SecDeptService deptService;
	
	@Scheduled(cron = "0 0/3 16-17 * * MON-FRI")
	public void execute(){
		if(!TradeTimeUtil.checkLongHuTime()){
			return;
		}
		Date now= new Date();
		String tradeDate = sdf.format(now);
		execute(tradeDate);
	}
	/**
	 * gpfw:0-全部，1-上证，2-深证
	 */
	public void execute(String tradeDate){
		long start = System.currentTimeMillis();
		if(StringUtil.isBlank(tradeDate)){
			Date now = new Date();
			tradeDate = sdf.format(now);
		}
		String url = buildUrl(tradeDate, 0);
		String result = HttpTool.get(url, Charset.forName("GB2312"));
		parse(result, tradeDate);
		long end = System.currentTimeMillis();
		logger.info("Fetch LongHu used:" + (end-start) + "ms!");
	}
	
	private String buildUrl(String date, int bazaar){
		StringBuilder sb = new StringBuilder();
		sb.append("http://data.eastmoney.com/DataCenter_V3/stock2016/TradeDetail/pagesize=200,page=1,sortRule=-1,sortType=,startDate=");
		sb.append(date);
		sb.append(",endDate=");
		sb.append(date);
		sb.append(",gpfw=");
		sb.append(bazaar);
		sb.append(",js=.html");
		return sb.toString();
	}
	
	private void parse(String content, String tradeDate){
		Set<String> lhSet = completedLongHuMap(tradeDate);
		List<LongHu> longHuList = WangYiLongHuTool.getLongHuList(tradeDate);
		for (LongHu longHu : longHuList) {
			if(lhSet.contains(longHu.getSymbol())){
				continue;
			}
			fetchDetail(longHu);
		}
	}
	
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
			lhdList.addAll(fetchDetail(1, longHu.getYrType(), longHu));
		}
		if(StringUtil.isNotBlank(longHu.getErType())){
			lhdList.addAll(fetchDetail(2, longHu.getErType(), longHu));
		}
		if(StringUtil.isNotBlank(longHu.getSrType())){
			lhdList.addAll(fetchDetail(3, longHu.getSrType(), longHu));
		}
		if(lhdList.size() == 0){
			logger.error("Can't find longhu detail for stock:"+longHu.getSymbol());
			return;
		}
		lhService.insert(longHu, lhdList);
		lhService.analyzeClique(longHu);
	}
	
	public List<LongHuDetail> fetchDetail(int dateType, String lhType, LongHu longHu){
		Date now = new Date();
		int index = lhType.indexOf(SymbolConstant.COMMA);
		if(index > 0){
			lhType = lhType.substring(0, index);
		}
		String code = StockUtil.symbol2Code(longHu.getSymbol());
		String tradeDate = longHu.getTradeDate();
		List<LongHuDetail> detailList = WangYiLongHuTool.fetchDetail(code, tradeDate, lhType);
		BigDecimal buyTotal = BigDecimal.ZERO;
		BigDecimal sellTotal = BigDecimal.ZERO;
		Map<String,LongHuDetail> detailMap = new HashMap<String,LongHuDetail>();
		for (LongHuDetail detail : detailList) {
			LongHuDetail lhd = detailMap.get(detail.getSecDeptCode());
			if(lhd != null){//已经存在了
				if(detail.getSellAmt().compareTo(lhd.getSellAmt()) > 0){
					lhd.setSellAmt(detail.getSellAmt());
					BigDecimal netBuy = lhd.getBuyAmt().subtract(lhd.getSellAmt());
					lhd.setNetBuy(netBuy);
					sellTotal = sellTotal.add(lhd.getSellAmt());
				}
				continue;
			} else {
				detailMap.put(detail.getSecDeptCode(), detail);
			}
			detail.setLongHuId(longHu.getId());
			detail.setDateType(dateType);
			detail.setGmtCreate(now);
			detail.setSymbol(StockUtil.code2Symbol(code));
			detail.setTradeDate(tradeDate);
			buyTotal = buyTotal.add(detail.getBuyAmt());
			sellTotal = sellTotal.add(detail.getSellAmt());
		}
		if(dateType == 1){
			longHu.setYrAmt(buildAmt(buyTotal, sellTotal));
		} else if(dateType == 2){
			longHu.setErAmt(buildAmt(buyTotal, sellTotal));
		} else if (dateType == 3){
			longHu.setSrAmt(buildAmt(buyTotal, sellTotal));
		}
		List<LongHuDetail> lhdList = new ArrayList<LongHuDetail>();
		for (LongHuDetail detail : detailMap.values()) {
			lhdList.add(detail);
		}
		return lhdList;
	}
	
	
	private String buildAmt(BigDecimal buyTotal, BigDecimal sellTotal){
		BigDecimal netBuy = buyTotal.subtract(sellTotal);
		StringBuilder sb = new StringBuilder();
		sb.append(buyTotal.toString());
		sb.append(SymbolConstant.COMMA);
		sb.append(sellTotal.toString());
		sb.append(SymbolConstant.COMMA);
		sb.append(netBuy.toString());
		return sb.toString();
	}
	
	public static void main(String[] args){
		LongHuTask task = new LongHuTask();
		task.execute("2016-10-11");
	}
}
