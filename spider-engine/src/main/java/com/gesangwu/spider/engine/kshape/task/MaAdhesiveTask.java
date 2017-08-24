package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.CalculateUtil;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;

/**
 * 五日、十日均线粘合，集体并向上发散 TODO 是否改为最高点接近近一年的历史高点(1-2个点)，参考安洁科技和中炬高新2017-08-11
 * @author bran
 *
 */
@Component
public class MaAdhesiveTask extends ShapeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(MaAdhesiveTask.class);

	@Scheduled(cron="0 15 15 * * MON-FRI")
	public void execute(){
		logger.info("Ma Adhesive task begin...");
		long start = System.currentTimeMillis();
		execute(null);
		long end = System.currentTimeMillis();
		logger.info("Ma Adhesive task end, used:" + (end-start) + "ms");
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<KLine> klList = klService.selectForShape(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
			if(kl.getClose() < kl.getMa5() && kl.getClose() < kl.getMa10()){
				continue;
			}
			double scale = CalculateUtil.div(kl.getMa5(), kl.getMa10(), 3);
			double diff = Math.abs(CalculateUtil.sub(scale, 1, 3));
			if(diff > 0.005) {
				continue;
			}
			boolean valid = isValid(kl);
			if(valid){
				idList.add(kl.getId());
			}
		}
		if(idList.size() > 0){
			klService.updateShape(ShapeEnum.MA_ADH, idList);
		}
	}
	
	public boolean isValid(KLine kl){
		if(!isOnTop(kl)){
			return false;
		}
		List<KLine> list = getKLList(kl.getSymbol(), kl.getTradeDate());
		if(!isUpTrend(kl, 30)){
			return false;
		}
		for(int i = 0; i < list.size(); i++){
			KLine curK = list.get(i);
			if(i == 0){
				if(kl.getMa5() < curK.getMa5() || kl.getMa10() < curK.getMa10()){
					return false;
				}
			} else {
				KLine nextK = list.get(i-1);
				if(curK.getMa5() > nextK.getMa5() || curK.getMa10() > nextK.getMa10()){
					return false;
				}
			}
		}
		return true;
	}
	
	private List<KLine> getKLList(String symbol, String tradeDate){
		KLineExample example = new KLineExample();
		example.setOrderByClause("trade_date desc");
		example.setOffset(0);
		example.setRows(2);
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateLessThan(tradeDate);
		return klService.selectByExample(example);
	}
	
}
