package com.gesangwu.spider.engine.util;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.service.CompanyService;

@Component
public class InitContext implements InitializingBean {
	
	@Resource
	private CompanyService companyService;

//	public void init() {
//		List<Company> list = companyService.loadLittleCompany();
//		LittleCompanyHolder.setCompanyList(list);
//	}

	@Override
	public void afterPropertiesSet() throws Exception {
		List<Company> list = companyService.loadLittleCompany();
		LittleCompanyHolder.setCompanyList(list);
	}

}
