package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.KLineStatis;
import com.gesangwu.spider.biz.dao.model.KLineStatisExample;

public interface KLineStatisService extends BaseService<KLineStatis, KLineStatisExample> {

	public KLineStatis aou(String tradeDate);
	
	public List<KLineStatis> getList(String startDate, String endDate);
	
}
