package com.gesangwu.spider.engine.task;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.LargeVol;
import com.gesangwu.spider.biz.dao.model.LargeVolStatis;
import com.gesangwu.spider.biz.service.LargeVolService;
import com.gesangwu.spider.biz.service.LargeVolStatisService;
import com.gesangwu.spider.engine.util.LittleCompanyHolder;
import com.gesangwu.spider.engine.util.TradeTimeUtil;

/**
 * 大成交量统计
 * <pre>
 * 网易大单统计，
 * http://quotes.money.163.com/hs/realtimedata/service/dadan.php?t=0?host=/hs/realtimedata/service/dadan.php?t=0&page=1&query=vol:6000;time_period:09_55-10_00&fields=RN,SYMBOL,NAME,DATE,PRICE,PRICE_PRE,PERCENT,TURNOVER_INC,VOLUME_INC,TRADE_TYPE,,CODE&sort=DATE&order=desc&count=25&type=query&callback=callback_1062193561&req=3232
 * 
 * </pre>
 * @author zhuxb
 *
 */
@Component
public class LargeVolTask {
	
	private static final String regex = "\\{\"CODE\"\\:\"\\d*\",\"SYMBOL\"\\:\"([0-9]{6})\",\"TRADE_TYPE\"\\:([^,]*),\"PRICE_PRE\"\\:[0-9\\.]*,\"VOLUME_INC\"\\:(\\d*),\"PERCENT\"\\:([0-9\\.\\-]*),\"NAME\"\\:\"[^\"]*\",\"PRICE\"\\:[0-9\\.]*,\"TURNOVER_INC\"\\:([0-9\\.]*),\"DATE\"\\:\"([^\"]*)\",\"RN\"\\:\\d*\\}";
	private Pattern r = Pattern.compile(regex);
	
	@Resource
	private LargeVolService service;
	@Resource
	private LargeVolStatisService statisService;
	private static final int volMin = 1000;
	
	@Scheduled(cron = "59 0/1 9-14 * * MON-FRI")
	public void execute(){
		if(!TradeTimeUtil.checkTime()){
			return;
		}
		long start = System.currentTimeMillis();
		Date now = new Date();
		int page = 0;
		
		String timePeriod = getTimePeriod();
		String url = buildUrl(page, timePeriod, volMin);
		String result = HttpTool.get(url);
		Matcher m = r.matcher(result);
		while(m.find()){
			String tradeType = m.group(2);
			String vol = m.group(3);
			String code = m.group(1);
			String percent = m.group(4);
			String amountStr = m.group(5);
			String time = m.group(6);
			if(StringUtil.isBlank(LittleCompanyHolder.getNameByCode(code))){//大市值企业不需要
				continue;
			}
			double amount = Double.valueOf(amountStr);
			if(amount < 8000000){
				continue;
			}
			LargeVol lv = new LargeVol();
			lv.setAmount(amount);
			lv.setGmtCreate(now);
			lv.setPercent(Double.valueOf(percent));
			String symbol = StockUtil.code2Symbol(code);
			lv.setSymbol(symbol);
			lv.setTradeTime(time);
			lv.setVol(Integer.valueOf(vol));
			lv.setTradeType(Integer.valueOf(tradeType));
			service.insert(lv);
			LargeVolStatis statis = statisService.selectBySymbolAndDate(symbol, now);
			if(statis == null){
				statis = new LargeVolStatis();
				statis.setSymbol(symbol);
				statis.setDate(now);
				statis.setSellTotal(0);
				statis.setEqualTotal(0);
				statis.setBuyTotal(0);
				statis.setGmtCreate(now);
				statis.setGmtUpdate(now);
				statisService.insert(statis);
			}
			if("-1".equals(tradeType)){
				statis.setSellTotal(statis.getSellTotal()+1);
			} else if("0".equals(tradeType)){
				statis.setEqualTotal(statis.getEqualTotal()+1);
			} else if("1".equals(tradeType)){
				statis.setBuyTotal(statis.getBuyTotal()+1);
			}
			statis.setGmtUpdate(now);
			statisService.updateByPrimaryKey(statis);
		}
		long end = System.currentTimeMillis();
		System.out.println("======================" + (end - start));
	}
	
	private String getTimePeriod(){
		StringBuffer sb = new StringBuffer();
		Calendar c = Calendar.getInstance();
		int hourEnd = c.get(Calendar.HOUR_OF_DAY);
		int minEnd = c.get(Calendar.MINUTE);
//		int hourEnd = 14;
//		int minEnd = 31;
		int hourStart = hourEnd;
		int minStart = minEnd - 1;
		if(minEnd == 0){
			hourStart = hourEnd - 1;
			minStart = 59;
		}
		sb.append(hourStart);
		sb.append(SymbolConstant.U_LINE);
		sb.append(minStart);
		sb.append(SymbolConstant.H_LINE);
		sb.append(hourEnd);
		sb.append(SymbolConstant.U_LINE);
		sb.append(minEnd);
		return sb.toString();
	}
	
	private String buildUrl(int page, String timePeriod, int vol){
		StringBuffer sb = new StringBuffer();
		sb.append("http://quotes.money.163.com/hs/realtimedata/service/dadan.php?page=");
		sb.append(page);
		sb.append("&query=vol:");
		sb.append(vol);
		sb.append(";time_period:");
		sb.append(timePeriod);
		sb.append("&sort=DATE&order=desc&count=500");
		return sb.toString();
	}

	public static void main(String[] args){
		LargeVolTask task = new LargeVolTask();
		task.execute();
	}
}
