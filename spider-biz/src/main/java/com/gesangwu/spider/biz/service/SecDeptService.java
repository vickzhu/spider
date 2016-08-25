package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.SecDept;
import com.gesangwu.spider.biz.dao.model.SecDeptExample;

public interface SecDeptService extends BaseService<SecDept, SecDeptExample> {

	public void batchInsert(List<SecDept> secDeptList);
	
}
