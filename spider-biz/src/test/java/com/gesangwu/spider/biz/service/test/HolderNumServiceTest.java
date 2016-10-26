package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.HolderNumService;
import com.gesangwu.spider.biz.test.BaseTest;

public class HolderNumServiceTest extends BaseTest {
	
	@Resource
	private HolderNumService service;

//	@Test
	public void selectByExample(){
		service.selectByExample(null);
	}
	
	@Test
	public void selectLatestBySymbol(){
		service.selectLatestBySymbol("002211");
	}
}
