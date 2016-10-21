package com.gesangwu.spider.engine.task;

import java.util.Calendar;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;

@Component
public class TestTask {
	
//	@Scheduled(cron = "59 0/1 16 * * MON-FRI")
	public void execute(){
		String timePeriod = getTimePeriod();
		String url = buildUrl(0, timePeriod, 1000);
		System.out.println(url);
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
}
