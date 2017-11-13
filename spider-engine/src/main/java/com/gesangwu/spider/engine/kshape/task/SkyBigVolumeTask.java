package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;

@Component
public class SkyBigVolumeTask extends ShapeTask {

	public void execute(){
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateGreaterThanOrEqualTo("2017-09-01");
		criteria.andPercentGreaterThan(9.9);
		criteria.andMa20IsNotNull();
		List<KLine> klList = klService.selectByExample(example);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
			Long id = getId(kl);
			if(id == null){
				continue;
			}
			idList.add(id);
		}
		klService.updateShape(ShapeEnum.SKY_BIG_VOLUME, idList);
	}
	
	private Long getId(KLine kl){
		KLine kLine = getNextK(kl.getSymbol(), kl.getTradeDate());
		if(kLine == null){
			return null;
		}
		if(kl.getHigh() > kLine.getLow()){
			return null;
		}
		if(kl.getVolume()*3 < kLine.getVolume()){
			return kLine.getId();
		}
		return null;
	}
	
	private KLine getNextK(String symbol, String tradeDate){
		KLineExample example = new KLineExample();
		example.setOffset(0);
		example.setRows(1);
		example.setOrderByClause("trade_date");
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateGreaterThan(tradeDate);
		List<KLine> klList = klService.selectByExample(example);
		return CollectionUtils.isEmpty(klList) ? null : klList.get(0);
	}
	
	public void execute(String tradeDate){
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		criteria.andPercentGreaterThan(9.9);
		criteria.andMa20IsNotNull();
		List<KLine> klList = klService.selectByExample(example);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
			Long id = getId(kl);
			if(id == null){
				continue;
			}
			idList.add(id);
		}
		klService.updateShape(ShapeEnum.SKY_BIG_VOLUME, idList);
	}
}
