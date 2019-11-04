package com.gesangwu.spider.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.NewsTypeMapper;
import com.gesangwu.spider.biz.dao.model.NewsType;
import com.gesangwu.spider.biz.dao.model.NewsTypeExample;
import com.gesangwu.spider.biz.service.NewsTypeService;

@Service
public class NewsTypeServiceImpl extends BaseServiceImpl<NewsType, NewsTypeExample> implements
		NewsTypeService {
	
	@Resource
	private NewsTypeMapper mapper;

	@Override
	protected BaseMapper<NewsType, NewsTypeExample> getMapper() {
		return mapper;
	}

}
