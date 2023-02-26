package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.SecDeptService;
import com.gesangwu.spider.biz.test.BaseTest;

public class SecDeptTest extends BaseTest {

	@Resource
	private SecDeptService sdService;
	@Resource
	private LongHuDetailService lhdService;
	
	@Test
	public void updateActiveDept(){
		sdService.clearActiveDept();
		sdService.updateActiveDept("");
	}
}
