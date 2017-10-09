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
import com.gesangwu.spider.biz.dao.model.KLineExample;

//TODO 前一日不能是最高点
@Component
public class CoverNegTask extends ShapeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(CoverNegTask.class);
	
	@Scheduled(cron="0 10 15 * * MON-FRI")
	public void execute(){
		logger.info("CoverNeg task begin...");
		long start = System.currentTimeMillis();
		execute(null);
		long end = System.currentTimeMillis();
		logger.info("CoverNeg task end, used:" + (end-start) + "ms");
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<KLine> klList = klService.selectCoverNeg(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
//			if("sz300224".equals(kl.getSymbol())){
//				System.out.println(".............");
//			}
			double secondHigh = kl.getOpen() > kl.getClose()?kl.getOpen():kl.getClose();
			double scale = CalculateUtil.div(kl.getHigh(), secondHigh, 3);
			double diff = CalculateUtil.sub(scale, 1, 3);
			if(diff < 0.02){
				continue;
			}
			KLine k1 = selectHigh(kl, 30);
			if(k1 == null){
				continue;
			}
			if(!chechHigh(kl, k1)){
				continue;
			}
			if(kl.getHigh() > k1.getHigh()){
				continue;
			}
			if(!isSecondHigh(kl, k1.getTradeDate())){//今天收最高点以来的次高点
				continue;
			}
			KLine k2 = selectHigh(kl, 90);
			if(!k1.getTradeDate().equals(k2.getTradeDate())){//最高点必须出现在30天内，时间太长不行
				continue;
			}
			idList.add(kl.getId());
//			boolean valid = isValid(kl);
//			if(valid){
//				System.out.println(kl.getSymbol());
//			}
		}
		if(idList.size() > 0){
			klService.updateShape(ShapeEnum.COVER_NEG, idList);
		}
	}
	
	/**
	 * 检查最高K线是否在两个交易日之前
	 * @param curK
	 * @param maxK
	 * @return
	 */
	private boolean chechHigh(KLine curK, KLine maxK){
		List<KLine> kList = listByTradeDateDesc(curK.getSymbol(), curK.getTradeDate(), 2);
		for (KLine kl : kList) {
			if(kl.getTradeDate().equals(maxK.getTradeDate())){//不得在近两日内
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 查询是否为最高点以来的第二高点
	 * @param kl
	 * @param startDate	最高点的时间
	 * @return
	 */
	private boolean isSecondHigh(KLine kl, String startDate){
		KLineExample example = new KLineExample();
		example.setOrderByClause("high desc");
		example.setOffset(0);
		example.setRows(1);
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(kl.getSymbol());
		criteria.andTradeDateGreaterThan(startDate);
		criteria.andTradeDateLessThanOrEqualTo(kl.getTradeDate());
		List<KLine> klList = klService.selectByExample(example);
		return kl.getTradeDate().equals(klList.get(0).getTradeDate());
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
	
	public boolean isValid(KLine kl){
		List<KLine> list = getKLList(kl.getSymbol(), kl.getTradeDate(), 1);
		for(int i = 0; i < list.size(); i++){
			KLine curK = list.get(i);
			if(i == 0){
				if(curK.getClose() < 0){
					return false;
				}
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
