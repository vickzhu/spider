package com.gesangwu.spider.biz.service.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.StockShareHolder;
import com.gesangwu.spider.biz.dao.model.StockShareHolderExample;
import com.gesangwu.spider.biz.dao.model.ext.StockShareHolderExt;
import com.gesangwu.spider.biz.service.StockShareHolderService;
import com.gesangwu.spider.biz.test.BaseTest;

public class StockShareholderServiceTest extends BaseTest {
	
	@Resource
	private StockShareHolderService sshService;
	
//	@Test
	public void selectByExample(){
		sshService.selectByExample(null);
	}
	
//	@Test
	public void calcFloatRate(){
		String symbol = "sz002545";
		System.out.println(sshService.calcFloatRate(symbol));
	}
	
//	@Test
	public void selectLatestDate(){
		String symbol = "sz002211";
		System.out.println(sshService.selectLatestDate(symbol));
	}
	
//	@Test
	public void selectByPersonalName(){
		List<StockShareHolderExt> sshExt = sshService.selectByPersonalName("李国院");
		for (StockShareHolderExt ext : sshExt) {
			System.out.println(ext.getStockName());
		}
	}
	
//	@Test
	public void selectByHolderId(){
		List<StockShareHolder> sshList = sshService.selectByShareHolder(50224l);
		for (StockShareHolder ssh : sshList) {
			System.out.println(ssh.getEndDate());
		}
	}
	
	@Test
	public void selectCliqueByExample(){
		Page<StockShareHolderExt> page = new Page<StockShareHolderExt>(1, 15);
		StockShareHolderExample example = new StockShareHolderExample();
		sshService.selectCliqueByPagination(example, page);
		List<StockShareHolderExt> sshList = page.getRecords();
		for (StockShareHolderExt ssh : sshList) {
			System.out.println(ssh.getHolderName());
		}
	}
}
