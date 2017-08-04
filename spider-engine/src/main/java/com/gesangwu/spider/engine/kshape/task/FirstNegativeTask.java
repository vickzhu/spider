package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

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
	
	public void execute(){
		execute(null);
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
			List<KLine> list = listByTradeDateDesc(kl.getSymbol(), tradeDate, 3);
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
//			double max = yesterday.getClose();
//			double min = list.get(list.size()-1).getClose();
//			double raise = CalculateUtil.div((max - min), min, 2);
//			if(raise < 0.15){
//				continue;
//			}
			idList.add(kl.getId());
		}
		klService.updateShape(ShapeEnum.FIRST_NEG.getCode(), idList);
	}
	
}
