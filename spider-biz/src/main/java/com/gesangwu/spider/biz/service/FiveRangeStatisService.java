package com.gesangwu.spider.biz.service;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.FiveRangeStatis;
import com.gesangwu.spider.biz.dao.model.FiveRangeStatisExample;

public interface FiveRangeStatisService extends BaseService<FiveRangeStatis, FiveRangeStatisExample> {

	public FiveRangeStatis selectBySymbolAndDate(String symbol, String date);
}
