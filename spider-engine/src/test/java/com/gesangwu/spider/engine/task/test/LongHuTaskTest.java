package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.engine.task.LongHuSinaTask;
import com.gesangwu.spider.engine.task.LongHuTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class LongHuTaskTest extends BaseTest {

	@Resource
	private LongHuTask task;
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhdService;
	@Resource
	private LongHuSinaTask sinaTask;
	
	@Test
	public void execute(){
		task.execute(null);
//		task.execute("2018-06-22");
//		LongHu longHu = lhService.selectByPrimaryKey(82345l);
//		task.fetchDetail(1, "04", longHu);
//		task.fetchDetail(longHu);
	}
	
//	@Test
//	public void isOrg(){
//		LongHuDetailExample example = new LongHuDetailExample();
//		LongHuDetailExample.Criteria criteria = example.createCriteria();
//		criteria.andTradeDateEqualTo("2017-07-24");
//		criteria.andSymbolEqualTo("sz300137");
//		List<LongHuDetail> lhdList = lhdService.selectByExample(example);
//		boolean isOrg = sinaTask.isOrg(lhdList);
//		System.out.println(isOrg);
//	}
}
