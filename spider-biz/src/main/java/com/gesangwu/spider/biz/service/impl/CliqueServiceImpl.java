package com.gesangwu.spider.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.CliqueMapper;
import com.gesangwu.spider.biz.dao.model.Clique;
import com.gesangwu.spider.biz.dao.model.CliqueExample;
import com.gesangwu.spider.biz.service.CliqueService;

@Service
public class CliqueServiceImpl extends BaseServiceImpl<Clique, CliqueExample> implements
		CliqueService {

	@Resource
	private CliqueMapper mapper;
	
	@Override
	protected BaseMapper<Clique, CliqueExample> getMapper() {
		return mapper;
	}

	
}
