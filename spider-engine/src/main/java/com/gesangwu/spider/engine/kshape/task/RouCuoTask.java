package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;

/**
 * 揉搓线
 * <ol>
 * <li>多头</li>
 * <li>真阴线</li>
 * <li>跳空低开</li>
 * <li>收盘价低于五日线</li>
 * </ol>
 * @author zhuxb
 *
 */
@Component
public class RouCuoTask extends ShapeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(RouCuoTask.class);

	@Scheduled(cron="0 21 15 * * MON-FRI")
	public void execute(){
		logger.info("Rou Cuo task begin...");
		long start = System.currentTimeMillis();
		execute(null);
		long end = System.currentTimeMillis();
		logger.info("Rou Cuo task end, used:" + (end-start) + "ms");
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<KLine> klList = klService.selectByPositive(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
			if(kl.getPercent() >= 0){
				continue;
			}
			if(kl.getOpen() < kl.getClose()){
				continue;
			}
			if(kl.getClose() > kl.getMa5()){
				continue;
			}
			if(kl.getOpen() >= kl.getYesterdayClose()){
				continue;
			}
			List<KLine> preKlList = listByTradeDateDesc(kl.getSymbol(), kl.getTradeDate(), 2);
			KLine pre1 = preKlList.get(0);
			if(pre1.getPercent() > 0){
				continue;
			}
			if(pre1.getClose() < pre1.getMa5()){
				continue;
			}
			if(pre1.getOpen() < pre1.getClose()){
				continue;
			}
			KLine pre2 = preKlList.get(1);
			if(pre2.getClose() < pre2.getMa5()){
				continue;
			}
			if(pre2.getOpen() > pre2.getClose()){
				continue;
			}
			if(kl.getVolume() > pre2.getVolume() || pre1.getVolume() > pre2.getVolume()){
				continue;
			}
			idList.add(kl.getId());
		}
		klService.updateShape(ShapeEnum.ROU_CUO, idList);
	}
}
