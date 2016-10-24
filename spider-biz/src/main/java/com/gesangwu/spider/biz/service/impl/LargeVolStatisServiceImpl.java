package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.mapper.LargeVolStatisMapper;
import com.gesangwu.spider.biz.dao.model.LargeVolStatis;
import com.gesangwu.spider.biz.dao.model.LargeVolStatisExample;
import com.gesangwu.spider.biz.service.LargeVolStatisService;

@Service
public class LargeVolStatisServiceImpl extends BaseServiceImpl<LargeVolStatis, LargeVolStatisExample> implements
		LargeVolStatisService {
	
	@Resource
	private LargeVolStatisMapper mapper;

	@Override
	protected BaseMapper<LargeVolStatis, LargeVolStatisExample> getMapper() {
		return mapper;
	}
	
	@Override
	public LargeVolStatis selectBySymbolAndDate(String symbol, String tradeDate) {
		LargeVolStatisExample example = new LargeVolStatisExample();
		LargeVolStatisExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateEqualTo(tradeDate);
		List<LargeVolStatis> list = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(list)?null:list.get(0);
	}

	@Override
	public List<LargeVolStatis> selectByTradeDate(String tradeDate) {
		return mapper.selectByTradeDate(tradeDate);
	}

	@Override
	public void selectByPagination(LargeVolStatisExample example,
			Page<LargeVolStatis> page) {
		example.setOffset(page.getOffset());
        example.setRows(page.getPageSize());
        int totalCounts = mapper.countByExample(example);
        page.setTotalCounts(totalCounts);
        List<LargeVolStatis> companyList = mapper.selectByExample(example);
        page.setRecords(companyList);		
	}

}
