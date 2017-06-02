package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.SynergyMapper;
import com.gesangwu.spider.biz.dao.model.Synergy;
import com.gesangwu.spider.biz.dao.model.SynergyExample;
import com.gesangwu.spider.biz.service.SynergyService;

@Service
public class SynergyServiceImpl extends
		BaseServiceImpl<Synergy, SynergyExample> implements SynergyService {

	@Resource
	private SynergyMapper mapper;
	
	@Override
	protected BaseMapper<Synergy, SynergyExample> getMapper() {
		return mapper;
	}

	@Override
	public void insertSynergyBatch(List<Synergy> sList) {
		mapper.insertSynergyBatch(sList);		
	}

}
