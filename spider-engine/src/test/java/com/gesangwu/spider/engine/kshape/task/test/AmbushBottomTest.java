package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.AmbushBottomTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class AmbushBottomTest extends BaseTest {

	@Resource
	private AmbushBottomTask task;
	
	@Test
	public void execute(){
		task.execute();
	}
}