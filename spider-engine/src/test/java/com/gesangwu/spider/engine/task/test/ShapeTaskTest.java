package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.engine.task.ShapeTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class ShapeTaskTest extends BaseTest {
	
	@Resource
	private ShapeTask task;
	@Resource
	private KLineService klService;
	
	@Test
	public void execute(){
		System.out.println("start.........");
		task.execute("2017-07-27");
		System.out.println("==============");
	}
	
//	@Test
	public void isValid(){
		KLine kl = klService.selectByPrimaryKey(1706213l);
		boolean result = task.isValid("2017-07-24", kl);
		System.out.println(result);
	}
	
}
