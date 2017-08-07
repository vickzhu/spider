package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;

/**
 * 双突破，十日在上，五日在下，在前五日之内曾经为多头，单日3%以上涨幅，突破十日线，开盘价在十日线以下
 * @author bran
 *
 */
@Component
public class DoubleBreakTask extends ShapeTask {
	
	public void execute(){
		execute(null);
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<KLine> klList = klService.selectForShape(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
			if(!isBreak(kl)){
				continue;
			}
			List<KLine> list = getLatestKL(kl.getSymbol(), tradeDate);
			for(int i=0; i < list.size(); i++){
				KLine k = list.get(i);
				if(i == 0){
					if(k.getMa5() > k.getMa10()){//前一天为多头
						break;
					}
				}else {
					if(k.getMa5() > k.getMa10() && k.getMa10() > k.getMa20()){//出现多头
						System.out.println(kl.getSymbol() + ":" + kl.getTradeDate());
						idList.add(kl.getId());
						break;
					}
				}
			}
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
	 * 十日上，五日下，突破十日
	 * @param kl
	 */
	private boolean isBreak(KLine kl){
		if(kl.getPercent() < 3 || kl.getPercent() > 7){
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
