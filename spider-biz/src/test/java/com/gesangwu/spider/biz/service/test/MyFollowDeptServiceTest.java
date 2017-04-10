package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.MyFollowDeptService;
import com.gesangwu.spider.biz.test.BaseTest;

public class MyFollowDeptServiceTest extends BaseTest {

	@Resource
	private MyFollowDeptService myFollowDeptService;
	
	@Test
	public void selectByExample(){
		myFollowDeptService.selectByExample(null);
	}
}
