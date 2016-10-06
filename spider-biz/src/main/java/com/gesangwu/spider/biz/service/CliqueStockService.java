package com.gesangwu.spider.biz.service;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.CliqueStock;
import com.gesangwu.spider.biz.dao.model.CliqueStockExample;

public interface CliqueStockService extends BaseService<CliqueStock, CliqueStockExample> {
	
	public void selectByCliqueId(long cliqueId, Page<CliqueStock> page);
	
}
