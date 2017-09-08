package com.gesangwu.spider.biz.service;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.ActiveDeptOperation;
import com.gesangwu.spider.biz.dao.model.ActiveDeptOperationExample;

public interface ActiveDeptOperationService extends
		BaseService<ActiveDeptOperation, ActiveDeptOperationExample> {
	
	public ActiveDeptOperation selectByTradeDate(String tradeDate);

}
