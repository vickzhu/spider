package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.FLoatShareCalc;
import com.gesangwu.spider.engine.test.BaseTest;

public class FLoatShareCalcTest extends BaseTest {

	@Resource
	private FLoatShareCalc shareCalc;
	
	@Test
	public void calcActiveShare(){
		shareCalc.execute();
	}
}
