package com.gesangwu.spider.biz.service.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.test.BaseTest;

public class KLineServiceTest extends BaseTest {

	@Resource
	private KLineService kLineService;
	
//	@Test
	public void selectByExampleTest(){
		KLineExample example = new KLineExample();
		example.setOrderByClause("gmt_create desc");
		example.setOffset(0);
		example.setRows(1);		
		kLineService.selectByExample(example);
	}
	
	@Test
	public void selectLastest30Close() {
		String symbol = "sz002211";
		String lastDate = "2017-07-11";
		List<Double> closeList = kLineService.selectLastest30Close(symbol, lastDate);
		for (Double close : closeList) {
			System.out.println(close);
		}
	}

}
