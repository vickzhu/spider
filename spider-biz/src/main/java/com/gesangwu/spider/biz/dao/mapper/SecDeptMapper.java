package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.SecDept;
import com.gesangwu.spider.biz.dao.model.SecDeptExample;

public interface SecDeptMapper extends BaseMapper<SecDept, SecDeptExample> {
	
	void insertBatch(List<SecDept> secDeptList);
	
	void clearActiveDept();
	
	void updateActiveDept(@Param("startDate")String startDate);
	
}