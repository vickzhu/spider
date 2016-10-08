package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.CliqueStockTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class CliqueStockTaskTest extends BaseTest {

	@Resource
	private CliqueStockTask task;
	
	@Test
	public void test(){
		task.execute();
	}
}
