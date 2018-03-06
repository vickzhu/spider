package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.Bidding;
import com.gesangwu.spider.biz.dao.model.BiddingExample;

public interface BiddingService extends BaseService<Bidding, BiddingExample> {

	public void batchInsert(List<Bidding> bdList);
	
	public void selectByPagination(BiddingExample example, Page<Bidding> page);
	
}
