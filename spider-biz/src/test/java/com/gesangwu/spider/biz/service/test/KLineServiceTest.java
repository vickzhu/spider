package com.gesangwu.spider.biz.service.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gandalf.framework.mybatis.KeyValue;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.test.BaseTest;

public class KLineServiceTest extends BaseTest {

	@Resource
	private KLineService kLineService;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
//	@Test
	public void selectByExampleTest(){
		KLineExample example = new KLineExample();
		example.setOrderByClause("gmt_create desc");
		example.setOffset(0);
		example.setRows(1);		
		kLineService.selectByExample(example);
	}
	
	@Test
	public void selectLastestClose() throws ParseException {
		String symbol = "sz002211";
		String lastDate = "2017-07-11";
		Date date = sdf.parse(lastDate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, -1);
		String startDate = sdf.format(c.getTime());
		System.out.println(startDate);
		KLineExample example = new KLineExample();
		example.setOrderByClause("close desc");
		example.setOffset(1);
		example.setRows(30);
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateLessThanOrEqualTo(lastDate);
		criteria.andTradeDateGreaterThanOrEqualTo(startDate);
		criteria.andSymbolEqualTo(symbol);
		List<KeyValue<String, Double>> kvList = kLineService.selectLastestClose(example);
		for (KeyValue<String, Double> kv : kvList) {
			System.out.println(kv.getKey() + ":" + kv.getValue());
		}
	}

}
