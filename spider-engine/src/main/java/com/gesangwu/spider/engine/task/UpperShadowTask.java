package com.gesangwu.spider.engine.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.common.DecimalUtil;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.dao.model.UpperShadow;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.service.UpperShadowService;

//@Component
public class UpperShadowTask {
	
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
		execute(1);
		long end = System.currentTimeMillis();
		logger.info("Calc upper shadow used:" + (end-start) + "ms!");
	}
	
	public void execute(int curPage){
		Page<KLine> page = new Page<KLine>(curPage, 500);
		KLineExample example = new KLineExample();
		String tradeDate = getTradeDate();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		klService.selectByPagination(example, page);
		List<KLine> klList = page.getRecords();
		for (KLine kl : klList) {
			double high = kl.getHigh();
			double percent = kl.getPercent();
			double close = kl.getClose();
			double open = kl.getOpen();
			double low = kl.getLow();
			double yestdayClose = getYestdayClose(close, percent);
			if(high <= yestdayClose){
				continue;
			}
			double maxPercent = getMaxPercent(yestdayClose, high);
			if(maxPercent < 8){
				continue;
			}
			double diffPercent = maxPercent-percent;//红柱的情况
			double openPercent =  getMaxPercent(yestdayClose, open);
			if(open > close){//绿柱
				diffPercent = maxPercent - openPercent;
			}
			if(diffPercent < 5){
				continue;
			}
			double maxNegPercent = getMaxPercent(yestdayClose, low);//最大跌幅
			double negPercent = openPercent - maxNegPercent;//红柱，下影线百分比
			if(open > close){//绿柱
				negPercent = percent - maxNegPercent;
			}
			if(negPercent > 2){//下影线太长
				continue;
			}
			Company company = companyService.selectBySymbol(kl.getSymbol());
			if(company.getActiveMarketValue() == null){
				continue;
			}
			if(!StockUtil.isLittleMarketValue(company.getActiveMarketValue())){
				continue;
			}
			UpperShadow us = new UpperShadow();
			us.setActiveFloatMarket(DecimalUtil.format(company.getActiveMarketValue()/100000000, 2).doubleValue());
			us.setChgPercent(percent);
			us.setGmtCreate(new Date());
			us.setMaxChgPercent(DecimalUtil.format(maxPercent, 2).doubleValue());
			us.setShadowPercent(DecimalUtil.format(diffPercent, 2).doubleValue());
			us.setSymbol(kl.getSymbol());
			us.setTradeDate(kl.getTradeDate());
			usService.insert(us);
		}
		if(curPage < page.getTotalPages()){
			execute(++curPage);
		}
	}
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private String getTradeDate(){
		Date now = new Date();
		return sdf.format(now);
	}

	private static double getMaxPercent(double yestdayClose, double high){
		return (high - yestdayClose)*100/yestdayClose;
	}
	
	private static double getYestdayClose(double close, double percent){
		return 100*close/(100+percent);
	}
	
	public static void main(String[] args){
		double percent = 1.34d;
		double close = 25.76d;
		double high = 27.50d;
		double yestdayClose = getYestdayClose(close, percent);
		System.out.println(yestdayClose);
		System.out.println(getMaxPercent(yestdayClose, high));
	}
}
