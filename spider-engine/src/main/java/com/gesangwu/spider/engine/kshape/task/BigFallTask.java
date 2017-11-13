package com.gesangwu.spider.engine.kshape.task;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.dao.model.KLine;

/**
 * 大跌
 * @author zhuxb
 *
 */
@Component
public class BigFallTask extends ShapeTask {

	public void execute(){
		execute(null);
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<KLine> klList = klService.selectByPositive(tradeDate);
		for (KLine kl : klList) {
			if(kl.getPercent() > -5 ){
				continue;
			}
			List<KLine> tmpList = listByTradeDateDesc(kl.getSymbol(), tradeDate, 1);
			if(CollectionUtils.isEmpty(tmpList)){
				continue;
			}
			KLine preKl = tmpList.get(0);
			if(preKl.getPercent() < 0){
				continue;
			}
			if(preKl.getLow() > kl.getHigh()){
				continue;
			}
			System.out.println(kl.getSymbol() + "：" + kl.getTradeDate());
		}
	}
}
