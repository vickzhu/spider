package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.CliqueDept;
import com.gesangwu.spider.biz.dao.model.CliqueDeptExample;

public interface CliqueDeptService extends BaseService<CliqueDept, CliqueDeptExample> {
	
	public void selectByCliqueId(long cliqueId, Page<CliqueDept> page);
	
	public List<CliqueDept> selectByCliqueId(long cliqueId);
	
	public List<CliqueDept> selectByDeptCode(String deptCode);
	
}