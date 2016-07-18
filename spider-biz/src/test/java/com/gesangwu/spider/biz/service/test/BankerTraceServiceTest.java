package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.BankerTraceService;
import com.gesangwu.spider.biz.test.BaseTest;

public class BankerTraceServiceTest extends BaseTest {
	
	@Resource
	private BankerTraceService service;

	@Test
	public void selectByExampleTest(){
		service.selectByExample(null);
	}
}
