package com.gesangwu.spider.biz.service.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.biz.test.BaseTest;

public class LongHuServiceTest extends BaseTest {
	
	@Resource
	private LongHuService lhService;

	@Test
	public void selectByTradeDate(){
		
		List<LongHu> lhList = lhService.selectByTradeDate(null);
		for (LongHu longHu : lhList) {
			System.out.println(longHu.getStockName());
		}
	}
}
