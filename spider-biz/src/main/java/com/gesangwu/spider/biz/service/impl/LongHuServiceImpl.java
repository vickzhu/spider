package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.LongHuMapper;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuExample;
import com.gesangwu.spider.biz.service.LongHuService;

@Service
public class LongHuServiceImpl extends BaseServiceImpl<LongHu, LongHuExample>
		implements LongHuService {
	
	@Resource
	private LongHuMapper mapper;

	@Override
	protected BaseMapper<LongHu, LongHuExample> getMapper() {
		return mapper;
	}

	@Override
	public List<LongHu> selectByTradeDate(String tradeDate) {
		return mapper.selectByTradeDate(tradeDate);
	}

	@Override
	public LongHu selectBySymbolAndTradeDate(String symbol, String tradeDate) {
		LongHuExample example = new LongHuExample();
		LongHuExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateEqualTo(tradeDate);
		List<LongHu> longHuList = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(longHuList)?null:longHuList.get(0);
	}

	@Override
	public List<String> selectTradeDate(String symbol) {
		return mapper.selectTradeDate(symbol);
	}

	@Override
	public LongHu selectLatestBySymbol(String symbol) {
		LongHuExample example = new LongHuExample();
		example.setOffset(0);
		example.setRows(1);
		example.setOrderByClause("trade_date desc");
		LongHuExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		List<LongHu> longHuList = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(longHuList) ? null : longHuList.get(0);
	}

}
