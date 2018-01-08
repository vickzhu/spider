package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gandalf.framework.util.CalculateUtil;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;

@Component
public class YiZiTask extends ShapeTask {

	public void execute(){
		execute(null);
	}
	
	public void execute(String tradeDate){
		List<Long> idList = new ArrayList<Long>();
		tradeDate = getTradeDate(tradeDate);
		List<KLine> klList = klService.selectForYiZi(tradeDate);
		for (KLine kl : klList) {
			double diff = CalculateUtil.div(kl.getOpen(), kl.getYesterdayClose(), 3);
			double scale = CalculateUtil.sub(diff, 1, 3);
			if(scale > 0.099){
				idList.add(kl.getId());
			}
		}
		if(idList.size() > 0){
			klService.updateShape(ShapeEnum.YI_ZI, idList);
		}
	}
	
	
}
