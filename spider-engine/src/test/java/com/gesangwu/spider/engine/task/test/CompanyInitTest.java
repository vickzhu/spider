package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import com.gesangwu.spider.engine.task.CompanyInit;
import com.gesangwu.spider.engine.test.BaseTest;

public class CompanyInitTest extends BaseTest {
	
	@Resource
	private CompanyInit spider;

//	@Test
	public void test(){
		spider.execute();
	}
	
}
