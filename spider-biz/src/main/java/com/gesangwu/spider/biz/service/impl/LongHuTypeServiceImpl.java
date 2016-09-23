package com.gesangwu.spider.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.LongHuTypeMapper;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.dao.model.LongHuTypeExample;
import com.gesangwu.spider.biz.service.LongHuTypeService;

@Service
public class LongHuTypeServiceImpl extends BaseServiceImpl<LongHuType, LongHuTypeExample> implements LongHuTypeService {
	
	@Resource
	private LongHuTypeMapper mapper;

	@Override
	protected BaseMapper<LongHuType, LongHuTypeExample> getMapper() {
		return mapper;
	}

}
