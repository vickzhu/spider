package com.gesangwu.spider.engine.task.test;

import java.util.Calendar;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.test.BaseTest;
import com.gesangwu.spider.engine.task.KLineTask;

public class KLineTaskTest extends BaseTest {

	@Resource
	private KLineTask task;
	
	@Test
	public void executTest(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MILLISECOND, 0);
		c.set(2016, 5, 21, 0, 0, 0);
		
		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)-1);
		long start = c.getTimeInMillis();
		System.out.println(c.getTimeInMillis());
		c.set(2016, 6, 16, 23, 59, 59);
//		c.set(Calendar.HOUR, 23);
//		c.set(Calendar.MINUTE, 59);
//		c.set(Calendar.SECOND, 59);
		long end = c.getTimeInMillis();
		task.execute(start, end);
	}
}
