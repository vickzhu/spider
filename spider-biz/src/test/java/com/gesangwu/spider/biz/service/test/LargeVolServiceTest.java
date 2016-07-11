package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.LargeVolService;
import com.gesangwu.spider.biz.test.BaseTest;

public class LargeVolServiceTest extends BaseTest {
	
	@Resource
	private LargeVolService service;

	@Test
	public void selectByExmaple(){
		service.selectByExample(null);
	}
}
