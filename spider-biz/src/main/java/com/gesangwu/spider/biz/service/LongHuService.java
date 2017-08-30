package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuExample;

public interface LongHuService extends BaseService<LongHu, LongHuExample> {
	
	List<LongHu> selectByTradeDate(String tradeDate);
	
	LongHu selectBySymbolAndTradeDate(String symbol, String tradeDate);
	
	List<String> selectTradeDate(String symbol);
	
	LongHu selectLatestBySymbol(String symbol);
	
	void insertBatch(List<LongHu> longHuList);
	
	void insert(LongHu longHu, List<LongHuDetail> detailList);
	
	void analyzeClique(LongHu longhu);
	
	public void selectByPagination(LongHuExample example, Page<LongHu> page);
	
}
