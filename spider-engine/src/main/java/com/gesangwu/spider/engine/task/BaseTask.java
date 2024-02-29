package com.gesangwu.spider.engine.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.gesangwu.spider.biz.service.HolidayService;

public abstract class BaseTask {
	
	protected static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	protected static final String cybStartDate = "2020-08-24";
	
	@Resource
	protected HolidayService holidayService;
	
	/**
	 * 是否为交易日
	 * @return
	 */
	protected boolean isTradeDate(String tradeDate){
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(tradeDate));
		} catch (ParseException e) {
			throw new RuntimeException("日期转换错误！！！");
		}
		if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			return false;
		}
		List<String> dateList = holidayService.selectByYear(String.valueOf(c.get(Calendar.YEAR)));
		for (String hd : dateList) {
			if(tradeDate.equals(hd)){
				return false;
			}
		}
		return true;
	}
}
