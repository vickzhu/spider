package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.mapper.BiddingMapper;
import com.gesangwu.spider.biz.dao.model.Bidding;
import com.gesangwu.spider.biz.dao.model.BiddingExample;
import com.gesangwu.spider.biz.service.BiddingService;

@Service
public class BiddingServicesImpl extends BaseServiceImpl<Bidding, BiddingExample> implements
		BiddingService {

	@Resource
	private BiddingMapper mapper;
	
	@Override
	protected BaseMapper<Bidding, BiddingExample> getMapper() {
		return mapper;
	}

	@Override
	public void batchInsert(List<Bidding> bdList) {
		mapper.batchInsert(bdList);		
	}

	@Override
	public void selectByPagination(BiddingExample example, Page<Bidding> page) {
		example.setOffset(page.getOffset());
        example.setRows(page.getPageSize());
        int totalCounts = mapper.countByExample(example);
        page.setTotalCounts(totalCounts);
        List<Bidding> biddingList = mapper.selectByExample(example);
        page.setRecords(biddingList);
	}

}
