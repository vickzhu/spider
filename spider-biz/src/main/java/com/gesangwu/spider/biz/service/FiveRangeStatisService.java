package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.FiveRangeStatis;
import com.gesangwu.spider.biz.dao.model.FiveRangeStatisExample;

public interface FiveRangeStatisService extends BaseService<FiveRangeStatis, FiveRangeStatisExample> {

	public FiveRangeStatis selectBySymbolAndDate(String symbol, String date);
	
	List<FiveRangeStatis> selectByTradeDate(String tradeDate);
	
	public void selectByPagination(FiveRangeStatisExample example, Page<FiveRangeStatis> page);
}
