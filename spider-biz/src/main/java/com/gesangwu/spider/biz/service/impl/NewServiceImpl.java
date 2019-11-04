package com.gesangwu.spider.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.NewsMapper;
import com.gesangwu.spider.biz.dao.model.News;
import com.gesangwu.spider.biz.dao.model.NewsExample;
import com.gesangwu.spider.biz.service.NewsService;

@Service
public class NewServiceImpl extends BaseServiceImpl<News, NewsExample> implements
		NewsService {
	
	@Resource
	private NewsMapper mapper;

	@Override
	protected BaseMapper<News, NewsExample> getMapper() {
		return mapper;
	}

	

}
