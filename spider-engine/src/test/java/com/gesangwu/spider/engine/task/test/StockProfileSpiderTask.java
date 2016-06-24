package com.gesangwu.spider.engine.task.test;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.StockProfileSpider;
import com.gesangwu.spider.engine.test.BaseTest;

public class StockProfileSpiderTask extends BaseTest {
	
	@Resource
	private StockProfileSpider spider;

	@Test
	public void test(){
		try {
			spider.execute();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
