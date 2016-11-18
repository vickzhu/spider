package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.UpperShadowService;
import com.gesangwu.spider.biz.test.BaseTest;

public class UpperShadowServiceTest extends BaseTest {
	
	@Resource
	private UpperShadowService usService;

	@Test
	public void selectByExample(){
		usService.selectByExample(null);
	}
}
