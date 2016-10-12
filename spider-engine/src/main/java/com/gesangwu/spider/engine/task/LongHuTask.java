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
import com.gesangwu.spider.engine.util.StockTool;
import com.gesangwu.spider.engine.util.XinLangLongHuTool;

/**
 * <ul>
 * 	<li>东方财富</li>
 *  <li>http://data.eastmoney.com/stock/tradedetail.html</li>
 * </ul>
 * <ul>
 * 	<li>新浪</li>
 * 	<li>http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/lhb/index.phtml</li>
 * </ul>
 * @author zhuxb
 *
 */
@Component
public class LongHuTask {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static final String r1 = "\"SCode\"\\:\"([0-9]*)\",\"SName\"\\:\"([^\"]*)\",\"ClosePrice\"\\:\"([0-9\\.]*)\",\"Chgradio\"\\:\"([\\-0-9\\.]*)\",\"Dchratio\"\\:\"([0-9\\.]*)\",\"JmMoney\"\\:\"[\\-0-9\\.]*\",\"Turnover\"\\:\"([\\-0-9\\.]*)\",\"Ntransac\"\\:\"([\\-0-9\\.]*)\",\"Ctypedes\"\\:\"[^\"]*\",\"Oldid\"\\:\"[\\-0-9\\.]*\",\"Smoney\"\\:\"([0-9\\.]*)\",\"Bmoney\"\\:\"([0-9\\.]*)\",\"ZeMoney\"\\:\"[^\"]*\",\"Tdate\"\\:\"([^\"]*)\",\"JmRate\"\\:\"[^\"]*\",\"ZeRate\"\\:\"[^\"]*\",\"Ltsz\"\\:\"([^\"]*)\"";
	private static Pattern p1 = Pattern.compile(r1);
	
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhdService;
	@Resource
	private LongHuTypeService typeService;
	
	/**
	 * gpfw:0-全部，1-上证，2-深证
	 */
	public void execute(String tradeDate){
		if(StringUtil.isBlank(tradeDate)){
			Date now = new Date();
			tradeDate = sdf.format(now);
		}
		String url = buildUrl(tradeDate, 0);
		String result = HttpTool.get(url);
		parse(result, tradeDate);
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
		Matcher m =  p1.matcher(content);
		Date now = new Date();
		List<LongHu> longHuList = new ArrayList<LongHu>();
		Map<String,List<String>> typeMap = XinLangLongHuTool.getLongHuType(tradeDate);
		while(m.find()){
			LongHu longHu = new LongHu();
			String code = m.group(1);
			String stockName = m.group(2);
			String price = m.group(3);
			String chg = m.group(4);
			String turnover = m.group(5);
			longHu.setSymbol(StockTool.codeToSymbol(code));
			longHu.setStockName(stockName);
			longHu.setPrice(Double.valueOf(price));
			longHu.setChgPercent(Double.valueOf(chg));
			longHu.setTurnover(Double.valueOf(turnover));
			longHu.setTradeDate(tradeDate);
			longHu.setGmtCreate(now);
			List<String> typeList = typeMap.get(code);
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
		lhService.insertBatch(longHuList);
		for (LongHu longHu : longHuList) {
			fetchDetail(longHu);
		}
	}
	
	private void fetchDetail(LongHu longHu){
		if(StringUtil.isNotBlank(longHu.getYrType())){
			fetchDetail(1, longHu.getYrType(), longHu);
		}
		if(StringUtil.isNotBlank(longHu.getErType())){
			fetchDetail(2, longHu.getErType(), longHu);
		}
		if(StringUtil.isNotBlank(longHu.getSrType())){
			fetchDetail(3, longHu.getSrType(), longHu);
		}
	}
	
	private static final String r2 = "SYMBOL\\:\"([0-9]{6})\",type\\:\"([0-9]{2})\",comCode\\:\"([0-9]*)\",comName\\:\"([^\"]*)\",buyAmount\\:\"([0-9\\.]*)\",sellAmount\\:\"([0-9\\.]*)\",netAmount\\:([0-9\\.\\-]*)";
	private Pattern p2 = Pattern.compile(r2);
	
	public void fetchDetail(int dateType, String lhType, LongHu longHu){
		int index = lhType.indexOf(SymbolConstant.COMMA);
		if(index > 0){
			lhType = lhType.substring(0, index);
		}
		String code = StockUtil.symbol2Code(longHu.getSymbol());
		String date = longHu.getTradeDate();
		String url = buildDetailUrl(lhType, code, date);
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
		headerMap.put("Accept-Encoding", "gzip, deflate, sdch");
		String result = HttpTool.get(url, headerMap, Charset.forName("utf-8"));
		Matcher m = p2.matcher(result);
		BigDecimal buyTotal = BigDecimal.ZERO;
		BigDecimal sellTotal = BigDecimal.ZERO;
		Set<String> detpCodeSet = new HashSet<String>();
		Date now = new Date();
		List<LongHuDetail> lhdList = new ArrayList<LongHuDetail>();
		while(m.find()){
			if(longHu.getSymbol().equals("sh600908")){
				System.out.println(longHu.getSymbol());
			}
			String deptCode = m.group(3);
			String deptName = m.group(4);//TODO 这里是否需要更新营业部的名称
			String buyAmtStr = m.group(5);
			String sellAmtStr = m.group(6);
			String netAmtStr = m.group(7);
			if(detpCodeSet.contains(deptCode)){//重复了
				continue;
			} else {
				detpCodeSet.add(deptCode);
			}
			BigDecimal buyAmt = DecimalUtil.parse(buyAmtStr);
			BigDecimal sellAmt = DecimalUtil.parse(sellAmtStr);
			BigDecimal netAmt = DecimalUtil.parse(netAmtStr);
			buyTotal = buyTotal.add(buyAmt);
			sellTotal = sellTotal.add(sellAmt);
			LongHuDetail detail = new LongHuDetail();
			detail.setLongHuId(longHu.getId());
			detail.setBuyAmt(buyAmt);
			detail.setSellAmt(sellAmt);
			detail.setNetBuy(netAmt);
			detail.setDateType(dateType);
			detail.setGmtCreate(now);
			detail.setSecDeptCode(deptCode);
			detail.setSymbol(StockUtil.code2Symbol(code));
			detail.setTradeDate(date);
			lhdList.add(detail);
		}
		if(CollectionUtils.isNotEmpty(lhdList)){
			lhdService.batchInsert(lhdList);
		}
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
	
	private String buildDetailUrl(String type, String code, String date){
		StringBuilder sb = new StringBuilder();
		sb.append("http://vip.stock.finance.sina.com.cn/q/api/jsonp.php/var%20details=/InvestConsultService.getLHBComBSData?symbol=");
		sb.append(code);
		sb.append("&tradedate=");
		sb.append(date);
		sb.append("&type=");
		sb.append(type);
		return sb.toString();
	}
	
	public static void main(String[] args){
		LongHuTask task = new LongHuTask();
		task.execute("2016-10-11");
	}
}
