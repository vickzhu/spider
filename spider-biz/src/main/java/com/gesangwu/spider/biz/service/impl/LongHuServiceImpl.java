package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

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

}
