package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.ShareHolderService;
import com.gesangwu.spider.biz.test.BaseTest;

public class ShareHolderServiceTest extends BaseTest {

	@Resource
	private ShareHolderService shareHolderService;
	
//	@Test
	public void selectAll(){
		shareHolderService.selectByExample(null);
	}
	
	@Test
	public void calcFloatRate(){
		double rate = shareHolderService.calcFloatRate("sz002265");
		System.out.println(rate);
	}
}
