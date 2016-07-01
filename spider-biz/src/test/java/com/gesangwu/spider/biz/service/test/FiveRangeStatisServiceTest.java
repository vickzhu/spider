package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.FiveRangeStatisService;
import com.gesangwu.spider.biz.test.BaseTest;

public class FiveRangeStatisServiceTest extends BaseTest {
	
	@Resource
	private FiveRangeStatisService service;
	
	@Test
	public void selectByExampleTest(){
		service.selectByExample(null);
	}
}
