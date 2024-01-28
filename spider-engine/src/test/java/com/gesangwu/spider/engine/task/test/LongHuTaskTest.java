package com.gesangwu.spider.engine.task.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.engine.common.LongHuTaskChannelEnum;
import com.gesangwu.spider.engine.task.LongHuSinaTask;
import com.gesangwu.spider.engine.task.LongHuTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class LongHuTaskTest extends BaseTest {

	@Resource
	private LongHuTask task;
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhdService;
	@Resource
	private LongHuSinaTask sinaTask;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Test
	public void execute(){
//		task.execute(null);
		Calendar c = Calendar.getInstance();
		c.set(2022, 10, 3);
		while(true) {
			if(c.getTimeInMillis() > System.currentTimeMillis()) {
				break;
			}
			Date d = c.getTime();
			String ds = sdf.format(d);
			System.out.println(ds);
			task.execute(ds, LongHuTaskChannelEnum.SINA);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			c.add(Calendar.DATE, 1);
		}
		System.out.println(c.getTime());
		//task.execute("2021-06-02",LongHuTaskChannelEnum.SINA);
//		LongHu longHu = lhService.selectByPrimaryKey(82345l);
//		task.fetchDetail(1, "04", longHu);
//		task.fetchDetail(longHu);
	}
	
//	@Test
//	public void isOrg(){
//		LongHuDetailExample example = new LongHuDetailExample();
//		LongHuDetailExample.Criteria criteria = example.createCriteria();
//		criteria.andTradeDateEqualTo("2017-07-24");
//		criteria.andSymbolEqualTo("sz300137");
//		List<LongHuDetail> lhdList = lhdService.selectByExample(example);
//		boolean isOrg = sinaTask.isOrg(lhdList);
//		System.out.println(isOrg);
//	}
}
