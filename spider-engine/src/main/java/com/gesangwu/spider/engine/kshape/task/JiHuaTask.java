package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;

/**
 * 季华6路，这个还是不是很靠谱
 * @author zhuxb
 *
 */
//@Component
public class JiHuaTask extends ShapeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(JiHuaTask.class);

//	@Scheduled(cron="0 16 15 * * MON-FRI")
	public void execute(){
		logger.info("JiHua task begin...");
		long start = System.currentTimeMillis();
		execute(null);
		long end = System.currentTimeMillis();
		logger.info("JiHua task end, used:" + (end-start) + "ms");
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		criteria.andPercentLessThan(-3d);
		List<KLine> klList = klService.selectByExample(example);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
			if(kl.getOpen() < kl.getClose()){//泛红肯定不行滴
				continue;
			}
			if(checkPre(kl)){
				idList.add(kl.getId());
			}
		}
		if(CollectionUtils.isNotEmpty(idList)){
//			klService.updateShape(ShapeEnum.JI_HUA, idList);
		}
	}
	
	public boolean checkPre(KLine kl){
		List<KLine> klList = listByTradeDateDesc(kl.getSymbol(), kl.getTradeDate(), 3);
		KLine pre1 = klList.get(0);
		if(pre1.getPercent() > 3d || pre1.getPercent() < -5d){//涨太多跌太多都不行
			return false;
		}
		if(pre1.getOpen() < pre1.getClose()){//泛红肯定不行滴
			return false;
		}
		KLine pre2 = klList.get(1);
		if(pre2.getPercent() < 9){
			return false;
		}
		KLine pre3 = klList.get(2);
		if(pre3.getPercent() > 5){
			return false;
		}
		return true;
	}
}
