package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.Date;
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
 * 最大涨幅大于7%，最大跌幅超过-2%，头一天涨停
 * @author
 *
 */
@Component
public class FallRiseTask extends ShapeTask {

	private static Logger logger = LoggerFactory.getLogger(FallRiseTask.class);
	
	//@Scheduled(cron="0 09 15 * * MON-FRI")
	public void execute(){
		Date now = new Date();
		if(!isTradeDate(sdf.format(now))){
			logger.error("非交易日！！！");
			return;
		}
		long start = System.currentTimeMillis();
		execute(null);
		long end = System.currentTimeMillis();
		logger.info("Fall Rise end, used:" + (end - start)+"ms");
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<KLine> klList = klService.selectByPositive(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
//			if("sh603895".equals(kl.getSymbol())){
//				System.out.println(".......");
//			}
			if(kl.getPercent() < 2){//收盘涨幅过小
				continue;
			}
			if(kl.getClose() < kl.getMa5()){
				continue;
			}
			double highScale = CalculateUtil.div(kl.getHigh(), kl.getYesterdayClose(), 3);
			double highDiff = CalculateUtil.sub(highScale, 1, 3);
			if(highDiff < 0.07){//最高点没超过+7%
				continue;
			}
			if(kl.getLow() > kl.getYesterdayClose()){//最低点高于昨天收盘价
				continue;
			}
			double lowScale = CalculateUtil.div(kl.getYesterdayClose(), kl.getLow(), 3);
			double lowDiff = CalculateUtil.sub(lowScale, 1, 3);
			if(lowDiff < 0.02){//跌幅小于-2%
				continue;
			}
			List<KLine> preklList = listByTradeDateDesc(kl.getSymbol(), tradeDate, 1);
			if(CollectionUtils.isEmpty(preklList)){
				continue;
			}
			KLine pre1 = preklList.get(0);
			if(pre1.getPercent() < 9.9){
				continue;
			}
			idList.add(kl.getId());
		}
		if(CollectionUtils.isNotEmpty(idList)){
			klService.updateShape(ShapeEnum.FALL_RISE, idList);
		}
	}
}
