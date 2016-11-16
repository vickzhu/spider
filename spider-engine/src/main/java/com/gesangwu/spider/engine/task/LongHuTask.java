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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.DecimalUtil;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.biz.service.LongHuTypeService;
import com.gesangwu.spider.biz.service.SecDeptService;
import com.gesangwu.spider.engine.util.StockTool;
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
	private static final String r1 = "\"SCode\"\\:\"([0-9]*)\",\"SName\"\\:\"([^\"]*)\",\"ClosePrice\"\\:\"([0-9\\.]*)\",\"Chgradio\"\\:\"([\\-0-9\\.]*)\",\"Dchratio\"\\:\"([0-9\\.]*)\",\"JmMoney\"\\:\"[\\-0-9\\.]*\",\"Turnover\"\\:\"([\\-0-9\\.]*)\",\"Ntransac\"\\:\"([\\-0-9\\.]*)\",\"Ctypedes\"\\:\"[^\"]*\",\"Oldid\"\\:\"[\\-0-9\\.]*\",\"Smoney\"\\:\"([0-9\\.]*)\",\"Bmoney\"\\:\"([0-9\\.]*)\",\"ZeMoney\"\\:\"[^\"]*\",\"Tdate\"\\:\"([^\"]*)\",\"JmRate\"\\:\"[^\"]*\",\"ZeRate\"\\:\"[^\"]*\",\"Ltsz\"\\:\"([^\"]*)\"";
	private static Pattern p1 = Pattern.compile(r1);
	
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
		Matcher m =  p1.matcher(content);
		Date now = new Date();
		List<LongHu> longHuList = new ArrayList<LongHu>();
		Set<String> lhCodeSet = new HashSet<String>();
		Map<String,List<String>> typeMap = WangYiLongHuTool.getLongHuType(tradeDate);
		if(typeMap == null || typeMap.size() == 0){
			logger.error("No type Map fund from 163!");
			return;
		}
		while(m.find()){
			String code = m.group(1);
			String symbol = StockTool.codeToSymbol(code);
			if(lhSet.contains(symbol)){
				continue;
			}
			String stockName = m.group(2);
			String price = m.group(3);
			String chg = m.group(4);
			String turnover = m.group(5);
			if(lhCodeSet.contains(code)){//已经存在了
				continue;
			}else {
				lhCodeSet.add(code);
			}
			LongHu longHu = new LongHu();
			longHu.setSymbol(symbol);
			longHu.setStockName(stockName);
			longHu.setPrice(Double.valueOf(price));
			longHu.setChgPercent(DecimalUtil.parse(chg).doubleValue());
			if(StringUtil.isNotBlank(turnover)){
				longHu.setTurnover(DecimalUtil.parse(turnover).doubleValue());
			}
			longHu.setTradeDate(tradeDate);
			longHu.setGmtCreate(now);
			List<String> typeList = typeMap.get(code);
			if(CollectionUtils.isEmpty(typeList)){
				continue;
			}
			List<String> yrList = new ArrayList<String>();
			List<String> erList = new ArrayList<String>();
			List<String> srList = new ArrayList<String>();
			buildLhType(yrList, erList, srList, typeList);
			if(yrList.size() > 0){
				String typeArr = StringUtil.join(yrList.iterator(), SymbolConstant.COMMA);
				longHu.setYrType(typeArr);
			}
			if(erList.size() > 0){
				String typeArr = StringUtil.join(erList.iterator(), SymbolConstant.COMMA);
				longHu.setErType(typeArr);		
			}
			if(srList.size() > 0){
				String typeArr = StringUtil.join(srList.iterator(), SymbolConstant.COMMA);
				longHu.setSrType(typeArr);
			}
			longHuList.add(longHu);
		}
		for (LongHu longHu : longHuList) {
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
	
	private void buildLhType(List<String> yrList, List<String> erList, List<String> srList, List<String> typeList){
		for (String type : typeList) {
			LongHuType lhType = typeService.selectByType(type);
			if(lhType.getDateType() == 1){//一日
				yrList.add(type);
			} else if(lhType.getDateType() == 3){//三日
				srList.add(type);
			} else if(lhType.getDateType() == 2){//二日
				erList.add(type);
			}
		}
	}
	
	public static void main(String[] args){
		LongHuTask task = new LongHuTask();
		task.execute("2016-10-11");
	}
}
