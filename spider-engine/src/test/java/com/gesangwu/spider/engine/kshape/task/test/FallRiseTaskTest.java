package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.FallRiseTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class FallRiseTaskTest extends BaseTest {

	@Resource
	private FallRiseTask task;
	
//	@Test
	public void execute(){
		task.execute("2018-01-17");
	}
	
	@Test
	public void init(){
		for(int i = 1; i <= 31; i++){
			String tradeDate = buildDate(i);
			task.execute(tradeDate);
		}
	}
	
	private String buildDate(int day){
		StringBuilder sb = new StringBuilder();
		sb.append("2018-01-");
		if(day < 10){
			sb.append("0");
		}
		sb.append(day);
		return sb.toString();
	}
}
