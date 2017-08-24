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

/**
 * 缩跌
 * @author zhuxb
 *
 */
@Component
public class ShrinkFallTask extends ShapeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(ShrinkFallTask.class);

	@Scheduled(cron="0 12 15 * * MON-FRI")
	public void execute(){
		logger.info("Shrink fall task begin...");
		long start = System.currentTimeMillis();
		String tradeDate = getTradeDate(null);
		execute(tradeDate);
		long end = System.currentTimeMillis();
		logger.info("Shrink fall task end, used" + (end-start)/1000);
	}
	
	public void execute(String tradeDate){
		List<KLine> klList = klService.selectByPositive(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
			if(isValid(kl)){
				idList.add(kl.getId());
			}
		}
		klService.updateShape(ShapeEnum.SHR_FALL, idList);
	}
	
	public boolean isValid(KLine kLine){
		if(kLine.getPercent() >= 0 || kLine.getPercent() <= -2){
			return false;
		}
		String symbol = kLine.getSymbol();
		String tradeDate = kLine.getTradeDate();
		List<KLine> tmpList = listByCloseDesc(symbol, tradeDate, 10);
		if(CollectionUtils.isEmpty(tmpList)){
			return false;
		}
		String maxDate = tmpList.get(0).getTradeDate();
		List<KLine> klList = listByTradeDateDesc(symbol, tradeDate, 10);
		double maxHigh = 0;
		double curLow = kLine.getLow();
		for (int i = 0; i < klList.size(); i++) {
			KLine kl = klList.get(i);
			if(kl.getMa10() == null || kl.getMa5() == null){
				return false;
			}
			if(kl.getMa10() > kl.getMa5()){
				return false;
			}
			
			if(i == 0){
				if(kl.getPercent() < 2){//昨天涨幅必须大于2%
					return false;
				}
				if(!kl.getTradeDate().equals(maxDate)){//昨日非本轮最高
					return false;
				}
				long yv = kl.getVolume();
				if(yv * 75 < kLine.getVolume() * 100){//今天比昨天缩量1/4以上
					return false;
				}
				if(kl.getLow() > curLow){//今天最低点不能低于昨日
					return false;
				}
				maxHigh = kl.getHigh();
			} else {
				if(kl.getHigh() > maxHigh){
					return false;
				}
			}
			
		}
		return true;
	}
	
}
