package com.gesangwu.spider.engine.task.test;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.CompanySpider;
import com.gesangwu.spider.engine.test.BaseTest;

public class CompanySpiderTest extends BaseTest {
	
	@Resource
	private CompanySpider spider;

	@Test
	public void test(){
		try {
			spider.execute();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
