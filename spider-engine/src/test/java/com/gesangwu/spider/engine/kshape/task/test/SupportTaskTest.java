package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.SupportTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class SupportTaskTest extends BaseTest {
	
	@Resource
	private SupportTask task;
	
	@Test
	public void execute(){
		task.execute("2017-08-02");
		
	}
}
