package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.LongHuTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class LongHuTaskTest extends BaseTest {

	@Resource
	private LongHuTask task;
	
	@Test
	public void execute(){
		task.execute();
	}
}
