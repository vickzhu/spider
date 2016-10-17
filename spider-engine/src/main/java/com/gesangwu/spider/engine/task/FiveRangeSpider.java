package com.gesangwu.spider.engine.task;

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
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.FiveRangeStatis;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.FiveRangeStatisService;
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
public class FiveRangeSpider {
	
	private static final Logger logger = LoggerFactory.getLogger(FiveRangeSpider.class);
	
	private static final String regex = "var hq_str_([\\w]{8})=\"(.*)\";";
	private static final Pattern r = Pattern.compile(regex);
	
	@Resource
	private CompanyService companyService;
	@Resource
	private FiveRangeStatisService statisService;
	
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
			sb.append(companyList.get(i).getSymbol());
			sb.append(SymbolConstant.COMMA);
			if(i == size - 1){
				fetch(sb.toString());
				sb = new StringBuffer();
			} else {
				if(i != 0 && i % 499 == 0){
					fetch(sb.toString());
					sb = new StringBuffer();
				}
			}
		}
		long end = System.currentTimeMillis();
		logger.info("Fetch the FiveRange use:"+(end-start)+"ms!");
	}
	
	private void fetch(String symbolArr){
		String result = HttpTool.get("http://hq.sinajs.cn/etag.php?list=" + symbolArr);
		Matcher matcher = r.matcher(result);
		Date now = new Date();
		while(matcher.find()){
			boolean b = false;
			boolean s = false;
			String symbol = matcher.group(1);
			String detail = matcher.group(2);
			String[] details = detail.split(SymbolConstant.COMMA);
			String zs = details[2];
			String xj = details[3];
			double chgPercent = calcChgPercent(zs, xj);
			if(chgPercent >= 0.07){//涨跌幅过大，不予计算
				continue;
			}
			if(checkBuy(details)){
				b = true;
			}

			if(checkSell(details)){
				s = true;
			}
			String date = details[30];
			if(b||s){
				FiveRangeStatis statis = statisService.selectBySymbolAndDate(symbol, date);
				if(statis == null){
					statis = new FiveRangeStatis();
					if(b){
						statis.setBigBuy(1);
					}
					if(s){
						statis.setBigSell(1);
					}
					statis.setDate(date);
					statis.setStockName(details[0]);
					statis.setSymbol(symbol);
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
	
	private static int min_price = 500;
	
	private boolean checkBuy(String[] details){
		String b_1_c = details[10];
		String b_2_c = details[12];
		String b_3_c = details[14];
		String b_4_c = details[16];
		String b_5_c = details[18];
		String buy_1_price = details[11];
		String buy_2_price = details[13];
		String buy_3_price = details[15];
		String buy_4_price = details[17];
		String buy_5_price = details[19];
		double b1amt = calcAmt(b_1_c, buy_1_price);
		if(b1amt >= min_price){
			return true;
		}
		double b2amt = calcAmt(b_2_c, buy_2_price);
		if(b2amt >= min_price){
			return true;
		}
		double b3amt = calcAmt(b_3_c, buy_3_price);
		if(b3amt >= min_price){
			return true;
		}
		double b4amt = calcAmt(b_4_c, buy_4_price);
		if(b4amt >= min_price){
			return true;
		}
		double b5amt = calcAmt(b_5_c, buy_5_price);
		if(b5amt >= min_price){
			return true;
		}
		return false;
	}
	
	private boolean checkSell(String[] details){
		String s_1_c = details[20];
		String s_2_c = details[22];
		String s_3_c = details[24];
		String s_4_c = details[26];
		String s_5_c = details[28];
		String sell_1_price = details[21];
		String sell_2_price = details[23];
		String sell_3_price = details[25];
		String sell_4_price = details[27];
		String sell_5_price = details[29];
		double b1amt = calcAmt(s_1_c, sell_1_price);
		if(b1amt >= min_price){
			return true;
		}
		double b2amt = calcAmt(s_2_c, sell_2_price);
		if(b2amt >= min_price){
			return true;
		}
		double b3amt = calcAmt(s_3_c, sell_3_price);
		if(b3amt >= min_price){
			return true;
		}
		double b4amt = calcAmt(s_4_c, sell_4_price);
		if(b4amt >= min_price){
			return true;
		}
		double b5amt = calcAmt(s_5_c, sell_5_price);
		if(b5amt >= min_price){
			return true;
		}
		return false;
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
		return vol * price / 10000;
	}

}
