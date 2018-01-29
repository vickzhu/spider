package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.BiddingTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class BiddingTaskTest extends BaseTest {

	@Resource
	private BiddingTask task;
	
	@Test
	public void execute(){
		task.calc();
	}
}
