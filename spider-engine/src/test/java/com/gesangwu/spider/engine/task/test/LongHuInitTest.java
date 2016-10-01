package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.engine.task.LongHuInit;
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
		longHuInit.getLongHu("sz002266", "20160930");
		longHuInit.getLongHu("sz000635", "20160930");
		longHuInit.getLongHu("sz000731", "20160930");
		longHuInit.getLongHu("sz000757", "20160930");
		longHuInit.getLongHu("sz000912", "20160930");
		longHuInit.getLongHu("sz000953", "20160930");
//		longHuInit.getLongHu("sz200054", "20160930");
		
		longHuInit.getLongHu("sh600173", "20160930");
		longHuInit.getLongHu("sh601128", "20160930");
		longHuInit.getLongHu("sh600600", "20160930");
//		longHuInit.execute();
	}
}
