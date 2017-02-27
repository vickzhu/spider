package com.gesangwu.spider.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.StockDiaryMapper;
import com.gesangwu.spider.biz.dao.model.StockDiary;
import com.gesangwu.spider.biz.dao.model.StockDiaryExample;
import com.gesangwu.spider.biz.service.StockDiaryService;

@Service
public class StockDiaryServiceImpl extends
		BaseServiceImpl<StockDiary, StockDiaryExample> implements
		StockDiaryService {
	
	@Resource
	private StockDiaryMapper mapper;

	@Override
	protected BaseMapper<StockDiary, StockDiaryExample> getMapper() {
		return mapper;
	}

}
