package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.BigVolStatisMapper;
import com.gesangwu.spider.biz.dao.model.BigVolStatis;
import com.gesangwu.spider.biz.dao.model.BigVolStatisExample;
import com.gesangwu.spider.biz.service.BigVolStatisService;

@Service
public class BigVolStatisServiceImpl extends BaseServiceImpl<BigVolStatis, BigVolStatisExample> implements
		BigVolStatisService {
	
	@Resource
	private BigVolStatisMapper mapper;

	@Override
	protected BaseMapper<BigVolStatis, BigVolStatisExample> getMapper() {
		return mapper;
	}

	@Override
	public BigVolStatis selectBySymbolAndDate(String symbol, String date) {
		BigVolStatisExample example = new BigVolStatisExample();
		BigVolStatisExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andDateEqualTo(date);
		List<BigVolStatis> list = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(list)?null:list.get(0);
	}

}
