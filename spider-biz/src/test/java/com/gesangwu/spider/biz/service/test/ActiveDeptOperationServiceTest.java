package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.ActiveDeptOperationService;
import com.gesangwu.spider.biz.test.BaseTest;

public class ActiveDeptOperationServiceTest extends BaseTest {
	
	@Resource
	private ActiveDeptOperationService adoService;

	@Test
	public void selectByExample(){
		adoService.selectByExample(null);
	}
}
