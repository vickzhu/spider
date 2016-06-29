package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.CompanyExample;

public interface CompanyMapper extends BaseMapper<Company, CompanyExample> {
    
	void insertCompanyBatch(List<Company> companyList);
	
}