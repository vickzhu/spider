package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.RouCuoTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class RouCuoTaskTest extends BaseTest {
	
	@Resource
	private RouCuoTask task;

//	@Test
	public void execute(){
//		task.execute();
		task.execute("2017-11-06");
	}
	
	@Test
	public void init(){
		for(int i = 1; i <= 13; i++){
			String tradeDate = buildDate(i);
			task.execute(tradeDate);
		}
	}
	
	private String buildDate(int day){
		StringBuilder sb = new StringBuilder();
		sb.append("2017-11-");
		if(day < 10){
			sb.append("0");
		}
		sb.append(day);
		return sb.toString();
	}
}
