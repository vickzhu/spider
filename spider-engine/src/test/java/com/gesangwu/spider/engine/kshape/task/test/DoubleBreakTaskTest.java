package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.DoubleBreakTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class DoubleBreakTaskTest extends BaseTest {
	
	@Resource
	private DoubleBreakTask task;

	@Test
	public void execute(){
		task.execute("2017-07-19");
	}
	
}
