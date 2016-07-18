package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.BankerTraceStatisTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class BankerTraceStatisTaskTest extends BaseTest {

	@Resource
	private BankerTraceStatisTask task;
	
	@Test
	public void execute(){
		task.execute();
	}
}
