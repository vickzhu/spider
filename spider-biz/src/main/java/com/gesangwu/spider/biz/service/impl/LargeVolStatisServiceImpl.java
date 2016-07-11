package com.gesangwu.spider.biz.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.LargeVolStatisMapper;
import com.gesangwu.spider.biz.dao.model.LargeVolStatis;
import com.gesangwu.spider.biz.dao.model.LargeVolStatisExample;
import com.gesangwu.spider.biz.service.LargeVolStatisService;

@Service
public class LargeVolStatisServiceImpl extends BaseServiceImpl<LargeVolStatis, LargeVolStatisExample> implements
		LargeVolStatisService {
	
	@Resource
	private LargeVolStatisMapper mapper;

	@Override
	protected BaseMapper<LargeVolStatis, LargeVolStatisExample> getMapper() {
		return mapper;
	}
	
	@Override
	public LargeVolStatis selectBySymbolAndDate(String symbol, Date date) {
		LargeVolStatisExample example = new LargeVolStatisExample();
		LargeVolStatisExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andDateEqualTo(date);
		List<LargeVolStatis> list = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(list)?null:list.get(0);
	}

}
