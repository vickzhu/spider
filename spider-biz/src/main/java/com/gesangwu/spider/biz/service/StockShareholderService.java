package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.StockShareholder;
import com.gesangwu.spider.biz.dao.model.StockShareholderExample;

public interface StockShareholderService extends BaseService<StockShareholder, StockShareholderExample> {

	public void insertBatch(List<StockShareholder> sshList);
	
	public Double calcFloatRate(String symbol);
	
}
