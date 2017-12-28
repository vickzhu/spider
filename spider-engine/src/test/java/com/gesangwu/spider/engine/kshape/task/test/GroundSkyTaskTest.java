package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.GroundSkyTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class GroundSkyTaskTest extends BaseTest {

	@Resource
	private GroundSkyTask task;
	
	@Test
	public void execute(){
		task.execute();
//		task.execute("2017-11-29");
	}
}
