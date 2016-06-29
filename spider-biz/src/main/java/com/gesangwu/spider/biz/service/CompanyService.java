package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.CompanyExample;

public interface CompanyService extends BaseService<Company, CompanyExample> {
	
	public void batchInsert(List<Company> companyList);
	
	public void selectByPagination(CompanyExample example, Page<Company> page);
	
}
