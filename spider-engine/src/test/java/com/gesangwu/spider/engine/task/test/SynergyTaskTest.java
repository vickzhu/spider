package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.SynergyTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class SynergyTaskTest extends BaseTest {
	
	@Resource
	private SynergyTask task;
	
//	@Test
	public void execute(){
		task.execute();
	}
	
	@Test
	public void analyze(){
		task.analyze("2017-06-01");
	}
}
