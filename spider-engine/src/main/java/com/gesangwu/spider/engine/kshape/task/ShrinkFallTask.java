package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;

/**
 * 缩跌：缩跌前一日跳空高开的不要搞
 * TODO SELECT COUNT(*) FROM k_line WHERE ma5>ma10 AND ma10>ma20 AND trade_date='2017-09-07' AND percent<=0 AND CLOSE>ma5;
 * 1、多头刚发散，并且刚刚创历史新高
 * 2、持续多日放量创新高
 * 缩量1/3以上，跌幅两个点以内。对于宇宙级龙头，缩量1/4以上也行，不过这种票一般复盘能发现
 * @author zhuxb
 *
 */
@Component
public class ShrinkFallTask extends ShapeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(ShrinkFallTask.class);

	@Scheduled(cron="0 20 15 * * MON-FRI")
	public void execute(){
		logger.info("Shrink fall task begin...");
		long start = System.currentTimeMillis();
		execute(null);
		long end = System.currentTimeMillis();
		logger.info("Shrink fall task end, used:" + (end-start) + "ms");
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
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
		if(kLine.getOpen() > kLine.getYesterdayClose()){
			return false;
		}
		String symbol = kLine.getSymbol();
		String tradeDate = kLine.getTradeDate();
		List<KLine> klList = listByTradeDateDesc(symbol, tradeDate, 3);
		double maxHigh = 0;
		double curLow = kLine.getLow();
		double curHigh = kLine.getHigh();
		for (int i = 0; i < klList.size(); i++) {
			KLine kl = klList.get(i);
			if(kl.getMa10() == null || kl.getMa5() == null){
				return false;
			}
			if(kl.getMa10() > kl.getMa5()){
				return false;
			}
			if(i == 0){
//				if("sz300409".equals(kl.getSymbol())){
//					System.out.println(".....");
//				}
				if(kl.getPercent() < 1){//昨天涨幅必须大于1%
					return false;
				}
				if(!isOnTop(kl, 90)){
					return false;
				}
				long yv = kl.getVolume();
				if(yv * 2 < kLine.getVolume() * 3){//今天比昨天缩量1/3以上
					return false;
				}
				if(kl.getHigh() < curHigh || kl.getLow() > curLow){
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
