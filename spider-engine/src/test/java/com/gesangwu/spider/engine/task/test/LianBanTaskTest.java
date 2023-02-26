package com.gesangwu.spider.engine.task.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.task.LianBanTask;
import com.gesangwu.spider.engine.test.BaseTest;

public class LianBanTaskTest extends BaseTest {
	
	@Resource
	private LianBanTask task;
	
//	@Test
	public void execute(){
//		task.execute();
		task.execute("2022-11-11");
	}
	
	@Test
	public void init(){
		init("2022-11-15","2022-12-10");
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 闭区间
	 * @param start
	 * @param end
	 */
	private void init(String start, String end){
		Calendar c = Calendar.getInstance();
		try {			
			Date sd = sdf.parse(start);
			Date ed = sdf.parse(end);
			c.setTime(ed);
			c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
			ed = c.getTime();
			while (sd.before(ed)){
				task.execute(sdf.format(sd));
				c.setTime(sd);
				c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
				sd = c.getTime();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
