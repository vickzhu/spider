package com.gesangwu.spider.engine.kshape.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.CalculateUtil;
import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;

/**
 * 底部埋伏
 * @author zhuxb
 *
 */
@Component
public class AmbushBottomTask extends ShapeTask {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Scheduled(cron="0 25 15 * * MON-FRI")
	public void execute(){
		execute(null);
	}
	
	public void execute(String tradeDate){
		int cpp = 200;
		if(StringUtil.isBlank(tradeDate)){
			Date now = new Date();
			tradeDate = sdf.format(now);
		}
		Page<KLine> page = new Page<KLine>(1, cpp);
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andPercentGreaterThan(0d);
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
		List<Long> idList = new ArrayList<Long>();
		for (KLine kl : klList) {
			boolean valid = isValid(tradeDate, kl.getSymbol());
			if(valid){
				idList.add(kl.getId());
			}
		}
		if(idList.size() > 0){			
			klService.updateShape(ShapeEnum.DI_BU.getCode(), idList);
		}
	}
	
	private double shadow = 0.05;
	
	public boolean isValid(String tradeDate, String symbol){
		boolean isValid = false;
		KLineExample example = new KLineExample();
		example.setOrderByClause("trade_date desc");
		example.setOffset(0);
		example.setRows(20);
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateLessThanOrEqualTo(tradeDate);
		List<KLine> klList = klService.selectByExample(example);
		boolean isMs = false;
//		boolean isBreak = false;
		double top = 0;
		double floor = 0;
		
		for (int i = klList.size()-1; i >=0; i--) {
			KLine kl = klList.get(i);
			if(kl.getMa5() == null || kl.getMa10() == null || kl.getMa20() == null){
				continue;
			}
			if(kl.getPercent() < -5 && kl.getMa5() < kl.getMa10() && kl.getMa5()< kl.getMa20()){
				isMs = true;
				double diff = CalculateUtil.mul(kl.getClose(), 0.05, 2);
				top = CalculateUtil.add(kl.getClose(), diff, 2);
				diff = CalculateUtil.mul(kl.getClose(), 0.03, 2);
				floor = CalculateUtil.sub(kl.getClose(), diff, 2);
				continue;
			}
			if(!isMs){//有里程碑才往下继续走
				continue;
			}
			if(kl.getClose() >= top || kl.getClose() <= floor){
				isMs = false;
//				isBreak = false;
				continue;
			}
//			if(Math.abs(kl.getPercent()) > 3){//有里程碑，并且单日涨跌幅大于3，直接不玩了
//				isMs = false;
//				isBreak = false;
//				continue;
//			}
			if(kl.getOpen() > kl.getClose()){//阴
				double scale = CalculateUtil.div(kl.getHigh(), kl.getOpen(), 3);
				double percent = Math.abs(CalculateUtil.sub(1, scale, 3));
				if(percent > shadow){
					isMs = false;
//					isBreak = false;
					continue;
				}
				scale = CalculateUtil.div(kl.getClose(), kl.getLow(), 3);
				percent = Math.abs(CalculateUtil.sub(1, scale, 3));
				if(percent > shadow){
					isMs = false;
//					isBreak = false;
					continue;
				}
			} else {//阳
				double scale = CalculateUtil.div(kl.getHigh(), kl.getClose(), 3);
				double percent = Math.abs(CalculateUtil.sub(1, scale, 3));
				if(percent > shadow){
					isMs = false;
//					isBreak = false;
					continue;
				}
				scale = CalculateUtil.div(kl.getOpen(), kl.getLow(), 3);
				percent = Math.abs(CalculateUtil.sub(1, scale, 3));
				if(percent > shadow){
					isMs = false;
//					isBreak = false;
					continue;
				}
			}
				
//			if(kl.getMa5() > kl.getMa10()){
//				isBreak = true;
//			} else {
//				isBreak = false;
//			}
		}
		if(isMs){
			isValid = true;
		}
		return isValid;
	}
	
}
