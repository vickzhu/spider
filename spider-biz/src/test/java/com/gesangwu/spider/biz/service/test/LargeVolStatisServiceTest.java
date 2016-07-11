package com.gesangwu.spider.biz.service.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.LargeVolStatisService;
import com.gesangwu.spider.biz.test.BaseTest;

public class LargeVolStatisServiceTest extends BaseTest {
	
	@Resource
	private LargeVolStatisService lvsService;
	
	@Test
	public void selectByExampleTest(){
		lvsService.selectByExample(null);
	}
	
	@Test
	public void selectBySymbolAndDate(){
		String symbol = "sh002265";
		Date date = new Date();
		lvsService.selectBySymbolAndDate(symbol, date);
	}
}
