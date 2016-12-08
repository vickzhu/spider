package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.JdStatisService;
import com.gesangwu.spider.biz.test.BaseTest;

public class JdStatisServiceTest extends BaseTest {

	@Resource
	private JdStatisService statisService;
	
	@Test
	public void selectByExample(){
		statisService.selectByExample(null);
	}
}
