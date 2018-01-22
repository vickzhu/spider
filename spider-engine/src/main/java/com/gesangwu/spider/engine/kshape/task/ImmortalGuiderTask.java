package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.CalculateUtil;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.service.UpperShadowService;

/**
 * 仙人指路
 * 实体比上影线长的可以滚蛋了，量不能大于前一天太多，量不能大于前天一天太多，说明尼玛是冲高被套了
 * @author zhuxb
 *
 */
@Component
public class ImmortalGuiderTask extends ShapeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(ImmortalGuiderTask.class);
	
	@Resource
	private UpperShadowService usService;
	@Resource
	private KLineService klService;
	@Resource
	private CompanyService companyService;

	@Scheduled(cron = "0 17 15 * * MON-FRI")
	public void execute(){
		long start = System.currentTimeMillis();
		execute(null);
		long end = System.currentTimeMillis();
		logger.info("Calc Immortal Guider used:" + (end-start) + "ms!");
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<KLine> klList = klService.selectByPositive(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
//			if("sz002878".equals(kl.getSymbol())){
//				System.out.println("...........");
//			}
			if(kl.getPercent() < 0){//真阴
				continue;
			}
			if(kl.getClose() < kl.getOpen()){//假阴
				continue;
			}
			if(kl.getPercent() > 5){
				continue;
			}
			double high = kl.getHigh();
			double second = kl.getOpen() > kl.getClose()? kl.getOpen():kl.getClose();
			double scale = CalculateUtil.div(high, second, 3);
			double diff = CalculateUtil.sub(scale, 1, 3);
			if(diff < 0.02){
				continue;
			}
			if(high-second < Math.abs(kl.getOpen() - kl.getClose())){
				continue;
			}
			KLine k1 = selectHigh(kl, 90);
			if(k1 == null){
				continue;
			}
			if(k1.getHigh() > kl.getHigh()){
				continue;
			}
			List<KLine> kLineList = getKLList(kl.getSymbol(), tradeDate, 2);
			KLine kk = kLineList.get(0);
			if(kk.getPercent() < 2){
				continue;
			}
			if(kl.getLow() < kk.getLow()){
				continue;
			}
			if(2 * kl.getVolume() > 3 * kk.getVolume()){
				continue;
			}
			KLine k2 = kLineList.get(1);
			KLine k3 = selectHigh(k2, 90);
			if(k2.getHigh() > k3.getHigh()){
				continue;
			}
			idList.add(kl.getId());
		}
		klService.updateShape(ShapeEnum.IMMORTAL_GUIDER, idList);
	}
	
	private KLine selectHigh(KLine kl, int days){
		String symbol = kl.getSymbol();
		String tradeDate = kl.getTradeDate();
		String startDate = subDate(tradeDate, days);
		KLineExample example = new KLineExample();
		example.setOrderByClause("high desc");
		example.setOffset(0);
		example.setRows(1);
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateLessThan(tradeDate);
		criteria.andTradeDateGreaterThanOrEqualTo(startDate);
		List<KLine> klList = klService.selectByExample(example);
		return CollectionUtils.isEmpty(klList) ? null : klList.get(0);
	}
	
	private List<KLine> getKLList(String symbol, String tradeDate, int days){
		KLineExample example = new KLineExample();
		example.setOrderByClause("trade_date desc");
		example.setOffset(0);
		example.setRows(days);
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateLessThan(tradeDate);
		return klService.selectByExample(example);
	}
	
}
