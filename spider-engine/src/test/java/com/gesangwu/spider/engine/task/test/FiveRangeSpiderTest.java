package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.FiveRangeTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class FiveRangeSpiderTest extends BaseTest {
	
	@Resource
	private FiveRangeTask spider;
	
	@Test
	public void test(){
		spider.execute();
	}
}
