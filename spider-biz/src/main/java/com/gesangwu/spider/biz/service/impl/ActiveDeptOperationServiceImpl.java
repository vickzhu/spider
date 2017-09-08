package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.ActiveDeptOperationMapper;
import com.gesangwu.spider.biz.dao.model.ActiveDeptOperation;
import com.gesangwu.spider.biz.dao.model.ActiveDeptOperationExample;
import com.gesangwu.spider.biz.service.ActiveDeptOperationService;

@Service
public class ActiveDeptOperationServiceImpl extends
		BaseServiceImpl<ActiveDeptOperation, ActiveDeptOperationExample>
		implements ActiveDeptOperationService {
	
	@Resource
	private ActiveDeptOperationMapper mapper;

	@Override
	protected BaseMapper<ActiveDeptOperation, ActiveDeptOperationExample> getMapper() {
		return mapper;
	}

	@Override
	public ActiveDeptOperation selectByTradeDate(String tradeDate) {
		ActiveDeptOperationExample example = new ActiveDeptOperationExample();
		ActiveDeptOperationExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		List<ActiveDeptOperation> adoList = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(adoList) ? null : adoList.get(0);
	}

}
