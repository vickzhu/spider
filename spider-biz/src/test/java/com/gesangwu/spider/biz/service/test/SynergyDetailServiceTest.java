package com.gesangwu.spider.biz.service.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gandalf.framework.mybatis.KeyValue;
import com.gesangwu.spider.biz.service.SynergyDetailService;
import com.gesangwu.spider.biz.test.BaseTest;

public class SynergyDetailServiceTest extends BaseTest {

	@Resource
	private SynergyDetailService sdService;
	
	@Test
	public void relateDept(){
		List<String> depts = new ArrayList<String>();
		depts.add("80576429");
		depts.add("80551523");
		depts.add("80623190");
		depts.add("80618393");
		List<KeyValue<Integer, Integer>> list = sdService.relateDept(depts);
		for(KeyValue<Integer, Integer> kv: list){
			System.out.println(kv.getKey()+":" + kv.getValue());
		}
	}
}
