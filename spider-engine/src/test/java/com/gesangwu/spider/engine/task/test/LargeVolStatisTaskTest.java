package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.LargeVolTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class LargeVolStatisTaskTest extends BaseTest {
	
	@Resource
	private LargeVolTask task;
	
	@Test
	public void test(){
		task.execute();
	}
}
