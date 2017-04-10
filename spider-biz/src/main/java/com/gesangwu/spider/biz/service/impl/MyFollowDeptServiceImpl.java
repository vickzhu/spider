package com.gesangwu.spider.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.MyFollowDeptMapper;
import com.gesangwu.spider.biz.dao.model.MyFollowDept;
import com.gesangwu.spider.biz.dao.model.MyFollowDeptExample;
import com.gesangwu.spider.biz.service.MyFollowDeptService;

@Service
public class MyFollowDeptServiceImpl extends
		BaseServiceImpl<MyFollowDept, MyFollowDeptExample> implements
		MyFollowDeptService {
	
	@Resource
	private MyFollowDeptMapper mapper;

	@Override
	protected BaseMapper<MyFollowDept, MyFollowDeptExample> getMapper() {
		return mapper;
	}

}
