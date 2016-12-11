package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.JdStatis;
import com.gesangwu.spider.biz.dao.model.JdStatisExample;

public interface JdStatisService extends BaseService<JdStatis, JdStatisExample> {
	
	/**
	 * 用于定时任务读取是否有当日统计
	 * @param symbol
	 * @param tradeDate
	 * @return
	 */
	public JdStatis selectBySymbolAndDate(String symbol, String tradeDate);
	
	/**
	 * 用于按日期显示
	 * @param tradeDate
	 * @param minAmv
	 * @param maxAmv
	 * @return
	 */
	List<JdStatis> selectByTradeDate(String tradeDate, double minAmv,double maxAmv);

}
