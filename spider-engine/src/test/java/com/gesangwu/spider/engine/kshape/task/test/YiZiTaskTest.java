package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.YiZiTask;
import com.gesangwu.spider.engine.test.BaseTest;

/**
 * 一字
 * @author bran
 *
 */
public class YiZiTaskTest extends BaseTest {

	@Resource
	private YiZiTask task;
	
	@Test
	public void execute(){
		task.execute();
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
