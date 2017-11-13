package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.CalculateUtil;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.service.UpperShadowService;

/**
 * 实体比上影线长的可以滚蛋了，量不能大于前一天太多，量不能大于前天一天太多，说明尼玛是冲高被套了
 * @author zhuxb
 *
 */
@Component
public class UpperShadowTask extends ShapeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(UpperShadowTask.class);
	
	@Resource
	private UpperShadowService usService;
	@Resource
	private KLineService klService;
	@Resource
	private CompanyService companyService;

	@Scheduled(cron = "0 24 15 * * MON-FRI")
	public void execute(){
		long start = System.currentTimeMillis();
		execute(null);
		long end = System.currentTimeMillis();
		logger.info("Calc upper shadow used:" + (end-start) + "ms!");
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<KLine> klList = klService.selectByPositive(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
//			if("sh601155".equals(kl.getSymbol())){
//				System.out.println("...........");
//			}
			if(kl.getClose() < kl.getMa5()){
				continue;
			}
			if(kl.getPercent() > 5){
				continue;
			}
			double high = kl.getHigh();
			double second = kl.getOpen() > kl.getClose()? kl.getOpen():kl.getClose();
			double scale = CalculateUtil.div(high, second, 3);
			double diff = CalculateUtil.sub(scale, 1, 3);
			if(diff < 0.05){
				continue;
			}
			if(high-second < Math.abs(kl.getOpen() - kl.getClose())){//上影小于实体
				continue;
			}
			idList.add(kl.getId());
		}
		klService.updateShape(ShapeEnum.UPPER_SHADOW, idList);
	}	
}
