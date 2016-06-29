package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.mapper.CompanyMapper;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.CompanyExample;
import com.gesangwu.spider.biz.service.CompanyService;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company, CompanyExample> implements
		CompanyService {
	
	@Resource
	private CompanyMapper mapper;

	@Override
	protected BaseMapper<Company, CompanyExample> getMapper() {
		return mapper;
	}

	@Override
	public void batchInsert(List<Company> companyList) {
		mapper.insertCompanyBatch(companyList);		
	}

	@Override
	public void selectByPagination(CompanyExample example, Page<Company> page) {
		example.setOffset(page.getOffset());
        example.setRows(page.getPageSize());
        int totalCounts = mapper.countByExample(example);
        page.setTotalCounts(totalCounts);
        List<Company> companyList = mapper.selectByExample(example);
        page.setRecords(companyList);
	}

}
