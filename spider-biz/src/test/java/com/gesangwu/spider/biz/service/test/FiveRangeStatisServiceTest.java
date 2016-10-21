package com.gesangwu.spider.biz.service.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.dao.model.FiveRangeStatis;
import com.gesangwu.spider.biz.service.FiveRangeStatisService;
import com.gesangwu.spider.biz.test.BaseTest;

public class FiveRangeStatisServiceTest extends BaseTest {
	
	@Resource
	private FiveRangeStatisService service;
	
//	@Test
	public void selectByExampleTest(){
		service.selectByExample(null);
	}
	
	@Test
	public void selectByTradeDate(){
		List<FiveRangeStatis> list = service.selectByTradeDate("2016-10-20");
		for (FiveRangeStatis frs : list) {
			System.out.println(frs.getSymbol() + ":" + frs.getTradeDate());
		}
	}
}
