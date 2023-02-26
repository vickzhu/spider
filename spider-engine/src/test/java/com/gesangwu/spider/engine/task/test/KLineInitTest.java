package com.gesangwu.spider.engine.task.test;

import java.util.Calendar;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.init.KLineInit;
import com.gesangwu.spider.engine.test.BaseTest;

public class KLineInitTest extends BaseTest {

	@Resource
	private KLineInit ki;
	
	@Test
	public void execute(){
		Calendar c = Calendar.getInstance();
		c.set(2022, 10, 15, 0, 0, 0);
		long start = c.getTimeInMillis();
		c.set(2022, 11, 10, 0, 0, 0);
		long end = c.getTimeInMillis();
		ki.execute(start, end);
	}
}
