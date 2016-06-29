package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.ShareHolderSpider;
import com.gesangwu.spider.engine.test.BaseTest;

public class ShareHolderTest extends BaseTest {
	
	@Resource
	private ShareHolderSpider spider;

	@Test
	public void test(){
		spider.execute();
	}
}
