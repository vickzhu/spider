package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.VolumeCalTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class VolumeCalTaskTest extends BaseTest {
	
	@Resource
	private VolumeCalTask task;

	@Test
	public void execute(){
		task.execute();
	}
	
}
