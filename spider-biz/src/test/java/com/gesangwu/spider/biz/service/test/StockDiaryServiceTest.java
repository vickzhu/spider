package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.StockDiaryService;
import com.gesangwu.spider.biz.test.BaseTest;

public class StockDiaryServiceTest extends BaseTest {
	
	@Resource
	private StockDiaryService service;

	@Test
	public void selectByExample(){
		service.selectByExample(null);	
	}
}
