package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.UpperShadowTask;
import com.gesangwu.spider.engine.test.BaseTest;

/**
 * 长上影
 * @author zhuxb
 *
 */
public class UpperShadowTaskTest extends BaseTest {
	
	@Resource
	private UpperShadowTask task;

	@Test
	public void execute(){
		task.execute();
//		task.execute("2018-07-04");
	}
	
//	@Test
	public void init(){
		for(int i = 1; i <= 4; i++){
			String tradeDate = buildDate(i);
			task.execute(tradeDate);
		}
	}
	
	private String buildDate(int day){
		StringBuilder sb = new StringBuilder();
		sb.append("2018-05-");
		if(day < 10){
			sb.append("0");
		}
		sb.append(day);
		return sb.toString();
	}
	
}
