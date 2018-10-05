package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.Holiday;
import com.gesangwu.spider.biz.dao.model.HolidayExample;

public interface HolidayService extends BaseService<Holiday, HolidayExample> {
	
	public List<String> selectByYear(String year);
	
}
