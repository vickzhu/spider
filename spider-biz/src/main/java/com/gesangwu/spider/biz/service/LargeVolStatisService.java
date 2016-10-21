package com.gesangwu.spider.biz.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.LargeVolStatis;
import com.gesangwu.spider.biz.dao.model.LargeVolStatisExample;

public interface LargeVolStatisService extends BaseService<LargeVolStatis, LargeVolStatisExample> {

	public LargeVolStatis selectBySymbolAndDate(String symbol, String tradeDate);
	
	List<LargeVolStatis> selectByTradeDate(@Param("tradeDate")String tradeDate);
	
}
