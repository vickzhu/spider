package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.CalculateUtil;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;

/**
 * 大分歧
 * @author zhuxb
 * FIXME	前一天非一字涨停，今日非一字涨停，量能是昨天的一倍以上
 */
@Component
public class BigDivergenceTask extends ShapeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(BigDivergenceTask.class);

	@Scheduled(cron="0 18 15 * * MON-FRI")
	public void execute(){
		logger.info("Big Divergence task begin...");
		long start = System.currentTimeMillis();
		execute(null);
		long end = System.currentTimeMillis();
		logger.info("Big Divergence task end, used:" + (end-start) + "ms");
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<KLine> klList = klService.selectByPositive(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
			if(kl.getPercent() < -0.05){
				continue;
			}
			double high = kl.getHigh();
			double second = kl.getOpen() > kl.getClose() ? kl.getOpen() : kl.getClose();
			double diff = CalculateUtil.sub(high, second, 3);
			double scale = CalculateUtil.div(diff, second, 3);
			if(scale < 0.05){
				continue;
			}
			if(isValid(kl)){
				idList.add(kl.getId());
			}
		}
		if(CollectionUtils.isNotEmpty(idList)){
			klService.updateShape(ShapeEnum.BIG_DIVERGENCE, idList);
		}
	}
	
	private boolean isValid(KLine kLine){
		List<KLine> klList = listByTradeDateDesc(kLine.getSymbol(), kLine.getTradeDate(), 2);
		for (int i = 0; i < klList.size(); i++) {
			KLine kl = klList.get(i);
			if(kl.getMa5() == null || kl.getMa10() == null){
				return false;
			}
			if(i == 0){
				if(kl.getPercent()<0){
					return false;
				}
				if(kl.getHigh() > kLine.getHigh()){//今日没创新高
					return false;
				}
				if(kLine.getVolume() * 75 < kl.getVolume() * 100){
					return false;
				}
				if(kl.getMa5() > kLine.getMa5() || kl.getMa10() > kLine.getMa10()){
					return false;
				}
			} else {
				KLine next = klList.get(i-1);
				if(kl.getMa5() > next.getMa5() || kl.getMa10() > next.getMa10()){
					return false;
				}
			}
		}
		return true;
	}
	
}
