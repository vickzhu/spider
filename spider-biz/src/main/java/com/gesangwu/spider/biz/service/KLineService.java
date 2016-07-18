package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;

public interface KLineService extends BaseService<KLine, KLineExample> {
	
	public void batchInsert(List<KLine> kLineList);
	
	public void selectByPagination(KLineExample example, Page<KLine> page);
	
}
