package com.gesangwu.spider.biz.service;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.HolderNum;
import com.gesangwu.spider.biz.dao.model.HolderNumExample;

public interface HolderNumService extends BaseService<HolderNum, HolderNumExample> {
	
	public HolderNum selectLatestBySymbol(String symbol);
}
