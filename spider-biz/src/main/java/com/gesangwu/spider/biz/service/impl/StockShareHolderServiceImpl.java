package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.mapper.StockShareHolderMapper;
import com.gesangwu.spider.biz.dao.model.StockShareHolder;
import com.gesangwu.spider.biz.dao.model.StockShareHolderExample;
import com.gesangwu.spider.biz.dao.model.ext.StockShareHolderExt;
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

	@Override
	public String selectLatestDate(String symbol) {
		return mapper.selectLatestDate(symbol);
	}

	@Override
	public List<StockShareHolder> selectByPersonalName(String name) {
		return mapper.selectByPersonalName(name);
	}

	@Override
	public List<StockShareHolder> selectByShareHolder(Long holderId) {
		StockShareHolderExample example = new StockShareHolderExample();
		example.setOrderByClause("end_date desc");;
		StockShareHolderExample.Criteria criteria = example.createCriteria();
		criteria.andShareholderEqualTo(holderId);
		return mapper.selectByExample(example);
	}

	@Override
	public void selectCliqueByPagination(StockShareHolderExample example,
			Page<StockShareHolderExt> page) {
		example.setOffset(page.getOffset());
        example.setRows(page.getPageSize());
        int totalCounts = mapper.countByExample(example);
        page.setTotalCounts(totalCounts);
        List<StockShareHolderExt> companyList = mapper.selectCliqueByExample(example);
        page.setRecords(companyList);
		
	}

	@Override
	public List<StockShareHolderExt> selectLatestBySymbol(String symbol) {
		return mapper.selectLatestBySymbol(symbol);
	}

}
