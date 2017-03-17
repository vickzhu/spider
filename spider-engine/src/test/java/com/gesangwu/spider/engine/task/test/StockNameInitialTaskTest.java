package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.StockNameInitialTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class StockNameInitialTaskTest extends BaseTest {

	@Resource
	private StockNameInitialTask task;
	
	@Test
	public void execute(){
		task.execute();
	}
}
