package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.BiddingService;
import com.gesangwu.spider.biz.test.BaseTest;

public class BiddingServiceTest extends BaseTest {
	
	@Resource
	private BiddingService bs;

	@Test
	public void selectByExample(){
		bs.selectByExample(null);
	}
}
