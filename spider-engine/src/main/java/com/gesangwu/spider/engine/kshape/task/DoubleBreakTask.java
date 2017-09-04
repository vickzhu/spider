package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;

/**
 * 双突破，十日在上，五日在下，在前五日之内曾经为多头，单日3%以上涨幅，突破十日线，开盘价在十日线以下
 * @author bran
 *
 */
@Component
public class DoubleBreakTask extends ShapeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(DoubleBreakTask.class);
	
	@Scheduled(cron="0 10 15 * * MON-FRI")
	public void execute(){
		logger.info("Double Break task begin...");
		long start = System.currentTimeMillis();
		execute(null);
		long end = System.currentTimeMillis();
		logger.info("Double Break task end, used:" + (end-start) + "ms");
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<KLine> klList = klService.selectForShape(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
			if(!isBreak(kl)){
				continue;
			}
			if(!isAroundTop(kl, 365)){
				continue;
			}
			List<KLine> list = getLatestKL(kl.getSymbol(), tradeDate);
			for(int i=0; i < list.size(); i++){
				KLine k = list.get(i);
				if(k.getMa5() > k.getMa10() && k.getMa10() > k.getMa20()){//五日内出现多头
//					System.out.println(kl.getSymbol() + ":" + kl.getTradeDate());
					idList.add(kl.getId());
					break;
				}
			}
		}
		if(klList.size() > 0){
			klService.updateShape(ShapeEnum.DOUBLE_BREAK, idList);
		}
	}
	
	public List<KLine> getLatestKL(String symbol, String tradeDate){
		KLineExample example = new KLineExample();
		example.setOrderByClause("trade_date desc");
		example.setOffset(0);
		example.setRows(5);
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateLessThan(tradeDate);
		return klService.selectByExample(example);
	}
	
	/**
	 * 当日涨幅1%~7%之间，十日上，五日下，突破十日
	 * @param kl
	 */
	private boolean isBreak(KLine kl){
		if(kl.getPercent() < 1 || kl.getPercent() > 8){
			return false;
		}
		if(kl.getMa5() > kl.getMa10()){
			return false;
		}
		if(kl.getOpen() > kl.getMa10()){
			return false;
		}
		if(kl.getClose() < kl.getMa10()){
			return false;
		}
		return true;
	}
	
}
