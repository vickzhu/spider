package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.DiXiTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class DiXiTaskTest extends BaseTest {

	@Resource
	private DiXiTask task;
	
//	@Test
	public void execute(){
		task.execute();
//		task.execute("2018-01-03");
	}
	
	@Test
	public void init(){
		for(int i = 8; i <= 14; i++){
			String tradeDate = buildDate(i);
			task.execute(tradeDate);
		}
	}
	
	private String buildDate(int day){
		StringBuilder sb = new StringBuilder();
		sb.append("2018-02-");
		if(day < 10){
			sb.append("0");
		}
		sb.append(day);
		return sb.toString();
	}
}
