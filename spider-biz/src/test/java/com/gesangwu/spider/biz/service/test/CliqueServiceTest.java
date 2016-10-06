package com.gesangwu.spider.biz.service.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.dao.model.Clique;
import com.gesangwu.spider.biz.service.CliqueService;
import com.gesangwu.spider.biz.test.BaseTest;

public class CliqueServiceTest extends BaseTest {

	@Resource
	private CliqueService service;
	
	@Test
	public void test(){
		List<Clique> cliqueList = service.selectByExample(null);
		for (Clique clique : cliqueList) {
			System.out.println(clique.getName());
		}
	}
}
