package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.CalculateUtil;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;

/**
 * 低吸
 * 1、三日内均为多头
 * 2、前一日收盘价在五日线之上
 * 3、当天开盘价在-2%之上
 * 4、当天最低点在五日线以下3%以上
 * @author
 *
 */
//@Component
public class DiXiTask extends ShapeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(DiXiTask.class);

//	@Scheduled(cron="0 07 15 * * MON-FRI")
	public void execute(){
		logger.info("DiXi task begin...");
		long start = System.currentTimeMillis();
		execute(null);
		long end = System.currentTimeMillis();
		logger.info("DiXi task end, used:" + (end-start) + "ms");
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		List<KLine> klList = klService.selectByPositive(tradeDate);
		for (KLine kl : klList) {
//			if("sh601882".equals(kl.getSymbol())){
//				System.out.println("...........");
//			}
			if(kl.getMa30() == null){
				continue;
			}
			double open = kl.getOpen();
			if(kl.getYesterdayClose() == null){
				continue;
			}
			double yest = kl.getYesterdayClose();
			if(open < yest){//开绿
				double diff = CalculateUtil.sub(yest, open, 3);
				double scale = CalculateUtil.div(diff, yest, 3);
				if(scale > 0.05){//低开5个点以上，不予考虑
					continue;
				}
			}
			double low = kl.getLow();
			if(low > kl.getMa5()){
				continue;
			}
			double diff = CalculateUtil.sub(kl.getMa5(), low, 3);
			double scale = CalculateUtil.div(diff, kl.getMa5(), 3);
			if(scale < 0.03){
				continue;
			}
			if(!checkPre(kl.getSymbol(), tradeDate)){
				continue;
			}
			idList.add(kl.getId());
		}
		if(CollectionUtils.isNotEmpty(idList)){
//			klService.updateShape(ShapeEnum.DI_XI, idList);
		}
	}
	
	public boolean checkPre(String symbol, String tradeDate){
		List<KLine> tmpList = listByTradeDateDesc(symbol, tradeDate, 1);
//		for (KLine kLine : tmpList) {
//			if(kLine.getMa30() == null){//排除次新
//				return false;
//			}
//			if(kLine.getMa5() < kLine.getMa10() || kLine.getMa10() < kLine.getMa20()){
//				return false;
//			}
//		}
		KLine kl = tmpList.get(0);
		if(kl.getMa30() == null){
			return false;
		}
		if(kl.getClose() < kl.getMa5()){
			return false;
		}
		if(kl.getLow() < kl.getMa5()){
			double diff = CalculateUtil.sub(kl.getMa5(), kl.getLow(), 3);
			double scale = CalculateUtil.div(diff, kl.getMa5(), 3);
			if(scale < 0.03){//最低点已经小于五日线一下3个点，很尴尬了
				return false;
			}
		}
		return true;
	}
	
}
