package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.BigVolStatisTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class BigVolStatisTaskTest extends BaseTest {
	
	@Resource
	private BigVolStatisTask task;
	
	@Test
	public void test(){
		task.execute();
	}
}
