package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.ShareHolderTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class ShareHolderTaskTest extends BaseTest {
	
	@Resource
	private ShareHolderTask task;

	@Test
	public void test(){
		task.execute();
	}
}
