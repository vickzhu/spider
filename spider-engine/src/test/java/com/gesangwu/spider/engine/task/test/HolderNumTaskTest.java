package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.HolderNumTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class HolderNumTaskTest extends BaseTest {
	
	@Resource
	private HolderNumTask task;
	
	@Test
	public void execute(){
		task.execute();
	}
}
