package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.init.UpperShadowInit;
import com.gesangwu.spider.engine.test.BaseTest;

public class UpperShadowInitTest extends BaseTest {
	
	@Resource
	private UpperShadowInit usi;

	@Test
	public void execute(){
		usi.execute();
	}
}
