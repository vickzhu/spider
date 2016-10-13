package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.CliqueDept;
import com.gesangwu.spider.biz.dao.model.CliqueDeptExample;
import com.gesangwu.spider.biz.dao.model.ext.CliqueDeptExt;

public interface CliqueDeptMapper extends BaseMapper<CliqueDept, CliqueDeptExample> {
	
	List<CliqueDeptExt> selectExtByExample(CliqueDeptExample example);
	
}