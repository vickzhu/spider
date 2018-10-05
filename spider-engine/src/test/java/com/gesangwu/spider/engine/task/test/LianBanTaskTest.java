package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.LianBanTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class LianBanTaskTest extends BaseTest {
	
	@Resource
	private LianBanTask task;
	
	@Test
	public void execute(){
		task.execute("2018-10-01");
	}

}
