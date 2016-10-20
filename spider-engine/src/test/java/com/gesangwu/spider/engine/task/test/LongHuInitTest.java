package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.engine.task.init.LongHuInit;
import com.gesangwu.spider.engine.test.BaseTest;

public class LongHuInitTest extends BaseTest {
	
	@Resource
	private LongHuInit longHuInit;
	
	@Before
	public void init(){
		String cookieUrl = "https://xueqiu.com/account/lostpasswd";
		HttpTool.get(cookieUrl);
	}

	@Test
	public void test(){
//		longHuInit.getLongHu("sh601128", "20160930");
//		longHuInit.getLongHu("sh600397", "20160930");
//		longHuInit.execute();
	}
}
