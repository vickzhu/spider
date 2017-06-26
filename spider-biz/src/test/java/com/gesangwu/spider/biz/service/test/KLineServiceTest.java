package com.gesangwu.spider.biz.service.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.test.BaseTest;

public class KLineServiceTest extends BaseTest {

	@Resource
	private KLineService kLineService;
	
	@Test
	public void selectByExampleTest(){
		KLineExample example = new KLineExample();
		example.setOrderByClause("gmt_create desc");
		example.setOffset(0);
		example.setRows(1);		
		kLineService.selectByExample(example);
	}
}
