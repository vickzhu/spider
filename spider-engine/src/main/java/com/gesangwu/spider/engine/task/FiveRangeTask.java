package com.gesangwu.spider.engine.task;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.biz.common.DecimalUtil;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.FiveRangeStatis;
import com.gesangwu.spider.biz.dao.model.JdStatis;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.FiveRangeStatisService;
import com.gesangwu.spider.biz.service.JdStatisService;
import com.gesangwu.spider.engine.util.LittleCompanyHolder;
import com.gesangwu.spider.engine.util.TradeTimeUtil;

/**
 * 五档
 * <pre>
 * 这里的定时时间最长一分钟，涨跌幅7个点以上，不予计算
 * </pre>
 * @author zhuxb
 *
 */
@Component
public class FiveRangeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(FiveRangeTask.class);
	
	private static final String regex = "var hq_str_([\\w]{8})=\"(.*)\";";
	private static final Pattern r = Pattern.compile(regex);
	
	@Resource
	private CompanyService companyService;
	@Resource
	private FiveRangeStatisService statisService;
	@Resource
	private JdStatisService jdStatisService;
	
	@Scheduled(cron = "0 0/1 9-14 * * MON-FRI")
	public void execute(){
		if(!TradeTimeUtil.checkTime()){
			return;
		}
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
		logger.info("Fetch the FiveRange used:"+(end-start)+"ms!");
	}
	
	private void fetch(String symbolArr){
		String result = HttpTool.get("http://hq.sinajs.cn/etag.php?list=" + symbolArr,Charset.forName("GBK"));
		Matcher matcher = r.matcher(result);
		Date now = new Date();
		while(matcher.find()){
			boolean b = false;
			boolean s = false;
			int jiadan = 0;
			String symbol = matcher.group(1);
			String detail = matcher.group(2);
			String[] details = detail.split(SymbolConstant.COMMA);
			if(details.length < 30){
				continue;
			}
			String zs = details[2];//昨收
			String xj = details[3];//现价
			double chgPercent = calcChgPercent(zs, xj);
			if(chgPercent >= 0.07){//涨跌幅过大，不予计算
				continue;
			}
			String[] buyPriceArr = {details[11],details[13],details[15],details[17],details[19]};
			String[] buyVolArr = {details[10],details[12],details[14],details[16],details[18]};
			List<Double> bigBuyPrice = getBigPrice(buyPriceArr, buyVolArr);
			if(bigBuyPrice.size() > 0){
				if(bigBuyPrice.size() == 1){
					jiadan++;
				}
				b = checkSuperBigPrice(bigBuyPrice);
			}
			String[] sellPriceArr = {details[21],details[23],details[25],details[27],details[29]};
			String[] sellVolArr = {details[20],details[22],details[24],details[26],details[28]};
			List<Double> bigSellPrice = getBigPrice(sellPriceArr, sellVolArr);
			if(bigSellPrice.size() > 0){
				if(bigSellPrice.size() == 1){
					jiadan++;
				}
				s = checkSuperBigPrice(bigSellPrice);
			}
			String tradeDate = details[30];
			if(jiadan == 2){//上下夹单成立
				JdStatis jdStatis = jdStatisService.selectBySymbolAndDate(symbol, tradeDate);
				if(jdStatis == null){
					Company company = companyService.selectBySymbol(symbol);
					jdStatis = new JdStatis();
					double amv = DecimalUtil.format(company.getActiveMarketValue()/100000000,2).doubleValue();
					jdStatis.setActiveFloatMarket(amv);
					jdStatis.setGmtCreate(now);
					jdStatis.setJdTotal(1);
					jdStatis.setSymbol(symbol);
					jdStatis.setTradeDate(tradeDate);
					jdStatisService.insert(jdStatis);
				} else {
					jdStatis.setJdTotal(jdStatis.getJdTotal()+1);
					jdStatis.setGmtUpdate(now);
					jdStatisService.updateByPrimaryKey(jdStatis);
				}
			}
			
//			if(checkBuy(details)){
//				b = true;
//			}
//
//			if(checkSell(details)){
//				s = true;
//			}
			
			if(b||s){
				FiveRangeStatis statis = statisService.selectBySymbolAndDate(symbol, tradeDate);
				if(statis == null){
					statis = new FiveRangeStatis();
					if(b){
						statis.setBigBuy(1);
						statis.setBigSell(0);
					}
					if(s){
						statis.setBigSell(1);
						statis.setBigBuy(0);
					}
					Company company = companyService.selectBySymbol(symbol);
					statis.setTradeDate(tradeDate);
					statis.setSymbol(symbol);
					double amv = DecimalUtil.format(company.getActiveMarketValue()/100000000, 2).doubleValue();
					statis.setActiveMarketValue(amv);
					statis.setGmtCreate(now);
					statis.setGmtUpdate(now);
					statisService.insert(statis);
				} else {
					if(b){
						statis.setBigBuy(statis.getBigBuy()+1);
					}
					if(s){
						statis.setBigSell(statis.getBigSell()+1);
					}
					statis.setGmtUpdate(now);
					statisService.updateByPrimaryKey(statis);
				}
			}
		}
	}
	
	private static final double minBigPrice = 2000000;
	private static final int min_price = 10000000;
	
	/**
	 * 获得大单
	 * @return
	 */
	private List<Double> getBigPrice(String[] priceArr, String[] volArr ){
		List<Double> bigPriceList = new ArrayList<Double>();
		for (int i = 0; i < priceArr.length; i++) {
			double price = calcAmt(priceArr[i], volArr[i]);
			if(price >= minBigPrice){
				bigPriceList.add(price);
			}
		}
		return bigPriceList;
	}
	
	/**
	 * 检查是否为超大单
	 * @param bigPrice
	 * @return
	 */
	private boolean checkSuperBigPrice(List<Double> bigPrice){
		for (Double price : bigPrice) {
			if(price >= min_price ){
				return true;
			}
		}
		return false;
	}
	
