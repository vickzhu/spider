package com.gesangwu.spider.engine.task;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.engine.util.LittleCompanyHolder;

/**
 * 小市值公司初始化
 * 每天早上8点执行一次
 * @author zhuxb
 *
 */
@Component
public class LittleCompanyInit {

	@Resource
	private CompanyService companyService;
	
	@Scheduled(cron = "0 0 8 * * MON-FRI")
	public void load(){
		List<Company> list = companyService.loadLittleCompany();
		LittleCompanyHolder.setCompanyList(list);
	}
	
}
