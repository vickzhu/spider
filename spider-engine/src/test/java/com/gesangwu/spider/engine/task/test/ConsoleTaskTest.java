package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.DieTingTask;
import com.gesangwu.spider.engine.task.HuiCheTask;
import com.gesangwu.spider.engine.task.KLineSinaTask;
import com.gesangwu.spider.engine.task.LianBanTask;
import com.gesangwu.spider.engine.task.LongHuTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class ConsoleTaskTest extends BaseTest {
	
	@Resource
	private KLineSinaTask klineTask;
	@Resource
	private LianBanTask lbTask;
	@Resource
	private LongHuTask lhTask;
	@Resource
	private DieTingTask dtTask;
	@Resource
	private HuiCheTask hcTask;
	
//	@Test
	public void testDieTing() {
		dtTask.execute();
	}
	
	@Test
	public void testHuiChe() {
		hcTask.execute();
	}
	
//	@Test
	public void execute(){
//		String tradeDate = "2020-04-30";
		long t1 = System.currentTimeMillis();
		klineTask.execute();
		long t2 = System.currentTimeMillis();
		System.out.println("KLine task used:" + (t2 - t1));
		lbTask.execute();
		long t3 = System.currentTimeMillis();
		System.out.println("LianBan task used:" + (t3 - t2));
//		lhTask.execute();
		long t4 = System.currentTimeMillis();
		System.out.println("LongHu task used:" + (t4 - t3));
		System.out.println("End task!!!");
	}

}
