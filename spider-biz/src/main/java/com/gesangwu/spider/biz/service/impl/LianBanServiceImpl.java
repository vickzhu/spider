package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.common.LianBanStatus;
import com.gesangwu.spider.biz.dao.mapper.LianBanMapper;
import com.gesangwu.spider.biz.dao.model.LianBan;
import com.gesangwu.spider.biz.dao.model.LianBanExample;
import com.gesangwu.spider.biz.service.LianBanService;

@Service
public class LianBanServiceImpl extends BaseServiceImpl<LianBan, LianBanExample> implements
		LianBanService {
	
	@Resource
	private LianBanMapper mapper;

	@Override
	protected BaseMapper<LianBan, LianBanExample> getMapper() {
		return mapper;
	}

	@Override
	public void batchInsert(List<LianBan> lbList) {
		mapper.batchInsert(lbList);
	}

	@Override
	public LianBan selectByTradeDate(String symbol, String tradeDate) {
		LianBanExample example = new LianBanExample();
		LianBanExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateEqualTo(tradeDate);
		List<LianBan> lbList = mapper.selectByExample(example);
		return CollectionUtils.isNotEmpty(lbList) ? lbList.get(0) : null;
	}

	@Override
	public List<LianBan> getMaxByDate(String startDate, String endDate) {
		return mapper.getMaxByDate(startDate, endDate);
	}
	
	@Override
	public int ztCount(String tradeDate) {
		LianBanExample example = new LianBanExample();
		LianBanExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		criteria.andStatusEqualTo(LianBanStatus.ZT.getCode());
		return mapper.countByExample(example);
	}

}
