package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.init.CompanyInit;
import com.gesangwu.spider.engine.test.BaseTest;

@Deprecated
public class CompanyInitTest extends BaseTest {
	
	@Resource
	private CompanyInit spider;

	@Test
	public void test(){
		spider.execute();
	}
	
}
