package com.gesangwu.spider.biz.service.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.dao.model.StockNameInitial;
import com.gesangwu.spider.biz.service.StockNameInitialService;
import com.gesangwu.spider.biz.test.BaseTest;

public class StockNameInitialServiceTest extends BaseTest {

	@Resource
	private StockNameInitialService initialService;
	
	@Test
	public void batchInsert(){
		List<StockNameInitial> initialList = new ArrayList<StockNameInitial>();
		initialService.batchInsert(initialList);
	}
	
}
