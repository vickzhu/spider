package com.gesangwu.spider.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.SecCompanyMapper;
import com.gesangwu.spider.biz.dao.model.SecCompany;
import com.gesangwu.spider.biz.dao.model.SecCompanyExample;
import com.gesangwu.spider.biz.service.SecCompanyService;

@Service
public class SecCompanyServiceImpl extends BaseServiceImpl<SecCompany, SecCompanyExample> implements
		SecCompanyService {
	
	@Resource
	private SecCompanyMapper mapper;

	@Override
	protected BaseMapper<SecCompany, SecCompanyExample> getMapper() {
		return mapper;
	}

}
