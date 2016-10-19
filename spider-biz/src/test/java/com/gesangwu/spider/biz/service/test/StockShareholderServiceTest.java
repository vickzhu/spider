package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.StockShareHolderService;
import com.gesangwu.spider.biz.test.BaseTest;

public class StockShareholderServiceTest extends BaseTest {
	
	@Resource
	private StockShareHolderService sshService;
	
	@Test
	public void selectByExample(){
		sshService.selectByExample(null);
	}
}
