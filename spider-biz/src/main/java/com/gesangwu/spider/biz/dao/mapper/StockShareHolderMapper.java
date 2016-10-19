package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.StockShareHolder;
import com.gesangwu.spider.biz.dao.model.StockShareHolderExample;

public interface StockShareHolderMapper extends BaseMapper<StockShareHolder, StockShareHolderExample> {

	void insertBatch(List<StockShareHolder> sshList);
	
	double calcFloatRate(String symbol);
	
}