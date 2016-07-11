package com.gesangwu.spider.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.LargeVolMapper;
import com.gesangwu.spider.biz.dao.model.LargeVol;
import com.gesangwu.spider.biz.dao.model.LargeVolExample;
import com.gesangwu.spider.biz.service.LargeVolService;

@Service
public class LargeVolServiceImpl extends BaseServiceImpl<LargeVol, LargeVolExample> implements
		LargeVolService {
	
	@Resource
	private LargeVolMapper mapper;

	@Override
	protected BaseMapper<LargeVol, LargeVolExample> getMapper() {
		return mapper;
	}
	
}
