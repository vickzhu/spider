package com.gesangwu.spider.engine.kshape.task;

import java.util.List;

import org.springframework.stereotype.Component;

import com.gandalf.framework.util.CalculateUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;

/**
 * 类地天板
 * @author bran
 *
 */
@Component
public class GroundSkyTask extends ShapeTask {
	
	public void execute(){
		execute(null);
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		int cpp = 500;
		Page<KLine> page = new Page<KLine>(1, cpp);
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		klService.selectByPagination(example, page);
		int totalPages = page.getTotalPages();
		List<KLine> klList = page.getRecords();
		execute(tradeDate, klList);
		
		for(int i = 2; i <= totalPages; i++){
			page = new Page<KLine>(i, cpp);
			klService.selectByPagination(example, page);
			klList = page.getRecords();
			execute(tradeDate, klList);
		}
	}
	public void execute(String tradeDate, List<KLine> klList){
//		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
			double high = kl.getHigh();
			double low = kl.getLow();
			double yest = kl.getYesterdayClose();
			if(high < yest || low > yest){
				continue;
			}
			double openScale = CalculateUtil.div(kl.getOpen(), yest, 3);
			double upScale = CalculateUtil.div(high, yest, 3);
			double downScale = CalculateUtil.div(yest, low, 3);
			double openDiff = CalculateUtil.sub(openScale, 1, 3);
			double upDiff = CalculateUtil.sub(upScale, 1, 3);
			double downDiff = CalculateUtil.sub(downScale, 1, 3);
			if(openDiff > 0.05){
				continue;
			}
			if(upDiff < 0.099){//涨幅小于9.9个点
				continue;
			}
			if(downDiff < 0.03){//跌幅小于3个点
				continue;
			}
			System.out.println(kl.getSymbol() + ":" + kl.getTradeDate());
		}
		
	}
}
