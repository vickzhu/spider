package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.engine.task.LongHuTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class LongHuTaskTest extends BaseTest {

	@Resource
	private LongHuTask task;
	@Resource
	private LongHuService lhService;
	
	@Test
	public void execute(){
//		task.execute(null);
//		task.execute("2016-10-13");
//		task.execute("2016-10-14");
		task.execute("2017-01-26");
//		LongHu longHu = lhService.selectByPrimaryKey(82345l);
//		task.fetchDetail(1, "04", longHu);
//		task.fetchDetail(longHu);
	}
}
