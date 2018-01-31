package com.gesangwu.spider.engine.task;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
import com.gesangwu.spider.biz.dao.model.Bidding;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.service.BiddingService;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.engine.common.BiddingHolder;
import com.gesangwu.spider.engine.util.LittleCompanyHolder;


@Component
public class BiddingTask {
	
private static final Logger logger = LoggerFactory.getLogger(BiddingTask.class);
	
	private static final String regex = "var hq_str_([\\w]{8})=\"(.*)\";";
	private static final Pattern r = Pattern.compile(regex);
	
	@Resource
	private CompanyService companyService;
	@Resource
	private BiddingService bdService;
	
	@Scheduled(cron = "0/3 20-24 9 * * MON-FRI")
	public void execute(){
		calc();
	}
	
	public void calc(){
		long start = System.currentTimeMillis();
		List<Company> companyList = LittleCompanyHolder.getCompanyList();
		int size = companyList.size();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			Company company = companyList.get(i);
			if(company.getLastPrice().doubleValue() <= 5){//股价太低的不予考虑
				continue;
			}
			String stockName = company.getStockName();
			if(stockName.startsWith("S") || stockName.startsWith("*S")){//去掉带S和*的
				continue;
			}
			sb.append(company.getSymbol());
			sb.append(SymbolConstant.COMMA);
			if(i == size - 1){
				fetch(sb.toString());
				sb = new StringBuffer();
			} else {
				if(i != 0 && i % 299 == 0){
					fetch(sb.toString());
					sb = new StringBuffer();
				}
			}
		}
		long end = System.currentTimeMillis();
		logger.info("Fetch Bidding used:"+(end-start)+"ms!");
	}

	private void fetch(String symbolArr){
		String result = HttpTool.get("http://hq.sinajs.cn/etag.php?list=" + symbolArr,Charset.forName("GBK"));
		Matcher matcher = r.matcher(result);
		Date now = new Date();
		while(matcher.find()){
			String symbol = matcher.group(1);
			String detail = matcher.group(2);
			String[] details = detail.split(SymbolConstant.COMMA);
			if(details.length < 30){
				continue;
			}
			String zs = details[2];//昨收
			String xj = details[3];//现价
//			double chgPercent = calcChgPercent(zs, xj);//涨幅
			
			String[] buyPriceArr = {details[11],details[13],details[15],details[17],details[19]};
			String[] buyVolArr = {details[10],details[12],details[14],details[16],details[18]};
			
//			String[] sellPriceArr = {details[21],details[23],details[25],details[27],details[29]};
			String[] sellVolArr = {details[20],details[22],details[24],details[26],details[28]};
			
//			String tradeDate = details[30];
			String tradeTime = details[31];
			if("09:25:00".compareTo(tradeTime) < 0){
				continue;
			}
			Bidding bd = new Bidding();
			bd.setPrice(Double.valueOf(buyPriceArr[0]));
			bd.setVol(Integer.valueOf(buyVolArr[0]));
			bd.setBuySurplus(Integer.valueOf(buyVolArr[1]));
			bd.setSellSurplus(Integer.valueOf(sellVolArr[1]));
			bd.setSymbol(symbol);
			bd.setTradeTime(tradeTime);
			bd.setGmtCreate(now);
			
			Map<String, Bidding> biddingMap = BiddingHolder.getBidMap().get(symbol);
			if(biddingMap == null){
				biddingMap = new TreeMap<String, Bidding>();
				BiddingHolder.getBidMap().put(symbol, biddingMap);
			}
			biddingMap.put(tradeTime, bd);
		}
	}
	
	/**
	 * 计算涨跌幅
	 * @param zs	昨收
	 * @param xj	现价
	 * @return
	 */
	private double calcChgPercent(String zs, String xj){
		double zuoshou = Double.valueOf(zs);
		double xianjia = Double.valueOf(xj);
		double change = Math.abs(xianjia - zuoshou);
		return change/zuoshou;
	}

}
