package com.gesangwu.spider.engine.task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.util.DateUtil;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.DecimalUtil;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.LargeVol;
import com.gesangwu.spider.biz.dao.model.LargeVolStatis;
import com.gesangwu.spider.biz.service.CompanyService;
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
	
	private static final Logger logger = LoggerFactory.getLogger(LargeVolTask.class);
	
	private static final String p2 = ",\"list\"\\:(.*)}";
	private Pattern r2 = Pattern.compile(p2);
	
	@Resource
	private LargeVolService service;
	@Resource
	private LargeVolStatisService statisService;
	@Resource
	private CompanyService companyService;
	private static final int volMin = 1000;
	
	@Scheduled(cron = "59 0/1 9-14 * * MON-FRI")
	public void execute(){
		if(!TradeTimeUtil.checkTime()){
			return;
		}
		String timePeriod = getTimePeriod();
		execute(timePeriod);
	}
	
	@SuppressWarnings("unchecked")
	public void execute(String timePeriod){
		Date now = new Date();
		long start = System.currentTimeMillis();
		String url = buildUrl(0, timePeriod, volMin);
		String result = HttpTool.get(url);
		Matcher m = r2.matcher(result);
		if(!m.find()){
			logger.info("Can't match the largevol from remote!");
			return;
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Map<String, Object>> list = mapper.readValue(m.group(1), List.class);
			for (Map<String, Object> columnMap : list) {
				int tradeType = (Integer)columnMap.get("TRADE_TYPE");
				int vol = (Integer)columnMap.get("VOLUME_INC");
				String code = (String)columnMap.get("SYMBOL");
				Object percentObj = columnMap.get("PERCENT");
				Object amountObj = columnMap.get("TURNOVER_INC");
				String time = (String)columnMap.get("DATE");
				if(StringUtil.isBlank(LittleCompanyHolder.getNameByCode(code))){//大市值企业不需要
					continue;
				}
				double amount = Double.valueOf(amountObj.toString());
				if(amount < 8000000){
					continue;
				}
				LargeVol lv = new LargeVol();
				lv.setAmount(amount);
				lv.setGmtCreate(now);
				lv.setPercent(Double.valueOf(percentObj.toString()));
				String symbol = StockUtil.code2Symbol(code);
				lv.setSymbol(symbol);
				lv.setTradeTime(time);
				lv.setVol(vol);
				lv.setTradeType(tradeType);
				service.insert(lv);
				String tradeDate = DateUtil.format(now, "yyyy-MM-dd");
				LargeVolStatis statis = statisService.selectBySymbolAndDate(symbol, tradeDate);
				if(statis == null){
					Company company = companyService.selectBySymbol(symbol);
					statis = new LargeVolStatis();
					statis.setSymbol(symbol);
					statis.setTradeDate(tradeDate);
					statis.setSellTotal(0);
					statis.setEqualTotal(0);
					statis.setBuyTotal(0);
					double amv = DecimalUtil.format(company.getActiveMarketValue()/100000000, 2).doubleValue();
					statis.setActiveMarketValue(amv);
					statis.setGmtCreate(now);
					statis.setGmtUpdate(now);
					statisService.insert(statis);
				}
				if(tradeType == -1){
					statis.setSellTotal(statis.getSellTotal()+1);
				} else if(tradeType == 0){
					statis.setEqualTotal(statis.getEqualTotal()+1);
				} else if(tradeType == 1){
					statis.setBuyTotal(statis.getBuyTotal()+1);
				}
				statis.setGmtUpdate(now);
				statisService.updateByPrimaryKey(statis);
			}
		} catch (Exception e) {
			logger.error("Parse largeVol failed!", e);
		} 
		long end = System.currentTimeMillis();
		logger.info("Fetch the LargeVol used:" + (end - start) + "ms!");
	}
	
	private String getTimePeriod(){
		StringBuffer sb = new StringBuffer();
		Calendar c = Calendar.getInstance();
		int hourEnd = c.get(Calendar.HOUR_OF_DAY);
		int minEnd = c.get(Calendar.MINUTE);
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

}
