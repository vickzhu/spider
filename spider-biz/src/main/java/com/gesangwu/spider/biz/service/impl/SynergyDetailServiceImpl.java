package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gandalf.framework.mybatis.KeyValue;
import com.gesangwu.spider.biz.dao.mapper.SynergyDetailMapper;
import com.gesangwu.spider.biz.dao.model.SynergyDetail;
import com.gesangwu.spider.biz.dao.model.SynergyDetailExample;
import com.gesangwu.spider.biz.service.SynergyDetailService;

@Service
public class SynergyDetailServiceImpl extends
		BaseServiceImpl<SynergyDetail, SynergyDetailExample> implements
		SynergyDetailService {

	@Resource
	private SynergyDetailMapper mapper;
	
	@Override
	protected BaseMapper<SynergyDetail, SynergyDetailExample> getMapper() {
		return mapper;
	}

	@Override
	public void insertSynergyDetailBatch(List<SynergyDetail> sdList) {
		mapper.insertSynergyDetailBatch(sdList);		
	}

	@Override
	public List<KeyValue<Integer, Integer>> relateDept(List<String> list) {
		return mapper.relateDept(list);
	}

}
