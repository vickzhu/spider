package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.StockNameInitial;
import com.gesangwu.spider.biz.dao.model.StockNameInitialExample;

public interface StockNameInitialMapper extends BaseMapper<StockNameInitial, StockNameInitialExample> {
	
	public void batchInsert(List<StockNameInitial> initialList);
	
}