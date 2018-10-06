package com.gesangwu.spider.biz.service.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.dao.model.LianBanPlate;
import com.gesangwu.spider.biz.service.LianBanPlateService;
import com.gesangwu.spider.biz.test.BaseTest;

public class LianBanPlateServiceTest extends BaseTest {

	@Resource
	private LianBanPlateService lbpService;
	
	@Test
	public void selectByTradeDate(){
		List<LianBanPlate> lbpList = lbpService.selectByTradeDate("2018-09-28");
		for (LianBanPlate lbp : lbpList) {
			System.out.println(lbp.getPlate());
		}
	}
}
