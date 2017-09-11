package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.UpperShadowTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class UpperShadowTaskTest extends BaseTest {
	
	@Resource
	private UpperShadowTask task;

	@Test
	public void execute(){
		task.execute();
//		task.execute("2017-09-07");
	}
	
}
