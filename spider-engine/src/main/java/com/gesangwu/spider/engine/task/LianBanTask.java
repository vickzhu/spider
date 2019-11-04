package com.gesangwu.spider.engine.task;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.LianBanStatus;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.dao.model.LianBan;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.service.LianBanService;

/**
 * 处理连板
 * @author bran
 *
 */
@Component
public class LianBanTask extends BaseTask {
	
	private static final Logger logger = LoggerFactory.getLogger(LianBanTask.class);
	
	@Resource
	private LianBanService lbService;
	@Resource
	private CompanyService compnayService;
	@Resource
	private KLineService klService;
	
	@Scheduled(cron="0 30 15 * * MON-FRI")
	public void execute(){
		execute(null);
	}
	
	public void execute(String tradeDate){
		try {			
			tradeDate = getTradeDate(tradeDate);
		} catch (Exception e) {
			logger.info("交易日异常：" + tradeDate, e.getMessage());
			return;
		}
		List<KLine> klList = getKLineList(tradeDate);
		Date date = new Date();
		List<LianBan> lbList = new ArrayList<LianBan>();
		for (KLine kl : klList) {
			if(kl.getSymbol().startsWith("sh688")){
				continue;
			}
			Company company = compnayService.selectBySymbol(kl.getSymbol());
			if(company == null){
				continue;
			}
			LianBan preLianBan = getPreLianBan(company, tradeDate);
			int days = 1;
			Long plate = null;
			String reason = null;
			if(preLianBan != null){
				days = preLianBan.getDays() + 1;
				plate = preLianBan.getPlate();
				reason = preLianBan.getReason();
			}
			LianBan lb = new LianBan();
			lb.setDays(days);
			lb.setGmtCreate(date);
			lb.setPercent(kl.getPercent());
			lb.setShape(null);
			lb.setStatus(LianBanStatus.ZT.getCode());
			lb.setSymbol(kl.getSymbol());
			lb.setStockName(company.getStockName());
			lb.setTradeDate(tradeDate);
			lb.setPlate(plate);
			lb.setReason(reason);
			lbList.add(lb);
		}
		if(CollectionUtils.isNotEmpty(lbList)){
			lbService.batchInsert(lbList);
		}
	}
	
	private LianBan getPreLianBan(Company company, String tradeDate){
		String symbol = company.getSymbol();
		String stockName = company.getStockName();
		String preDate1 = getPreTradeDate(tradeDate);
		LianBan preLianBan1 = lbService.selectByTradeDate(symbol, preDate1);
		if(preLianBan1 == null || preLianBan1.getStatus() != LianBanStatus.ZT.getCode()){//前一日非涨停，则计算前第二日
			String preDate2 = getPreTradeDate(preDate1);
			LianBan preLianBan2 = lbService.selectByTradeDate(symbol, preDate2);
			if(preLianBan2 != null && preLianBan2.getStatus() == LianBanStatus.ZT.getCode()){//前第二个交易日为涨停
				
				KLine kl = klService.selectByDate(symbol, preDate1);
				
				LianBan lb = new LianBan();
				lb.setGmtCreate(new Date());
				if(kl != null){
					lb.setPercent(kl.getPercent());
				}
				lb.setSymbol(symbol);
				lb.setStockName(stockName);
				lb.setStatus(LianBanStatus.FZT.getCode());
				lb.setTradeDate(preDate1);
				lbService.insert(lb);

				return preLianBan2;
			}
		} else {
			return preLianBan1;
		}
		return null;
	}
	
	private List<KLine> getKLineList(String tradeDate){
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		criteria.andPercentGreaterThanOrEqualTo(9.9d);
		return klService.selectByExample(example);
	}

	private String getTradeDate(String tradeDate){
		String date = tradeDate;
		if(StringUtil.isBlank(date)){
			Date now = new Date();
			date = sdf.format(now);
		}
		if(!isTradeDate(date)){
			throw new RuntimeException("法定节假日无相关数据！！！");
		}
		return date;
	}
	
	private String getPreTradeDate(String tradeDate){
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(tradeDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int day = c.get(Calendar.DATE); 
		c.set(Calendar.DATE, day-1);
		String preDate = sdf.format(c.getTime());
		if(!isTradeDate(preDate)){//非交易日
			return getPreTradeDate(preDate);
		}
		return preDate;
	}
	
}
