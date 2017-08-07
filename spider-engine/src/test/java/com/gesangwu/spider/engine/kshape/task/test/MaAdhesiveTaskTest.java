package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.MaAdhesiveTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class MaAdhesiveTaskTest extends BaseTest {

	@Resource
	private MaAdhesiveTask task;
	
	@Test
	public void execute(){
		task.execute();
//		task.execute("2017-08-04");
	}
	
}
