package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.CompanyUpdateTask;
import com.gesangwu.spider.engine.task.StCompanyUpdateTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class CompanyUpdateTest extends BaseTest {
	
	@Resource
	private CompanyUpdateTask company1Update;
	@Resource
	private StCompanyUpdateTask company2Update;

	@Test
	public void test(){
		company1Update.execute();
		company2Update.execute();
	}
	
}