//	public static void main(String[] args){
//		FiveRangeTask task = new FiveRangeTask();
//		String result = HttpTool.get("http://hq.sinajs.cn/etag.php?list=sz002211",Charset.forName("GBK"));
//		Matcher matcher = r.matcher(result);
//		while(matcher.find()){
//			String detail = matcher.group(2);
//			String[] details = detail.split(SymbolConstant.COMMA);
//			if(details.length < 30){
//				continue;
//			}
//			String[] buyPriceArr = {details[11],details[13],details[15],details[17],details[19]};
//			String[] buyVolArr = {details[10],details[12],details[14],details[16],details[18]};
//			List<Double> bigBuyPrice = task.getBigPrice(buyPriceArr, buyVolArr);
//			if(bigBuyPrice.size() > 0){
//				boolean superBigBuy = task.checkSuperBigPrice(bigBuyPrice);
//				System.out.println(superBigBuy);
//			}
//			for (Double double1 : bigBuyPrice) {
//				System.out.println(double1);
//			}
//			String[] sellPriceArr = {details[21],details[23],details[25],details[27],details[29]};
//			String[] sellVolArr = {details[20],details[22],details[24],details[26],details[28]};
//			List<Double> bigSellPrice = task.getBigPrice(sellPriceArr, sellVolArr);
//			for (Double double1 : bigSellPrice) {
//				System.out.println(double1);
//			}
//			if(bigSellPrice.size() > 0){
//				boolean superBigSell = task.checkSuperBigPrice(bigSellPrice);
//				System.out.println(superBigSell);
//			}
//		}
//	}
	
	
//	private boolean checkBuy(String[] details){
//		String b_1_c = details[10];
//		String b_2_c = details[12];
//		String b_3_c = details[14];
//		String b_4_c = details[16];
//		String b_5_c = details[18];
//		String buy_1_price = details[11];
//		String buy_2_price = details[13];
//		String buy_3_price = details[15];
//		String buy_4_price = details[17];
//		String buy_5_price = details[19];
//		double b1amt = calcAmt(b_1_c, buy_1_price);
//		if(b1amt >= min_price){
//			return true;
//		}
//		double b2amt = calcAmt(b_2_c, buy_2_price);
//		if(b2amt >= min_price){
//			return true;
//		}
//		double b3amt = calcAmt(b_3_c, buy_3_price);
//		if(b3amt >= min_price){
//			return true;
//		}
//		double b4amt = calcAmt(b_4_c, buy_4_price);
//		if(b4amt >= min_price){
//			return true;
//		}
//		double b5amt = calcAmt(b_5_c, buy_5_price);
//		if(b5amt >= min_price){
//			return true;
//		}
//		return false;
//	}
	
//	private boolean checkSell(String[] details){
//		String s_1_c = details[20];
//		String s_2_c = details[22];
//		String s_3_c = details[24];
//		String s_4_c = details[26];
//		String s_5_c = details[28];
//		String sell_1_price = details[21];
//		String sell_2_price = details[23];
//		String sell_3_price = details[25];
//		String sell_4_price = details[27];
//		String sell_5_price = details[29];
//		double b1amt = calcAmt(s_1_c, sell_1_price);
//		if(b1amt >= min_price){
//			return true;
//		}
//		double b2amt = calcAmt(s_2_c, sell_2_price);
//		if(b2amt >= min_price){
//			return true;
//		}
//		double b3amt = calcAmt(s_3_c, sell_3_price);
//		if(b3amt >= min_price){
//			return true;
//		}
//		double b4amt = calcAmt(s_4_c, sell_4_price);
//		if(b4amt >= min_price){
//			return true;
//		}
//		double b5amt = calcAmt(s_5_c, sell_5_price);
//		if(b5amt >= min_price){
//			return true;
//		}
//		return false;
//	}
	
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
	
	/**
	 * 计算挂单价格，单位万
	 * @param volStr
	 * @param priceStr
	 * @return
	 */
	private double calcAmt(String volStr, String priceStr){
		double vol = Double.valueOf(volStr);
		double price = Double.valueOf(priceStr);
		if(price < 10){
			vol = vol * price / 10;
		}
		return vol * price;
	}

}
