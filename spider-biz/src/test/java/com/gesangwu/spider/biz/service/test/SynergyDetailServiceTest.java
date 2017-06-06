package com.gesangwu.spider.biz.service.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gandalf.framework.mybatis.KeyValue;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.SynergyDetailService;
import com.gesangwu.spider.biz.test.BaseTest;

public class SynergyDetailServiceTest extends BaseTest {

	@Resource
	private LongHuDetailService lhdService;
	@Resource
	private SynergyDetailService sdService;
	
	@Test
	public void relateDept(){
		LongHuDetailExample example = new LongHuDetailExample();
		LongHuDetailExample.Criteria criteria = example.createCriteria();
		criteria.andLongHuIdEqualTo(92020l);
		List<LongHuDetail> lhdList = lhdService.selectByExample(example);
		List<String> depts = new ArrayList<String>();
		for (LongHuDetail longHuDetail : lhdList) {
			depts.add(longHuDetail.getSecDeptCode());
		}
		List<KeyValue<Integer, Integer>> list = sdService.relateDept(depts);
		for(KeyValue<Integer, Integer> kv: list){
			System.out.println(kv.getKey()+":" + kv.getValue());
			List<KeyValue<String, String>> tmpList = lhdService.selectRelationStock("2017-03-05", "2017-06-05", kv.getKey());
			for (KeyValue<String, String> tmpKv : tmpList) {
				System.out.println(tmpKv.getKey()+"---"+tmpKv.getValue());
			}
			System.out.println("***************");
		}
	}
}
