package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.test.BaseTest;

public class KLineServiceTest extends BaseTest {

	@Resource
	private KLineService kLineService;
	
	@Test
	public void selectByExampleTest(){
		kLineService.selectByExample(null);
	}
}
