package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.ShortStepStatisTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class ShortStepStatisTaskTest extends BaseTest {

	@Resource
	private ShortStepStatisTask task;
	
	@Test
	public void execute(){
		task.execute();
	}
	
}
