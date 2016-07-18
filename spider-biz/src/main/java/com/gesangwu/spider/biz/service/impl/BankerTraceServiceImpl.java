package com.gesangwu.spider.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.BankerTraceMapper;
import com.gesangwu.spider.biz.dao.model.BankerTrace;
import com.gesangwu.spider.biz.dao.model.BankerTraceExample;
import com.gesangwu.spider.biz.service.BankerTraceService;

@Service
public class BankerTraceServiceImpl extends BaseServiceImpl<BankerTrace, BankerTraceExample> implements
		BankerTraceService {
	
	@Resource
	private BankerTraceMapper mapper;

	@Override
	protected BaseMapper<BankerTrace, BankerTraceExample> getMapper() {
		return mapper;
	}

}
