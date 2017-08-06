package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.dao.model.KLine;

/**
 * 五日、十日均线粘合，集体并向上发散
 * @author bran
 *
 */
@Component
public class MaAdhesiveTask extends ShapeTask {

	public void execute(){
		execute(null);
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<KLine> klList = klService.selectForShape(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
			
		}
	}
	
}
