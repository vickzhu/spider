package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.StockShareholderMapper;
import com.gesangwu.spider.biz.dao.model.StockShareholder;
import com.gesangwu.spider.biz.dao.model.StockShareholderExample;
import com.gesangwu.spider.biz.service.StockShareholderService;

@Service
public class StockShareholderServiceImpl extends BaseServiceImpl<StockShareholder, StockShareholderExample>
		implements StockShareholderService {
	
	@Resource
	private StockShareholderMapper mapper;

	@Override
	protected BaseMapper<StockShareholder, StockShareholderExample> getMapper() {
		return mapper;
	}

	@Override
	public void insertBatch(List<StockShareholder> sshList) {
		if(CollectionUtils.isEmpty(sshList)){
			return;
		}
		mapper.insertBatch(sshList);		
	}

	@Override
	public Double calcFloatRate(String symbol) {
		return mapper.calcFloatRate(symbol);
	}

}
