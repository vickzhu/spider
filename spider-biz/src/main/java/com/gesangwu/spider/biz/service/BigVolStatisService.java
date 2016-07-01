package com.gesangwu.spider.biz.service;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.BigVolStatis;
import com.gesangwu.spider.biz.dao.model.BigVolStatisExample;

public interface BigVolStatisService extends BaseService<BigVolStatis, BigVolStatisExample> {
	
	public BigVolStatis selectBySymbolAndDate(String symbol, String date);
	
}
