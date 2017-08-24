package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.ShrinkFallTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class ShrinkFallTaskTest extends BaseTest {
	
	@Resource
	private ShrinkFallTask task;

	@Test
	public void execute(){
		task.execute("2017-08-23");
	}
}
