package com.gesangwu.spider.engine.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.CalculateUtil;
import com.gesangwu.spider.biz.dao.model.Bidding;
import com.gesangwu.spider.biz.service.BiddingService;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.engine.common.BiddingHolder;

@Component
public class BiddingCalcTask {

	@Resource
	private CompanyService companyService;
	@Resource
	private BiddingService bdService;
	
	@Scheduled(cron = "40 25 9 * * MON-FRI")
	public void execute(){
		List<Bidding> bdList = new ArrayList<Bidding>();
		Map<String, Map<String, Bidding>> bdmmp = BiddingHolder.getBidMap();
		for(Map<String, Bidding> bdmp : bdmmp.values()){
			Bidding pre = null;
			for(Bidding bd : bdmp.values()){
				if(pre == null){
					pre = bd;
					continue;
				}
				int diff = bd.getVol() - pre.getVol();
				if(diff > 100000 ){//差1000手
					double amount = CalculateUtil.mul(bd.getPrice(), diff, 2);
					if(amount > 3000000){//超过300万
						if(pre.getPrice() > bd.getPrice()){//跌
							System.out.println(bd.getSymbol() + ":" + bd.getTradeTime() + ":" + amount +",卖");
						} else if(pre.getPrice() < bd.getPrice()){//涨
							System.out.println(bd.getSymbol() + ":" + bd.getTradeTime() + ":" + amount +",买");
						} else {
							if(pre.getSellSurplus() > 0){//买
								System.out.println(bd.getSymbol() + ":" + bd.getTradeTime() + ":" + amount +",买");
							} else {//卖
								System.out.println(bd.getSymbol() + ":" + bd.getTradeTime() + ":" + amount +",卖");
							}
							
						}
					}
				}
				pre = bd;
				bdList.add(bd);
				if(bdList.size() == 300){
					saveBidding(bdList);
				}
			}
		}
		if(CollectionUtils.isNotEmpty(bdList)){
			saveBidding(bdList);
		}
		
	}
	
	/**
	 * 备份
	 * @param bdList
	 */
	private void saveBidding(List<Bidding> bdList){
		bdService.batchInsert(bdList);
		bdList.clear();
	}
	
}
