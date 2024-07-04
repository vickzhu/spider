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
		//闭区间
		Calendar c = Calendar.getInstance();
		c.set(2024, 2, 28, 0, 0, 0);
		long start = c.getTimeInMillis();
		c.set(2024, 6, 30, 0, 0, 0);
		long end = c.getTimeInMillis();
		ki.execute(start, end);
	}
}
