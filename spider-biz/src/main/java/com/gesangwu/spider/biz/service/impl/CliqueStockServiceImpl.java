package com.gesangwu.spider.biz.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.mapper.CliqueStockMapper;
import com.gesangwu.spider.biz.dao.model.CliqueDeptExample;
import com.gesangwu.spider.biz.dao.model.CliqueStock;
import com.gesangwu.spider.biz.dao.model.CliqueStockExample;
import com.gesangwu.spider.biz.service.CliqueStockService;

@Service
public class CliqueStockServiceImpl extends BaseServiceImpl<CliqueStock, CliqueStockExample> implements
		CliqueStockService {

	@Resource
	private CliqueStockMapper mapper;
	
	@Override
	protected BaseMapper<CliqueStock, CliqueStockExample> getMapper() {
		return mapper;
	}

	@Override
	public void selectByCliqueId(long cliqueId, Page<CliqueStock> page) {
		CliqueStockExample example = new CliqueStockExample();
		example.setOffset(page.getOffset());
        example.setRows(page.getPageSize());
        example.setOrderByClause("id desc");
        CliqueStockExample.Criteria criteria = example.createCriteria();
        criteria.andCliqueIdEqualTo(cliqueId);
        int totalCounts = mapper.countByExample(example);
        page.setTotalCounts(totalCounts);
        page.setRecords(mapper.selectByExample(example));
	}	

}
