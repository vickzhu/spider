package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.test.BaseTest;
import com.gesangwu.spider.engine.util.SpiderMailSender;

public class MailSenderTest extends BaseTest {
	
	@Resource
	private SpiderMailSender sender;

	@Test
	public void sender(){
		sender.send("这是一个测试邮件");
	}
}
