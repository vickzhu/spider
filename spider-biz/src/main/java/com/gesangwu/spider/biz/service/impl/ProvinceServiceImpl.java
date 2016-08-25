package com.gesangwu.spider.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.ProvinceMapper;
import com.gesangwu.spider.biz.dao.model.Province;
import com.gesangwu.spider.biz.dao.model.ProvinceExample;
import com.gesangwu.spider.biz.service.ProvinceService;

@Service
public class ProvinceServiceImpl extends BaseServiceImpl<Province, ProvinceExample> implements
		ProvinceService {

	@Resource
	private ProvinceMapper mapper;
	
	@Override
	protected BaseMapper<Province, ProvinceExample> getMapper() {
		return mapper;
	}

}
