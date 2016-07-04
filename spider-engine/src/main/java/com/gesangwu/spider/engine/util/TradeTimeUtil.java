package com.gesangwu.spider.engine.util;

import java.util.Calendar;

public class TradeTimeUtil {

	public static boolean checkTime(){
		Calendar c = Calendar.getInstance();
		int hod = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		if(hod == 9 && min <= 30){
			return Boolean.FALSE;
		}
		if(hod == 11 && min >= 30){
			return Boolean.FALSE;
		}
		if(hod == 12){
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
}
