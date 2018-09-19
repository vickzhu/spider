package com.gesangwu.spider.engine.task.test;

import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gandalf.framework.mybatis.KeyValue;
import com.gesangwu.spider.engine.task.KLineSinaTask;
import com.gesangwu.spider.engine.task.KLineXueQiuTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class KLineTaskTest extends BaseTest {

	@Resource
	private KLineSinaTask task1;
	
	@Resource
	private KLineXueQiuTask task;
	
	@Test
	public void executTest(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MILLISECOND, 0);
		c.set(2018, 3, 24, 0, 0, 0);
		
//		c.set(Calendar.DAY_OF_MONTH, c.get(Calendar.DAY_OF_MONTH)-1);
		long start = c.getTimeInMillis();
		System.out.println(c.getTimeInMillis());
		c.set(2018, 4, 4, 23, 59, 59);
//		c.set(Calendar.HOUR, 23);
//		c.set(Calendar.MINUTE, 59);
//		c.set(Calendar.SECOND, 59);
		long end = c.getTimeInMillis();
//		task.execute(start, end);
		task1.execute();
	}
	
	
//	@Test
	public void getCloseList(){
		List<KeyValue<String, Double>> kvList = task1.getCloseList("sz300668", "2017-08-03");
		for (KeyValue<String, Double> kv : kvList) {
			System.out.println(kv.getKey() + ":" + kv.getValue());
		}
		Double ma5 = task1.calcMA(kvList, 5);
		System.out.println(ma5);
	}
	
	public static void main(String[] args){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MILLISECOND, 0);
		c.set(2018, 1, 8, 0, 0, 0);
		System.out.println(c.getTimeInMillis());
		c.set(2018, 1, 14, 23, 59, 59);
		System.out.println(c.getTimeInMillis());
	}
}
