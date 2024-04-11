package com.gesangwu.spider.engine.task.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.KLineStatisService;
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
	@Resource
	private KLineStatisService ksService;
	
//	@Test
	public void initKLineStatis() {
		init("2022-11-16", "2024-04-09");
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private void init(String start, String end){
		Calendar c = Calendar.getInstance();
		try {			
			Date sd = sdf.parse(start);
			Date ed = sdf.parse(end);
			c.setTime(ed);
			c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
			ed = c.getTime();
			while (sd.before(ed)){
				String tradeDate = sdf.format(sd);
//				lbTask.ztCount(tradeDate);
//				dtTask.execute(tradeDate);
				hcTask.execute(tradeDate);
				c.setTime(sd);
				c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
				sd = c.getTime();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
//	@Test
	public void testDieTing() {
		dtTask.execute();
	}
	
//	@Test
	public void testHuiChe() {
		hcTask.execute();
	}
	
	@Test
	public void execute(){
//		String tradeDate = "2020-04-30";
		long t1 = System.currentTimeMillis();
		klineTask.execute();
		long t2 = System.currentTimeMillis();
		System.out.println("KLine task used:" + (t2 - t1));
		lbTask.execute();
		long t3 = System.currentTimeMillis();
		System.out.println("LianBan task used:" + (t3 - t2));
		dtTask.execute();
		long t4 = System.currentTimeMillis();
		System.out.println("DieTing task used:" + (t4 - t3));
		hcTask.execute();
		long t5 = System.currentTimeMillis();
		System.out.println("HuiChe task used:" + (t5 - t4));
		System.out.println("End task!!!");
	}

}
