package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.Synergy;
import com.gesangwu.spider.biz.dao.model.SynergyExample;

public interface SynergyService extends BaseService<Synergy, SynergyExample> {

	public void insertSynergyBatch(List<Synergy> sList);
	
}
