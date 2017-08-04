package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.dao.model.KLine;

/**
 * 支撑线
 * @author zhuxb
 *
 */
@Component
public class SupportTask extends ShapeTask {

	public void execute(){
		execute(null);
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<KLine> klList = klService.selectForShape(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
			if(isValid(tradeDate, kl)){
				idList.add(kl.getId());
			}
			if(kl.getMa10() > kl.getMa5()){
				judge(kl);
			}
		}
	}
	
	/**
	 * 十日上，五日下，突破十日
	 * @param kl
	 */
	private void judge(KLine kl){
		if(kl.getPercent() < 3 || kl.getPercent() > 7){
			return;
		}
		if(kl.getOpen() > kl.getMa10()){
			return;
		}
		if(kl.getClose() < kl.getMa10()){
			return;
		}
		System.out.println(kl.getSymbol() + ":" + kl.getTradeDate());
	}
	
	
	
	public boolean isValid(String tradeDate, KLine kl){
		List<KLine> list = listByCloseDesc(kl.getSymbol(), tradeDate, 30);
		KLine maxKLine = list.get(0);
		KLine minKline = list.get(list.size() - 1);
		if(maxKLine.getTradeDate().compareTo(minKline.getTradeDate()) < 0){//最高点日期小于最低点日期，说明是上一波的高峰
			return false;
		}
		double close = kl.getClose();
		double max = maxKLine.getClose();
		double min = minKline.getClose();
		double h1 = (close - min)/min;
		if(h1 < 0.1){//值越小离底部越近
			return false;
		}
		double h2 = (close - max)/max;
		if(Math.abs(h2) > 0.1){//离最大值大于10%
			return false;
		}
		return true;
	}
	
}
