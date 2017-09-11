package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.CoverNegTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class CoverNegTaskTest extends BaseTest {

	@Resource
	private CoverNegTask task;
	
	@Test
	public void execute(){
		task.execute();
//		task.execute("2017-09-11");
	}
}
