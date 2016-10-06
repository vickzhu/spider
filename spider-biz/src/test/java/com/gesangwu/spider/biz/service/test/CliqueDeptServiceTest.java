package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.CliqueDeptService;
import com.gesangwu.spider.biz.test.BaseTest;

public class CliqueDeptServiceTest extends BaseTest {

	@Resource
	private CliqueDeptService service;
	
	@Test
	public void test(){
		service.selectByExample(null);
	}
}
