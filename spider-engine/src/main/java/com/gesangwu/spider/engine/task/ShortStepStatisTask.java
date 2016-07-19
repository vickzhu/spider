package com.gesangwu.spider.engine.task;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.dao.model.BankerTrace;
import com.gesangwu.spider.biz.dao.model.BankerTraceExample;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.BankerTraceService;
import com.gesangwu.spider.biz.service.KLineService;

/**
 * 碎步统计
 * @author zhuxb
 *
 */
@Component
public class ShortStepStatisTask {
	
	@Resource
	private BankerTraceService traceService;
	@Resource
	private KLineService kLineService;

	public void execute(){
		BankerTraceExample example = new BankerTraceExample();
		BankerTraceExample.Criteria criteria = example.createCriteria();
		criteria.andStepEqualTo(1);
		List<BankerTrace> traceList = traceService.selectByExample(example);
		for (BankerTrace bankerTrace : traceList) {
			KLineExample klExample = new KLineExample();
			KLineExample.Criteria klC = klExample.createCriteria();
			klC.andTransDateGreaterThan(bankerTrace.getMsDate());
			List<KLine> kLineList = kLineService.selectByExample(klExample);
			if(CollectionUtils.isEmpty(kLineList)){
				continue;
			}
						
		}
	}
}
