package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.JiHuaTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class JiHuaTaskTest extends BaseTest {

	@Resource
	private JiHuaTask task;
	
	@Test
	public void execute(){
//		task.execute();
		task.execute("2017-11-23");
	}
	
//	@Test
	public void init(){
		for(int i = 1; i <= 31; i++){
			String tradeDate = buildDate(i);
			System.out.println(tradeDate);
			task.execute(tradeDate);
		}
	}
	
	private String buildDate(int day){
		StringBuilder sb = new StringBuilder();
		sb.append("2017-10-");
		if(day < 10){
			sb.append("0");
		}
		sb.append(day);
		return sb.toString();
	}
}
