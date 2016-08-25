package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.SecDeptTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class SecDeptTaskTest extends BaseTest {

	@Resource
	private SecDeptTask task;
	
	@Test
	public void execute(){
		task.execute();
	}
}
