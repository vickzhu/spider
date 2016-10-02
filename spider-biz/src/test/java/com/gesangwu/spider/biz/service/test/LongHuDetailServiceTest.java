package com.gesangwu.spider.biz.service.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExt;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.test.BaseTest;

public class LongHuDetailServiceTest extends BaseTest {
	
	@Resource
	private LongHuDetailService lhDetailService;

	@Test
	public void selectByTradeDate(){
		Page<LongHuDetailExt> page = new Page<LongHuDetailExt>(1, 10);
		LongHuDetailExample example = new LongHuDetailExample();
		LongHuDetailExample.Criteria criteria = example.createCriteria();
		criteria.andSecDeptCodeEqualTo(80443987);
		lhDetailService.selectDetailExtByExample(example,page);
		List<LongHuDetailExt> detailExtList = page.getRecords();
		for (LongHuDetailExt longHu : detailExtList) {
			System.out.println(longHu.getStockName());
		}
	}
}
