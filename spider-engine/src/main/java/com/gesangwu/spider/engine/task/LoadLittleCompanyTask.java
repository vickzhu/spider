package com.gesangwu.spider.engine.task;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.engine.util.LittleCompanyHolder;

/**
 * 加载小市值企业
 * @author zhuxb
 *
 */
//@Component
public class LoadLittleCompanyTask {
	
	@Resource
	private CompanyService companyService;
	
	@Scheduled(cron = "0 25 9 * * MON-FRI")
	public void load(){
		List<Company> list = companyService.loadLittleCompany();
		LittleCompanyHolder.setCompanyList(list);
	}
}
