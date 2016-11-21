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

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.spring.ContextHolder;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.DecimalUtil;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.dao.model.SecDept;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.biz.service.LongHuTypeService;
import com.gesangwu.spider.biz.service.SecDeptService;
import com.gesangwu.spider.engine.util.StockTool;
import com.gesangwu.spider.engine.util.TradeTimeUtil;

/**
 * 这里的数据有一个问题，营业部编号和新浪、网易、雪球的对不上
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
public class LongHuTask_eastmoney {
	
	private static final Logger logger = LoggerFactory.getLogger(LongHuTask_eastmoney.class);

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static final String r1 = "\"SCode\"\\:\"([0-9]*)\",\"SName\"\\:\"([^\"]*)\",\"ClosePrice\"\\:\"([0-9\\.]*)\",\"Chgradio\"\\:\"([\\-0-9\\.]*)\",\"Dchratio\"\\:\"([0-9\\.]*)\",\"JmMoney\"\\:\"[\\-0-9\\.]*\",\"Turnover\"\\:\"([\\-0-9\\.]*)\",\"Ntransac\"\\:\"([\\-0-9\\.]*)\",\"Ctypedes\"\\:\"[^\"]*\",\"Oldid\"\\:\"[\\-0-9\\.]*\",\"Smoney\"\\:\"([0-9\\.]*)\",\"Bmoney\"\\:\"([0-9\\.]*)\",\"ZeMoney\"\\:\"[^\"]*\",\"Tdate\"\\:\"([^\"]*)\",\"JmRate\"\\:\"[^\"]*\",\"ZeRate\"\\:\"[^\"]*\",\"Ltsz\"\\:\"([^\"]*)\"";
	private static Pattern p1 = Pattern.compile(r1);
	private static final String SPLITER = SymbolConstant.U_LINE;
	private Map<String,LongHuType> typeMap = new HashMap<String,LongHuType>();
	
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhdService;
	@Resource
	private LongHuTypeService typeService;
	@Resource
	private CliqueStockTask cliqueTask;
	@Resource
	private SecDeptService deptService;
	
	@PostConstruct
	public void init(){
		List<LongHuType> lhtList = typeService.selectByExample(null);
		for (LongHuType type : lhtList) {
			typeMap.put(type.getLhDesc(), type);
		}
	}
	
//	@Scheduled(cron = "0 0/3 16-18 * * MON-FRI")
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
		String code = StockUtil.symbol2Code(longHu.getSymbol());
		String url = buildDetailUrl(code, longHu.getTradeDate());
		String result = HttpTool.get(url);
		List<LongHuType> typeList = buildType(result);
		List<String> yrList = new ArrayList<String>();
		List<String> erList = new ArrayList<String>();
		List<String> srList = new ArrayList<String>();
		String[] strs = result.split("<div class=\"div_remark\" style=\"display\\:table;\"><div style=\"display\\:table-cell;vertical-align\\:middle;\"><span>");
		for(int i = 1;i < strs.length;i++){
			LongHuType lhType = typeList.get(i-1);
			int dateType = lhType.getDateType();
			if(dateType == 1){//一日
				yrList.add(lhType.getLhType());
				if(yrList.size()>1){
					continue;
				}
			} else if(dateType == 3){//三日
				srList.add(lhType.getLhType());
				if(srList.size()>1){
					continue;
				}
			} else if(dateType == 2){//二日
				erList.add(lhType.getLhType());
				if(erList.size()>1){
					continue;
				}
			}
			List<String> deptList = parseDept(strs[i]);
			List<String> amtList = parseAmt(strs[i]);
			Set<String> deptSet = new HashSet<String>();
			BigDecimal buyTotal = BigDecimal.ZERO;
			BigDecimal sellTotal = BigDecimal.ZERO;
			for(int j = 0; j< deptList.size(); j++){
				String deptCode = deptList.get(j);
				if(deptSet.contains(deptCode)){
					continue;
				} else {
					deptSet.add(deptCode);
				}
				LongHuDetail detail = new LongHuDetail();
				String amtStr = amtList.get(j).replaceAll(SymbolConstant.COMMA, StringUtil.EMPTY);
				String[] amtPair = amtStr.split(SPLITER);
				BigDecimal buyAmt = DecimalUtil.parse(amtPair[0]);
				BigDecimal sellAmt = DecimalUtil.parse(amtPair[1]);
				BigDecimal netBuy = buyAmt.subtract(sellAmt);
				detail.setBuyAmt(buyAmt);
				detail.setSellAmt(sellAmt);
				detail.setNetBuy(netBuy);
				detail.setDateType(lhType.getDateType());
				detail.setSymbol(longHu.getSymbol());
				detail.setGmtCreate(new Date());
				detail.setSecDeptCode(deptCode);
				detail.setTradeDate(longHu.getTradeDate());
				lhdList.add(detail);
				buyTotal = buyTotal.add(detail.getBuyAmt());
				sellTotal = sellTotal.add(detail.getSellAmt());
			}
			if(dateType == 1){
				longHu.setYrAmt(buildAmt(buyTotal, sellTotal));
				String typeArr = StringUtil.join(yrList.iterator(), SymbolConstant.COMMA);
				longHu.setYrType(typeArr);
			} else if(dateType == 2){
				longHu.setErAmt(buildAmt(buyTotal, sellTotal));
				String typeArr = StringUtil.join(erList.iterator(), SymbolConstant.COMMA);
				longHu.setErType(typeArr);
			} else if (dateType == 3){
				longHu.setSrAmt(buildAmt(buyTotal, sellTotal));
				String typeArr = StringUtil.join(srList.iterator(), SymbolConstant.COMMA);
				longHu.setSrType(typeArr);
			}
		}
		lhService.insert(longHu, lhdList);
		cliqueTask.calc(longHu);
	}
	
	private static final String r2 = "<a href=\"javascript\\:location.href='/Lhb/JgSearch/([0-9]*)'\">(.*)</a>";
	private static Pattern p2 = Pattern.compile(r2);
	
	private List<String> parseDept(String detail){
		Matcher m2 = p2.matcher(detail);
		List<String> deptCodeList = new ArrayList<String>();
		while(m2.find()){
			deptCodeList.add(m2.group(1));
			souDept(m2.group(1), m2.group(2));
		}
		return deptCodeList;
	}
	
	private static final String r3 = "<tbody id=\"data_list\">([\\s\\S]*)</tbody>";
	private static Pattern p3 = Pattern.compile(r3);
	
	private static final String r4 = "<tr><td class=\"(?:f-red|)\"><span>([0-9,\\.\\-]*)</span></td><td class=\"(?:f-green|)\"><span>([0-9,\\.\\-]*)</span></td>";
	private static Pattern p4 = Pattern.compile(r4);
	
	private List<String> parseAmt(String detail){
		List<String> amtList = new ArrayList<String>();
		Matcher m3 = p3.matcher(detail);
		while(m3.find()){
			String content = m3.group(1);
			content = content.replaceAll("[\\r\\n\\t]|  ", StringUtil.EMPTY);
			Matcher m4 = p4.matcher(content);
			while(m4.find()){
				String buy = m4.group(1);
				if(SymbolConstant.H_LINE.equals(buy)){
					buy = "0";
				}
				String sell = m4.group(2);
				if(SymbolConstant.H_LINE.equals(sell)){
					sell = "0";
				}
				amtList.add(buy+SPLITER+sell);
			}
		}
		return amtList;
	}
	
	private static final String r11 = "<div class=\"div_remark\" style=\"display\\:table;\"><div style=\"display\\:table-cell;vertical-align\\:middle;\"><span>(.*)</span>";
	private static Pattern p11 = Pattern.compile(r11);
	
	private List<LongHuType> buildType(String result){
		List<LongHuType> typeList = new ArrayList<LongHuType>();
		Matcher m1 = p11.matcher(result);
		while(m1.find()){
			String typeDesc = m1.group(1);
			LongHuType lhType = typeMap.get(typeDesc);
			typeList.add(lhType);
			if(lhType == null){//防止有龙虎类型没有入库
				logger.error("Can't find Dept with desc:" + typeDesc);
				continue;
			}
		}
		return typeList;
	}
	
	/**
	 * 更新营业部信息
	 * @param deptCode
	 * @param deptName
	 */
	private void souDept(String deptCode, String deptName){
		SecDeptService deptService = ContextHolder.getContext().getBean(SecDeptService.class);
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
	private String buildDetailUrl(String code, String date){
		StringBuilder sb = new StringBuilder();
		sb.append("http://m.data.eastmoney.com/lhb/StockDetailList/");
		sb.append(code);
		if(StringUtil.isNotBlank(date)){
			sb.append("?date=");
			sb.append(date);
		}
		return sb.toString();
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
}
