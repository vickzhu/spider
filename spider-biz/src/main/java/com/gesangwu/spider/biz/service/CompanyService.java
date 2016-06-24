package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.CompanyExample;

public interface CompanyService extends BaseService<Company, CompanyExample> {
	
	public void batchInsert(List<Company> companyList);
	
}
