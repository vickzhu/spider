package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.StockNameInitial;
import com.gesangwu.spider.biz.dao.model.StockNameInitialExample;

public interface StockNameInitialService extends BaseService<StockNameInitial, StockNameInitialExample> {
	
	public void batchInsert(List<StockNameInitial> initialList);
	
}
