package com.gesangwu.spider.engine.task.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.engine.task.CliqueStockTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class CliqueStockTaskTest extends BaseTest {

	@Resource
	private CliqueStockTask task;
	@Resource
	private LongHuDetailService lhdService;
	
	@Test
	public void execute(){
		task.execute();
	}
	
//	@Test
	public void calcOtherDept(){
		long cliqueId=1001l;
		long detailId = 639882l;
		LongHuDetail detail = lhdService.selectByPrimaryKey(detailId);
		List<LongHuDetail> detailList = new ArrayList<LongHuDetail>();
		detailList.add(detail);
		task.calcOtherDept(cliqueId, detailList);
	}
}
