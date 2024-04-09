package com.gesangwu.spider.engine.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.service.HolidayService;

public abstract class BaseTask {

	protected static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	protected static final String cybStartDate = "2020-08-24";

	@Resource
	protected HolidayService holidayService;

	/**
	 * 是否为交易日
	 * 
	 * @return
	 */
	protected boolean isTradeDate(String tradeDate) {
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(tradeDate));
		} catch (ParseException e) {
			throw new RuntimeException("日期转换错误！！！");
		}
		if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			return false;
		}
		List<String> dateList = holidayService.selectByYear(String.valueOf(c.get(Calendar.YEAR)));
		for (String hd : dateList) {
			if (tradeDate.equals(hd)) {
				return false;
			}
		}
		return true;
	}

	protected String getTradeDate(String tradeDate) {
		String date = tradeDate;
		if (StringUtil.isBlank(date)) {
			Date now = new Date();
			date = sdf.format(now);
		}
		if (!isTradeDate(date)) {
			throw new RuntimeException("法定节假日无相关数据！！！");
		}
		return date;
	}

	protected String getPreTradeDate(String tradeDate) {
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(tradeDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		String preDate = sdf.format(c.getTime());
		if (!isTradeDate(preDate)) {// 非交易日
			return getPreTradeDate(preDate);
		}
		return preDate;
	}

}
