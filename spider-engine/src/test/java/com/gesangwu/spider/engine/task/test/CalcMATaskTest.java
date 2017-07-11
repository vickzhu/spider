package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.CalcMATask;
import com.gesangwu.spider.engine.test.BaseTest;

public class CalcMATaskTest extends BaseTest {
	
	@Resource
	private CalcMATask task;
	
	@Test
	public void execute(){
		System.out.println("start.........");
		task.execute();
		System.out.println("==============");
	}
	
}
