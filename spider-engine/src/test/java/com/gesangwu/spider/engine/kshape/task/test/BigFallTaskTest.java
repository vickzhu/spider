package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.BigFallTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class BigFallTaskTest extends BaseTest {

	@Resource
	private BigFallTask task;
	
	@Test
	public void execute(){
		task.execute("2017-09-25");
	}
	
}
