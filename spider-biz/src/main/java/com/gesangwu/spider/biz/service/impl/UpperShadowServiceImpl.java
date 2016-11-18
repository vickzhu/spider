package com.gesangwu.spider.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.UpperShadowMapper;
import com.gesangwu.spider.biz.dao.model.UpperShadow;
import com.gesangwu.spider.biz.dao.model.UpperShadowExample;
import com.gesangwu.spider.biz.service.UpperShadowService;

@Service
public class UpperShadowServiceImpl extends BaseServiceImpl<UpperShadow, UpperShadowExample> implements
		UpperShadowService {

	@Resource
	private UpperShadowMapper mapper;
	
	@Override
	protected BaseMapper<UpperShadow, UpperShadowExample> getMapper() {
		return mapper;
	}

}
