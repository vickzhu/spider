package com.gesangwu.spider.engine.kshape.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;

import com.gandalf.framework.util.CalculateUtil;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.KLineService;

/**
 * 形态之父
 * @author zhuxb
 *
 */
public abstract class ShapeTask {
		
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
		if(klList.size() == 0){
			return false;
		}
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
	 * 查找截止时间指定天数的记录，并根据close倒序，不包含当天
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
	
	/**
	 * 在指定天数之内，是否为最高价
	 * @param kl
	 * @param days	指定天数
	 * @return
	 */
	public boolean isOnTop(KLine kl, int days){
		String symbol = kl.getSymbol();
		String tradeDate = kl.getTradeDate();
		KLine klMax = getMaxCLose(symbol, tradeDate, days);
		if(klMax == null){
			return false;
		}
		return kl.getClose() > klMax.getClose();
	}
	
	/**
	 * 是否在最高点附近,5个百分点以内
	 * @param kl
	 * @param days
	 * @return
	 */
	public boolean isAroundTop(KLine kl, int days){
		String symbol = kl.getSymbol();
		String tradeDate = kl.getTradeDate();
		KLine klMax = getMaxCLose(symbol, tradeDate, days);
		if(klMax == null){
			return false;
		}
		if(kl.getClose() >= klMax.getClose()){
			return true;
		}
		double scale = CalculateUtil.div(kl.getClose(), klMax.getClose(), 3);
		double percent  = CalculateUtil.sub(1, scale, 3);
		return percent < 0.05;
	}
	
	/**
	 * 获得指定时间段内最大价格
	 * @param symbol	
	 * @param tradeDate	截止时间
	 * @param days		往前计算的天数
	 * @return
	 */
	public KLine getMaxCLose(String symbol, String tradeDate, int days){
		String startDate = subDate(tradeDate, days);
		KLineExample example = new KLineExample();
		example.setOrderByClause("close desc");
		example.setOffset(0);
		example.setRows(1);
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateLessThan(tradeDate);
		criteria.andTradeDateGreaterThanOrEqualTo(startDate);
		List<KLine> klList = klService.selectByExample(example);
		if(CollectionUtils.isNotEmpty(klList)){
			return klList.get(0);
		} else {
			return null;
		}
	}
	
	public static String subDate(String tradeDate, int days) {
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
