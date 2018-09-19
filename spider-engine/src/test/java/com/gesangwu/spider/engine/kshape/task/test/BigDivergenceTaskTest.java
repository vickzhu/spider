package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.BigDivergenceTask;
import com.gesangwu.spider.engine.test.BaseTest;

/**
 * 大分歧
 * @author bran
 *
 */
public class BigDivergenceTaskTest extends BaseTest {
	
	@Resource
	private BigDivergenceTask task;

//	@Test
	public void execute(){
		task.execute();
//		task.execute("2017-08-28");
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
