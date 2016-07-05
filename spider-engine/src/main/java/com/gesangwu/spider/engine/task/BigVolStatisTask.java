package com.gesangwu.spider.engine.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.dao.model.BigVolStatis;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.service.BigVolStatisService;
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
public class BigVolStatisTask {
	
	private static final String regex = "{\"CODE\"\\:\"\\d*\",\"TRADE_TYPE\"\\:[^,]*,\"PRICE_PRE\"\\:([0-9\\.]*),\"VOLUME_INC\"\\:(\\d*),\"SYMBOL\"\\:\"[0-9]{6}\",\"PERCENT\"\\:([0-9\\.\\-]*),\"NAME\"\\:\"[^\"]*\",\"PRICE\"\\:([0-9\\.]*),\"TURNOVER_INC\"\\:([0-9\\.]*),\"DATE\"\\:\"([^\"]*)\",\"RN\"\\:\\d*}";
	private Pattern r = Pattern.compile(regex);
	
	@Resource
	private BigVolStatisService statisService;
	
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat df_full = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
//	@Scheduled(cron = "0 0/1 9-14 * * MON-FRI")
	public void execute(){
//		if(!TradeTimeUtil.checkTime()){
//			return;
//		}
		long start = System.currentTimeMillis();
		Date now = new Date();
		String date = df.format(now);
		
		int page = 0;
		int vol = 3000;
		
		String url = "http://quotes.money.163.com/hs/realtimedata/service/dadan.php?page="+page+"&query=vol:"+vol+";time_period:09_50-10_00&sort=DATE&order=desc&count=50";
		
		String result = HttpTool.get(url);
		System.out.println(result);
		Matcher m = r.matcher(result);
		while(m.matches()){
			System.out.println(m.group(1));
		}
		long end = System.currentTimeMillis();
		System.out.println("======================" + (end - start));
		
	}

	
	public static void main(String[] args){
		BigVolStatisTask task = new BigVolStatisTask();
		task.execute();
	}
}
