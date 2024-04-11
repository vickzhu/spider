package com.gesangwu.spider.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.HuiCheDetailMapper;
import com.gesangwu.spider.biz.dao.model.HuiCheDetail;
import com.gesangwu.spider.biz.dao.model.HuiCheDetailExample;
import com.gesangwu.spider.biz.service.HuiCheDetailService;

@Service
public class HuiCheDetailServiceImpl extends BaseServiceImpl<HuiCheDetail, HuiCheDetailExample> implements HuiCheDetailService {
	
	@Resource
	private HuiCheDetailMapper mapper;

	@Override
	protected BaseMapper<HuiCheDetail, HuiCheDetailExample> getMapper() {
		return mapper;
	}
	
}
