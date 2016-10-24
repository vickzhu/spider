package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.mapper.FiveRangeStatisMapper;
import com.gesangwu.spider.biz.dao.model.FiveRangeStatis;
import com.gesangwu.spider.biz.dao.model.FiveRangeStatisExample;
import com.gesangwu.spider.biz.service.FiveRangeStatisService;

@Service
public class FiveRangeStatisServiceImpl extends
		BaseServiceImpl<FiveRangeStatis, FiveRangeStatisExample> implements
		FiveRangeStatisService {
	
	@Resource
	private FiveRangeStatisMapper mapper;

	@Override
	protected BaseMapper<FiveRangeStatis, FiveRangeStatisExample> getMapper() {
		return mapper;
	}

	@Override
	public FiveRangeStatis selectBySymbolAndDate(String symbol, String date) {
		FiveRangeStatisExample example = new FiveRangeStatisExample();
		FiveRangeStatisExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateEqualTo(date);
		List<FiveRangeStatis> statisList = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(statisList)?null:statisList.get(0);
	}

	@Override
	public List<FiveRangeStatis> selectByTradeDate(String tradeDate) {
		return mapper.selectByTradeDate(tradeDate);
	}

	@Override
	public void selectByPagination(FiveRangeStatisExample example,
			Page<FiveRangeStatis> page) {
		example.setOffset(page.getOffset());
        example.setRows(page.getPageSize());
        int totalCounts = mapper.countByExample(example);
        page.setTotalCounts(totalCounts);
        List<FiveRangeStatis> companyList = mapper.selectByExample(example);
        page.setRecords(companyList);	
		
	}

}
