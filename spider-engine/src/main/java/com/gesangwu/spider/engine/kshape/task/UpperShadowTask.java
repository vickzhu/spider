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

@Component
public class UpperShadowTask extends ShapeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(UpperShadowTask.class);
	
	@Resource
	private UpperShadowService usService;
	@Resource
	private KLineService klService;
	@Resource
	private CompanyService companyService;

	@Scheduled(cron = "0 15 16 * * MON-FRI")
	public void execute(){
		long start = System.currentTimeMillis();
		execute(null);
		long end = System.currentTimeMillis();
		logger.info("Calc upper shadow used:" + (end-start) + "ms!");
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<KLine> klList = klService.selectForShape(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
			if(kl.getPercent() < 0){//真阴
				continue;
			}
			if(kl.getClose() < kl.getOpen()){//假阴
				continue;
			}
			if(kl.getPercent() > 7){
				continue;
			}
			if(kl.getClose() < kl.getMa5() || kl.getClose() < kl.getMa10()){
				continue;
			}
			double high = kl.getHigh();
			double second = kl.getOpen() > kl.getClose()? kl.getOpen():kl.getClose();
			double scale = CalculateUtil.div(high, second, 3);
			double diff = CalculateUtil.sub(scale, 1, 3);
			if(diff < 0.02){
				continue;
			}
			List<KLine> kLineList = getKLList(kl.getSymbol(), tradeDate, 30);
			boolean v1 = false;//收盘价不能高于之前的最高价
			boolean v2 = true;//最高价比之前的最高价都高
			for(int i = 0; i < kLineList.size(); i++){
				KLine ykl = kLineList.get(i);
				if(kl.getClose() < ykl.getHigh()){
					v1 = true;
				}
				if(kl.getHigh() < ykl.getHigh()){
					v2 = false;
					break;
				}
			}
			if(v1 && v2){
				idList.add(kl.getId());
			}
		}
		klService.updateShape(ShapeEnum.UPPER_SHADOW, idList);
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
