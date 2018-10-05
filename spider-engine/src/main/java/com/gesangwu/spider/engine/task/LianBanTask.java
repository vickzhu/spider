package com.gesangwu.spider.engine.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.dao.model.LianBan;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.HolidayService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.service.LianBanService;

/**
 * 处理连板
 * @author bran
 *
 */
@Component
public class LianBanTask {
	
	@Resource
	private LianBanService lbService;
	@Resource
	private CompanyService compnayService;
	@Resource
	private KLineService klService;
	@Resource
	private HolidayService holidayService;	
	
	public void execute(){
		execute(null);
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<KLine> klList = getKLineList(tradeDate);
		Date date = new Date();
		List<LianBan> lbList = new ArrayList<LianBan>();
		for (KLine kl : klList) {
			LianBan lb = new LianBan();
			lb.setDays(1);
			lb.setGmtCreate(date);
			lb.setPercent(kl.getPercent());
			lb.setShape(null);
			lb.setStatus(1);
			lb.setSymbol(kl.getSymbol());
			Company company = compnayService.selectBySymbol(kl.getSymbol());
			if(company == null){
				continue;
			}
			lb.setStockName(company.getStockName());
			lb.setTradeDate(tradeDate);
			lb.setPlate(null);
			lb.setReason(null);
			lbList.add(lb);
		}
		if(CollectionUtils.isNotEmpty(lbList)){
			lbService.batchInsert(lbList);
		}
	}
	
	private List<KLine> getKLineList(String tradeDate){
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		criteria.andPercentGreaterThanOrEqualTo(9.9d);
		return klService.selectByExample(example);
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public String getTradeDate(String tradeDate){
		String date = tradeDate;
		if(StringUtil.isBlank(date)){
			Date now = new Date();
			date = sdf.format(now);
		}
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(sdf.parse(date));
		} catch (ParseException e) {
			throw new RuntimeException("日期转换错误！！！");
		}
		if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			throw new RuntimeException("周末无相关数据！！！");
		}
		List<String> dateList = holidayService.selectByYear(String.valueOf(c.get(Calendar.YEAR)));
		for (String hd : dateList) {
			if(tradeDate.equals(hd)){
				throw new RuntimeException("法定假日无相关数据！！！");
			}
		}
		return date;
	}
	
	public static void main(String[] args){
		Calendar c = Calendar.getInstance();
		System.out.println(c.get(Calendar.YEAR));
	}
}
