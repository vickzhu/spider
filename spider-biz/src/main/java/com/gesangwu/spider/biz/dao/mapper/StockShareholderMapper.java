package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.StockShareholder;
import com.gesangwu.spider.biz.dao.model.StockShareholderExample;

public interface StockShareholderMapper extends BaseMapper<StockShareholder, StockShareholderExample> {
	
	void insertBatch(List<StockShareholder> sshList);
	
	double calcFloatRate(String symbol);
	
}