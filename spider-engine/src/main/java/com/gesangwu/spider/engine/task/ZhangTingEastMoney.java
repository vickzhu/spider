package com.gesangwu.spider.engine.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class ZhangTingEastMoney extends BaseTask {
	
	private static final Logger logger = LoggerFactory.getLogger(ZhangTingEastMoney.class);
	
	@Scheduled(cron="0 30 15 * * MON-FRI")
	public void execute(){
		Date now = new Date();
		if(!isTradeDate(sdf.format(now))){
			logger.error("非交易日！！！");
			return;
		}
		execute(null);
	}
	
	public void execute(String tradeDate){
		
	}

	public static void main(String[] args) {
		

	}

}
