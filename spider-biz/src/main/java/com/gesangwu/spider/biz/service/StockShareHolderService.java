package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.StockShareHolder;
import com.gesangwu.spider.biz.dao.model.StockShareHolderExample;
import com.gesangwu.spider.biz.dao.model.ext.StockShareHolderExt;

public interface StockShareHolderService extends BaseService<StockShareHolder, StockShareHolderExample> {

	public void insertBatch(List<StockShareHolder> sshList);
	
	public Double calcFloatRate(String symbol);
	
	public String selectLatestDate(String symbol);
	
	public List<StockShareHolder> selectByPersonalName(String name);
	
	public List<StockShareHolder> selectByShareHolder(Long holderId);
	
	public void selectCliqueByPagination(StockShareHolderExample example, Page<StockShareHolderExt> page);
	
	public List<StockShareHolderExt> selectLatestBySymbol(String symbol);
	
	public List<String> selectEndDate(String symbol);
	
	public List<StockShareHolderExt> selectByEndDate(String symbol, String endDate);
	
	public void selectStockByClique(StockShareHolderExample example, Page<StockShareHolderExt> page);
	
}
