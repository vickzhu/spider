package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.XinLangLongHuTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class XinLangLongHuTaskTest extends BaseTest{
	
	@Resource
	private XinLangLongHuTask task;

	@Test
	public void execute(){
		task.execute();
	}
}
