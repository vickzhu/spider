package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.KLineStatisMapper;
import com.gesangwu.spider.biz.dao.model.KLineStatis;
import com.gesangwu.spider.biz.dao.model.KLineStatisExample;
import com.gesangwu.spider.biz.service.KLineStatisService;

@Service
public class KLineStatisServiceImpl extends BaseServiceImpl<KLineStatis, KLineStatisExample> implements KLineStatisService {

	@Resource
	private KLineStatisMapper mapper;

	@Override
	protected BaseMapper<KLineStatis, KLineStatisExample> getMapper() {
		return mapper;
	}

	@Override
	public KLineStatis aou(String tradeDate) {
		KLineStatisExample example = new KLineStatisExample();
		KLineStatisExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		List<KLineStatis> list = mapper.selectByExample(example);
		if(list == null || list.size() == 0) {
			KLineStatis ks = new KLineStatis();
			ks.setTradeDate(tradeDate);
			mapper.insertSelective(ks);
			return ks;
		} else {			
			return list.get(0);
		}
	}

	@Override
	public List<KLineStatis> getList(String startDate, String endDate) {
		KLineStatisExample example = new KLineStatisExample();
		KLineStatisExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateGreaterThanOrEqualTo(startDate);
		criteria.andTradeDateLessThanOrEqualTo(endDate);
		return mapper.selectByExample(example);
	}

}
