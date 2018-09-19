package com.gesangwu.spider.engine.kshape.task.test;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.engine.kshape.task.SkyBigVolumeTask;
import com.gesangwu.spider.engine.test.BaseTest;

/**
 * 飞天巨量
 * @author bran
 *
 */
public class SkyBigVolumeTaskTest extends BaseTest {

	@Resource
	private SkyBigVolumeTask task;
	
	@Test
	public void execute(){
		task.execute();
//		task.execute("2017-09-01");
	}
}
