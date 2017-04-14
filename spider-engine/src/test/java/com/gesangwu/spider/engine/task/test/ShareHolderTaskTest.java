package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.engine.task.ShareHolderTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class ShareHolderTaskTest extends BaseTest {
	
	@Resource
	private ShareHolderTask task;
	
	@Before
	public void init(){
		String cookieUrl = "https://xueqiu.com/account/lostpasswd";
		HttpTool.get(cookieUrl);//这个链接只是为了获得cookie信息，因为后面的请求需要用到cookie
	}

	@Test
	public void test(){
		task.execute();
	}
	
//	@Test
	public void getContent(){
		String content = task.getContent("sh603010");
		System.out.println(content);
	}
	
//	@Test
	public void fetch(){
		String[] symbolArr = new String[]{"sh603703","sh603003","sh600802","sh600610","sh600281","sh600210"};
		for (String symbol : symbolArr) {
			task.fetch(symbol);
		}
		
	}
}
