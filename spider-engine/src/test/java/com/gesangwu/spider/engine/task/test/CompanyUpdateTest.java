package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.CompanyUpdateTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class CompanyUpdateTest extends BaseTest {
	
	@Resource
	private CompanyUpdateTask companyUpdate;
//	@Resource
//	private StCompanyUpdateTask companyUpdate;

	@Test
	public void test(){
		companyUpdate.execute();
	}
	
}
