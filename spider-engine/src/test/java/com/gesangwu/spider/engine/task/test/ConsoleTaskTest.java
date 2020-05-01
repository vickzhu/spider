package com.gesangwu.spider.engine.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.CoverNegTask;
import com.gesangwu.spider.engine.kshape.task.FallRiseTask;
import com.gesangwu.spider.engine.kshape.task.GroundSkyTask;
import com.gesangwu.spider.engine.kshape.task.ImmortalGuiderTask;
import com.gesangwu.spider.engine.kshape.task.JumpUpTask;
import com.gesangwu.spider.engine.kshape.task.UpperShadowTask;
import com.gesangwu.spider.engine.kshape.task.YiZiTask;
import com.gesangwu.spider.engine.task.LianBanTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class ConsoleTaskTest extends BaseTest {
	
	@Resource
	private LianBanTask lbTask;
	@Resource
	private CoverNegTask cnTask;
	@Resource
	private FallRiseTask frTask;
	@Resource
	private GroundSkyTask gsTask;
	@Resource
	private ImmortalGuiderTask igTask;
	@Resource
	private JumpUpTask juTask;
	@Resource
	private UpperShadowTask usTask;
	@Resource
	private YiZiTask yzTask;
	
	@Test
	public void execute(){
		String tradeDate = "2020-04-30";
		long t1 = System.currentTimeMillis();
		lbTask.execute(tradeDate);
		long t2 = System.currentTimeMillis();
		System.out.println("LianBan task used:" + (t2 - t1));
		yzTask.execute(tradeDate);
		long t3 = System.currentTimeMillis();
		System.out.println("YiZi task used:" + (t3 - t2));
		igTask.execute(tradeDate);
		long t7 = System.currentTimeMillis();
		System.out.println("GroundSky task used:" + (t7 - t3));
		System.out.println("End task!!!");
	}

}
