package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.CompanyExample;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.test.BaseTest;

public class CompanyServiceTest extends BaseTest {
	
	@Resource
	private CompanyService companyService;

	@Test
	public void testSelectAll(){
		companyService.selectByExample(null);
	}
	
//	@Test
	public void testSelectByPagination(){
		int cpp = 10;
		int count = companyService.countByExample(null);
		int totalPages = (count + cpp -1)/cpp;
		for(int cur = 1; cur<=totalPages; cur++){
			System.out.println(cur);
			Page<Company> page = new Page<Company>(cur);
			companyService.selectByPagination(new CompanyExample(), page);
		}		
	}
}
