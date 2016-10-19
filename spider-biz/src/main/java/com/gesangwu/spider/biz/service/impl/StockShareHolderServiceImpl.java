package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.StockShareHolderMapper;
import com.gesangwu.spider.biz.dao.model.StockShareHolder;
import com.gesangwu.spider.biz.dao.model.StockShareHolderExample;
import com.gesangwu.spider.biz.service.StockShareHolderService;

@Service
public class StockShareHolderServiceImpl extends BaseServiceImpl<StockShareHolder, StockShareHolderExample>
		implements StockShareHolderService {
	
	@Resource
	private StockShareHolderMapper mapper;

	@Override
	protected BaseMapper<StockShareHolder, StockShareHolderExample> getMapper() {
		return mapper;
	}

	@Override
	public void insertBatch(List<StockShareHolder> sshList) {
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
