package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.StockShareHolder;
import com.gesangwu.spider.biz.dao.model.StockShareHolderExample;
import com.gesangwu.spider.biz.dao.model.ext.StockShareHolderExt;

public interface StockShareHolderMapper extends BaseMapper<StockShareHolder, StockShareHolderExample> {

	void insertBatch(List<StockShareHolder> sshList);
	
	Double calcFloatRate(String symbol);
	
	String selectLatestDate(String symbol);
	
	List<StockShareHolder> selectByPersonalName(String name);
	
	List<StockShareHolderExt> selectCliqueByExample(StockShareHolderExample example);
	
	List<StockShareHolderExt> selectLatestBySymbol(String symbol);
	
}