package com.gesangwu.spider.engine.kshape.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.gandalf.framework.util.CalculateUtil;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.KLineService;

/**
 * K线形态，包括多头、空头、阳包阴等等
 * @author zhuxb
 *
 */
public class ShapeTask {
		
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	protected KLineService klService;
	
	public String getTradeDate(String tradeDate){
		String date = tradeDate;
		if(StringUtil.isBlank(date)){
			Date now = new Date();
			date = sdf.format(now);
		}
		return date;
	}
	
	/**
	 * 是否为向上走势
	 * @return
	 */
	public boolean isUpTrend(KLine kline, int days){
		List<KLine> klList = listByCloseDesc(kline.getSymbol(), kline.getTradeDate(), days);
		KLine high = klList.get(0);
		KLine low = klList.get(klList.size() - 1);
		if(high.getTradeDate().compareTo(low.getTradeDate()) < 0){//最高在最低左侧
			return false;
		}
		double curClose = kline.getClose();
		double maxClose = high.getClose();
		double scale = CalculateUtil.div(curClose, maxClose, 2);
		double diff = Math.abs(CalculateUtil.sub(scale, 1, 2));
		return diff < 0.1;
	}
	
	
	/**
	 * 查找截止时间指定天数的记录，并根据close倒序
	 * @param symbol
	 * @param tradeDate
	 * @param days
	 * @return
	 */
	public List<KLine> listByCloseDesc(String symbol, String tradeDate, int days){
		String startDate = subDate(tradeDate, days);
		KLineExample example = new KLineExample();
		example.setOffset(0);
		example.setRows(days);
		example.setOrderByClause("close desc");
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateLessThan(tradeDate);
		criteria.andTradeDateGreaterThanOrEqualTo(startDate);
		return klService.selectByExample(example);
	}
	
	/**
	 * 不包括tradeDate当天
	 * @param symbol
	 * @param tradeDate
	 * @param days
	 * @return
	 */
	public List<KLine> listByTradeDateDesc(String symbol, String tradeDate, int days){
		KLineExample example = new KLineExample();
		example.setOrderByClause("trade_date desc");
		example.setOffset(0);
		example.setRows(days);
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateLessThan(tradeDate);
		return klService.selectByExample(example);
	}
	
	private static String subDate(String tradeDate, int days) {
		try {
			Date date = sdf.parse(tradeDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DATE, -days);
			return sdf.format(c.getTime());
		} catch (ParseException e) {
			return null;
		}
	}
	
}
