package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.BigVolStatisService;
import com.gesangwu.spider.biz.test.BaseTest;

public class BigVolStatisServiceTest extends BaseTest {
	
	@Resource
	private BigVolStatisService service;

	@Test
	public void selectByExampleTest(){
		service.selectByExample(null);
	}
}
