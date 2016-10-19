package com.gesangwu.spider.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.StockShareholderMapper;
import com.gesangwu.spider.biz.dao.model.StockShareholder;
import com.gesangwu.spider.biz.dao.model.StockShareholderExample;
import com.gesangwu.spider.biz.service.StockShareholderService;

@Service
public class StockShareholderServiceImpl extends BaseServiceImpl<StockShareholder, StockShareholderExample>
		implements StockShareholderService {
	
	@Resource
	private StockShareholderMapper mapper;

	@Override
	protected BaseMapper<StockShareholder, StockShareholderExample> getMapper() {
		return mapper;
	}

}
