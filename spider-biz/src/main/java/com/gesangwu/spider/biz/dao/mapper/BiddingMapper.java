package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.Bidding;
import com.gesangwu.spider.biz.dao.model.BiddingExample;

public interface BiddingMapper extends BaseMapper<Bidding, BiddingExample> {
    
	public void batchInsert(List<Bidding> bdList);
	
}