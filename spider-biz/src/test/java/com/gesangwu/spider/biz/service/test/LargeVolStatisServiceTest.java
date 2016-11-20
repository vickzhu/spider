package com.gesangwu.spider.biz.service.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.dao.model.LargeVolStatis;
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
		lvsService.selectBySymbolAndDate(symbol, "2016-10-20");
	}
	
	@Test
	public void selectByTradeDate(){
		List<LargeVolStatis> lvsList = lvsService.selectByTradeDate(null);
		for (LargeVolStatis lvs : lvsList) {
			System.out.println(lvs.getSymbol() + ":" + lvs.getTradeDate());
		}
	}
}
