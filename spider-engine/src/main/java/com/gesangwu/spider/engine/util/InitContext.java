package com.gesangwu.spider.engine.util;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.Province;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.ProvinceService;

@Component
public class InitContext implements InitializingBean {
	
	@Resource
	private CompanyService companyService;
	@Resource
	private ProvinceService provinceService;

	@Override
	public void afterPropertiesSet() throws Exception {
		List<Company> list = companyService.loadLittleCompany();
		LittleCompanyHolder.setCompanyList(list);
		
		List<Province> provinceList = provinceService.selectByExample(null);
		ProvinceHolder.setProvinceList(provinceList);		
	}

}
