package com.gesangwu.spider.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.ActiveDeptOperationMapper;
import com.gesangwu.spider.biz.dao.model.ActiveDeptOperation;
import com.gesangwu.spider.biz.dao.model.ActiveDeptOperationExample;
import com.gesangwu.spider.biz.service.ActiveDeptOperationService;

@Service
public class ActiveDeptOperationServiceImpl extends
		BaseServiceImpl<ActiveDeptOperation, ActiveDeptOperationExample>
		implements ActiveDeptOperationService {
	
	@Resource
	private ActiveDeptOperationMapper mapper;

	@Override
	protected BaseMapper<ActiveDeptOperation, ActiveDeptOperationExample> getMapper() {
		return mapper;
	}

}
