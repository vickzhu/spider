package com.gesangwu.spider.engine.task;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
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
 * 五档，压托单
 * <pre>
 * 这里的定时时间最长一分钟，涨跌幅7个点以上，不予计算
 * </pre>
 * @author zhuxb
 *
 */
//@Component
public class FiveRangeTask extends BaseTask {
	
	private static final Logger logger = LoggerFactory.getLogger(FiveRangeTask.class);
	
	private static final String regex = "var hq_str_([\\w]{8})=\"(.*)\";";
	private static final Pattern r = Pattern.compile(regex);
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	private CompanyService companyService;
	@Resource
	private FiveRangeStatisService statisService;
	@Resource
	private JdStatisService jdStatisService;
	
	//@Scheduled(cron = "0 0/1 9-14 * * MON-FRI")
	public void execute(){
		Date now = new Date();
		if(!isTradeDate(sdf.format(now))){
			logger.info("非交易日！！！");
			return;
		}
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
			List<Double> bigBuyAmt = getBigPrice(buyPriceArr, buyVolArr);
			if(bigBuyAmt.size() > 0){
				b = checkSuperBigPrice(bigBuyAmt);
				if(existJiadan(buyVolArr)){
					jiadan++;
				}
			}
			
			String[] sellPriceArr = {details[21],details[23],details[25],details[27],details[29]};
			String[] sellVolArr = {details[20],details[22],details[24],details[26],details[28]};
			List<Double> bigSellAmt = getBigPrice(sellPriceArr, sellVolArr);
			if(bigSellAmt.size() > 0){				
				s = checkSuperBigPrice(bigSellAmt);
				if(jiadan == 1 && existJiadan(sellVolArr)){
					jiadan++;
				}
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
					jdStatis.setGmtUpdate(now);
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
	
	private static final double minBigPrice = 3000000;
	private static final int min_price = 5000000;
	private static final int min_max_price = 10000000;

	
	/**
	 * 是否存在夹单，判断依据，最大
	 * @param volArr
	 * @return
	 */
	private boolean existJiadan(String[] volArr){
		int maxLen = 0;
		int count = 0;
		for (String vol : volArr) {
			int tmpLen = vol.length();
			if(tmpLen > maxLen){
				maxLen = tmpLen;
				count = 1;
			} else if(tmpLen == maxLen){
				count++;
			}
		}
		return count == 1;
	}
	
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
		double total = 0;
		for (Double price : bigPrice) {
			if(price >= min_price ){
				total += price;
//				return true;
			}
		}
		return total >= min_max_price;
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
