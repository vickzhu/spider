package com.gesangwu.spider.biz.service;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.UpperShadow;
import com.gesangwu.spider.biz.dao.model.UpperShadowExample;

public interface UpperShadowService extends BaseService<UpperShadow, UpperShadowExample> {

	public void selectByPagination(UpperShadowExample example, Page<UpperShadow> page);
	
}
