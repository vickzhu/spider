package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.FirstNegativeTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class FirstNegativeTaskTest extends BaseTest {
	
	@Resource
	private FirstNegativeTask task;

	@Test
	public void execute(){
		task.execute();
//		task.execute("2017-08-04");
	}
	
}
