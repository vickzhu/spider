package com.gesangwu.spider.biz.service;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.JdStatis;
import com.gesangwu.spider.biz.dao.model.JdStatisExample;

public interface JdStatisService extends BaseService<JdStatis, JdStatisExample> {
	
	public JdStatis selectBySymbolAndDate(String symbol, String tradeDate);
	
}
