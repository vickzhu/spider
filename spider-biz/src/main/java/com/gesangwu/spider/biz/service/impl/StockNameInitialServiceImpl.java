package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.StockNameInitialMapper;
import com.gesangwu.spider.biz.dao.model.StockNameInitial;
import com.gesangwu.spider.biz.dao.model.StockNameInitialExample;
import com.gesangwu.spider.biz.service.StockNameInitialService;

@Service
public class StockNameInitialServiceImpl extends BaseServiceImpl<StockNameInitial, StockNameInitialExample> implements StockNameInitialService {

	@Resource
	private StockNameInitialMapper mapper;
	
	@Override
	protected BaseMapper<StockNameInitial, StockNameInitialExample> getMapper() {
		return mapper;
	}

	@Override
	public void batchInsert(List<StockNameInitial> initialList) {
		mapper.batchInsert(initialList);
	}

}
