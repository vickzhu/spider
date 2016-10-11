package com.gesangwu.spider.engine.task.test;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.engine.task.DanRiLongHu;
import com.gesangwu.spider.engine.test.BaseTest;

public class DanRiLongHuTest extends BaseTest {

	@Resource
	private DanRiLongHu drLongHu;
	
	@Before
	public void init(){
		String cookieUrl = "https://xueqiu.com/account/lostpasswd";
		HttpTool.get(cookieUrl);
	}
	
	@Test
	public void execute(){
		drLongHu.execute("2016-10-10");
	}
	
	public static void main(String[] args){
		System.out.println(new BigDecimal("7.0E-4").setScale(6, BigDecimal.ROUND_HALF_UP));
	}
}
