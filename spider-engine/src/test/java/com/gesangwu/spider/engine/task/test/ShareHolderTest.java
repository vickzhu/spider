package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.ShareHolderTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class ShareHolderTest extends BaseTest {
	
	@Resource
	private ShareHolderTask spider;

	@Test
	public void test(){
		spider.execute();
	}
}
