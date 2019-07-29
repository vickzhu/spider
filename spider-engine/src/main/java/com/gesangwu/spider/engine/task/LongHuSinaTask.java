package com.gesangwu.spider.engine.task;

import java.math.BigDecimal;
import java.nio.charset.Charset;
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
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.DecimalUtil;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.service.LongHuTypeService;
import com.gesangwu.spider.engine.util.StockTool;
import com.gesangwu.spider.engine.util.XinLangLongHuTool;

@Component
public class LongHuSinaTask extends LongHuTaskTemplate {
	
	private static final Logger logger = LoggerFactory.getLogger(LongHuSinaTask.class);
	
	private static final String r1 = "\"SCode\"\\:\"([0-9]*)\",\"SName\"\\:\"([^\"]*)\",\"ClosePrice\"\\:\"([0-9\\.]*)\",\"Chgradio\"\\:\"([\\-0-9\\.]*)\",\"Dchratio\"\\:\"([0-9\\.]*)\",\"JmMoney\"\\:\"[\\-0-9\\.]*\",\"Turnover\"\\:\"([\\-0-9\\.]*)\",\"Ntransac\"\\:\"([\\-0-9\\.]*)\",\"Ctypedes\"\\:\"[^\"]*\",\"Oldid\"\\:\"[\\-0-9\\.]*\",\"Smoney\"\\:\"([0-9\\.]*)\",\"Bmoney\"\\:\"([0-9\\.]*)\",\"ZeMoney\"\\:\"[^\"]*\",\"Tdate\"\\:\"([^\"]*)\",\"JmRate\"\\:\"[^\"]*\",\"ZeRate\"\\:\"[^\"]*\",\"Ltsz\"\\:\"([^\"]*)\"";
	private static Pattern p1 = Pattern.compile(r1);
	
	@Resource
	private LongHuTypeService typeService;

	@Override
	protected List<LongHu> getLongHuList(String tradeDate) {
		String url = buildUrl(tradeDate, 0);
		String content = HttpTool.get(url, Charset.forName("GBK"));
		Matcher m =  p1.matcher(content);
		Date now = new Date();
		List<LongHu> longHuList = new ArrayList<LongHu>();
		Set<String> lhCodeSet = new HashSet<String>();
		Map<String,List<String>> typeMap = XinLangLongHuTool.getLongHuType(tradeDate);
		if(typeMap == null || typeMap.size() == 0){
			logger.error("No type Map fund from Sina!");
			return longHuList;
		}
		while(m.find()){
			String code = m.group(1);
			String symbol = StockTool.codeToSymbol(code);
	
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
		return longHuList;
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
	
	private void buildLhType(List<String> yrList, List<String> erList, List<String> srList, List<String> typeList){
		for (String type : typeList) {
			LongHuType lhType = typeService.selectByType(type);
			if(lhType == null){
				yrList.add(type);
				logger.error("Can't find long hu type by:" + lhType);
				return;
			}
			if(lhType.getDateType() == 1){//一日
				yrList.add(type);
			} else if(lhType.getDateType() == 3){//三日
				srList.add(type);
			} else if(lhType.getDateType() == 2){//二日
				erList.add(type);
			}
		}
	}
	
	private static final String r2 = "SYMBOL\\:\"([0-9]{6})\",type\\:\"([0-9]{2})\",comCode\\:\"([0-9]*)\",comName\\:\"([^\"]*)\",buyAmount\\:\"([0-9\\.]*)\",sellAmount\\:\"([0-9\\.]*)\",netAmount\\:([0-9\\.\\-]*)";
	private Pattern p2 = Pattern.compile(r2);

	@Override
	public List<LongHuDetail> getLongHuDetailList(int dateType, String lhType,
			LongHu longHu) {
		int index = lhType.indexOf(SymbolConstant.COMMA);
		if(index > 0){
			lhType = lhType.substring(0, index);
		}
		String code = StockUtil.symbol2Code(longHu.getSymbol());
		String tradeDate = longHu.getTradeDate();
		String result = getDetailContent(lhType, code, tradeDate);
		Matcher m = p2.matcher(result);
		BigDecimal buyTotal = BigDecimal.ZERO;
		BigDecimal sellTotal = BigDecimal.ZERO;
		Date now = new Date();
		Map<String,LongHuDetail> detailMap = new HashMap<String,LongHuDetail>();
		while(m.find()){
			String deptCode = m.group(3);
			String deptName = m.group(4);
			String buyAmtStr = m.group(5);
			String sellAmtStr = m.group(6);
			String netAmtStr = m.group(7);
			souDept(deptCode, deptName);
			LongHuDetail detail = detailMap.get(deptCode);
			BigDecimal buyAmt = DecimalUtil.parse(buyAmtStr);
			BigDecimal sellAmt = DecimalUtil.parse(sellAmtStr);
			BigDecimal netBuy = DecimalUtil.parse(netAmtStr);
			if(detail != null){
				if(detail.getSellAmt().compareTo(sellAmt) < 0){
					detail.setSellAmt(sellAmt);
					netBuy = detail.getBuyAmt().subtract(sellAmt);
					detail.setNetBuy(netBuy);
					sellTotal = sellTotal.add(sellAmt);
				}
				continue;
			} else {
				detail = new LongHuDetail(); 
				detailMap.put(deptCode, detail);
			}
			
			buyTotal = buyTotal.add(buyAmt);
			sellTotal = sellTotal.add(sellAmt);

			detail.setLongHuId(longHu.getId());
			detail.setBuyAmt(buyAmt);
			detail.setSellAmt(sellAmt);
			detail.setNetBuy(netBuy);
			detail.setDateType(dateType);
			detail.setGmtCreate(now);
			detail.setSecDeptCode(deptCode);
			detail.setSymbol(StockUtil.code2Symbol(code));
			detail.setTradeDate(tradeDate);	
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
	
	private String getDetailContent(String lhType, String code, String tradeDate){
		String url = buildDetailUrl(lhType, code, tradeDate);
		return HttpTool.get(url, Charset.forName("GBK"));
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

}
