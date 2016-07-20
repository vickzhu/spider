package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.FindMilestoneTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class FindMilestoneTaskTest extends BaseTest {

	@Resource
	private FindMilestoneTask task;
	
	@Test
	public void execute(){
		task.execute();
	}
}
