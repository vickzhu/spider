package com.gesangwu.spider.biz.service;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.dao.model.LongHuTypeExample;

public interface LongHuTypeService extends BaseService<LongHuType, LongHuTypeExample> {

	public LongHuType selectByType(String type);
	
}
