package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.CalculateUtil;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;

/**
 * 首阴
 * @author zhuxb
 *
 */
@Component
public class FirstNegativeTask extends ShapeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(FirstNegativeTask.class);
	
	private int incDays = 3;
	private double minInc = incDays * 0.05;//最小涨幅
	
	@Scheduled(cron="0 20 15 * * MON-FRI")
	public void execute(){
		logger.info("First Negative task begin...");
		long start = System.currentTimeMillis();
		execute(null);
		long end = System.currentTimeMillis();
		logger.info("First Negative task end, used:" + (end-start) + "ms");
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<KLine> klList = klService.selectByPositive(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
			double high = kl.getHigh();
			double low = kl.getLow();
			double amplitude = CalculateUtil.div((high - low), low, 2);
			if(amplitude < 0.07){
				continue;
			}
			if(kl.getPercent() > 0){
				if(kl.getOpen() < kl.getClose()){//非阴
					continue;
				}
			}
			double upShadow = CalculateUtil.div(high, kl.getClose(), 3);
			double upShadowScale = Math.abs(CalculateUtil.sub(upShadow, 1, 3));
			if(upShadowScale < 0.02){//套牢太少
				continue;
			}
			List<KLine> list = listByTradeDateDesc(kl.getSymbol(), tradeDate, incDays);
			KLine yesterday = list.get(0);
			if(kl.getHigh() <= yesterday.getHigh()){//今天最高点没有创新高
				continue;
			}
			boolean isFirst = true;
			for (KLine k : list) {
				if(k.getPercent() < 0 || k.getClose() < k.getOpen()){
					isFirst = false;
					break;
				}
			}
			if(!isFirst){
				continue;
			}
			double max = yesterday.getClose();
			double min = list.get(list.size()-1).getYesterdayClose();
			double raise = CalculateUtil.div((max - min), min, 2);
			if(raise < minInc){
				continue;
			}
			idList.add(kl.getId());
		}
		if(idList.size() > 0){
			klService.updateShape(ShapeEnum.FIRST_NEG, idList);
		}
	}
	
}
