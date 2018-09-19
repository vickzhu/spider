package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.GroundSkyTask;
import com.gesangwu.spider.engine.test.BaseTest;

/**
 * 地天
 * @author bran
 *
 */
public class GroundSkyTaskTest extends BaseTest {

	@Resource
	private GroundSkyTask task;
	
	@Test
	public void execute(){
		task.execute();
//		task.execute("2017-11-29");
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
