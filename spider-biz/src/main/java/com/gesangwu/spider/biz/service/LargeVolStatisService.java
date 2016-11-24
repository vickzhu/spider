package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.LargeVolStatis;
import com.gesangwu.spider.biz.dao.model.LargeVolStatisExample;

public interface LargeVolStatisService extends BaseService<LargeVolStatis, LargeVolStatisExample> {

	public LargeVolStatis selectBySymbolAndDate(String symbol, String tradeDate);
	
	public List<LargeVolStatis> selectByTradeDate(String tradeDate, double minAmv, double maxAmv);
	
	public void selectByPagination(LargeVolStatisExample example, Page<LargeVolStatis> lvsPage);
	
}
