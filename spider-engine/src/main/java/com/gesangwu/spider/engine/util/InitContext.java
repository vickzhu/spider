package com.gesangwu.spider.engine.util;

import java.util.List;

import javax.annotation.Resource;

import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.service.CompanyService;

public class InitContext {
	
	@Resource
	private CompanyService companyService;

	public void init() {
		List<Company> list = companyService.loadLittleCompany();
		LittleCompanyHolder.setCompanyList(list);
	}

}
