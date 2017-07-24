package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.ShapeTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class ShapeTaskTest extends BaseTest {
	
	@Resource
	private ShapeTask task;
	
	@Test
	public void execute(){
		System.out.println("start.........");
		task.execute();
		System.out.println("==============");
	}
	
}
