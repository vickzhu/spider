package com.gesangwu.spider.engine.task.test;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.dao.model.Bidding;
import com.gesangwu.spider.biz.dao.model.BiddingExample;
import com.gesangwu.spider.biz.service.BiddingService;
import com.gesangwu.spider.engine.common.BiddingHolder;
import com.gesangwu.spider.engine.task.BiddingCalcTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class BiddingCalcTaskTest extends BaseTest {
	
	@Resource
	private BiddingCalcTask task;
	
	@Resource
	private BiddingService bdService;

	@Test
	public void execute(){
		long start = System.currentTimeMillis();
		List<Bidding> bdList = bdService.selectByExample(null);
		for (Bidding bd : bdList) {
			Map<String, Bidding> bdMap = BiddingHolder.getBidMap().get(bd.getSymbol());
			if(bdMap == null){
				bdMap = new TreeMap<String, Bidding>();
				BiddingHolder.getBidMap().put(bd.getSymbol(), bdMap);
			}
			bdMap.put(bd.getTradeTime(), bd);
		}
//		BiddingExample example = new BiddingExample();
//		BiddingExample.Criteria criteria = example.createCriteria();
//		criteria.andIdGreaterThan(0l);
//		bdService.deleteByExample(example);
		task.execute();
		long end = System.currentTimeMillis();
		System.out.println("Used:"+(end - start));
	}
}
