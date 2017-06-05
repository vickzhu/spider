package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.KeyValue;
import com.gesangwu.spider.biz.dao.model.SynergyDetail;
import com.gesangwu.spider.biz.dao.model.SynergyDetailExample;

public interface SynergyDetailMapper extends BaseMapper<SynergyDetail, SynergyDetailExample> {
	
    void insertSynergyDetailBatch(List<SynergyDetail> list);
    
    List<KeyValue<Integer, Integer>> relateDept(List<String> list);
    
}