package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.test.BaseTest;

public class StockProfileServiceTest extends BaseTest {
	
	@Resource
	private CompanyService profileService;

	@Test
	public void testSelectAll(){
		profileService.selectByExample(null);
	}
}
