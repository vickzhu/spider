package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.JdStatisMapper;
import com.gesangwu.spider.biz.dao.model.JdStatis;
import com.gesangwu.spider.biz.dao.model.JdStatisExample;
import com.gesangwu.spider.biz.service.JdStatisService;

@Service
public class JdStatisServiceImpl extends BaseServiceImpl<JdStatis, JdStatisExample> implements
		JdStatisService {
	
	@Resource
	private JdStatisMapper mapper;

	@Override
	protected BaseMapper<JdStatis, JdStatisExample> getMapper() {
		return mapper;
	}

	@Override
	public JdStatis selectBySymbolAndDate(String symbol, String tradeDate) {
		JdStatisExample example = new JdStatisExample();
		JdStatisExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateEqualTo(tradeDate);
		List<JdStatis> jdList = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(jdList) ? null : jdList.get(0);
	}

}
