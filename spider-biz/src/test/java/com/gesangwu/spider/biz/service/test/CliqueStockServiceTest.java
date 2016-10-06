package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.CliqueStockService;
import com.gesangwu.spider.biz.test.BaseTest;

public class CliqueStockServiceTest extends BaseTest {

	@Resource
	private CliqueStockService service;
	
	@Test
	public void test(){
		service.selectByExample(null);
	}
}
