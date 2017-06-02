package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.Synergy;
import com.gesangwu.spider.biz.dao.model.SynergyExample;

public interface SynergyMapper extends BaseMapper<Synergy, SynergyExample> {
    
	void insertSynergyBatch(List<Synergy> list);
	
}