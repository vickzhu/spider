package com.gesangwu.spider.engine.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gandalf.framework.mybatis.KeyValue;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.service.KLineService;

/**
 * 目前是嵌套在k线中使用的，如果以后有单独统计使用的功能，可以继续完善该功能
 * @author zhuxb
 *
 */
@Component
public class CalcMATask {
	
	private static final Logger logger = LoggerFactory.getLogger(CalcMATask.class);
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	private KLineService klService;
	
	public void execute(){
		execute(null);		
	}
	
	public void execute(String tradeDate){
		if(StringUtil.isBlank(tradeDate)){
			Date now = new Date();
			tradeDate = sdf.format(now);
		}
		List<KLine> klList = klService.selectByShape(tradeDate);
		String startDate = getStartDate(tradeDate);
		List<KLine> resultList = new ArrayList<KLine>();
		for (KLine kl : klList) {
			List<KeyValue<String, Double>> cList = klService.selectLastest90Close(kl.getSymbol(), tradeDate);
			KeyValue<String, Double> maxKV = cList.get(0);
			KeyValue<String, Double> minKV = cList.get(cList.size()-1);
			if(maxKV.getKey().compareTo(minKV.getKey()) < 0){//最高点日期小于最低点日期，说明是上一波的高峰
				continue;
			}
			double close = kl.getClose();
			double max = maxKV.getValue();
			double min = minKV.getValue();
			double h1 = (close - min)/min;
			if(h1 < 0.2){//值越小离底部越近
				continue;
			}
			double h2 = (close - max)/max;
			if(Math.abs(h2) > 0.1){//离最大值大于10%
				continue;
			}
			resultList.add(kl);
		}
		System.out.println("总数:" + resultList.size());
		for (KLine kLine : resultList) {
			System.out.println(kLine.getSymbol());
		}
	}
	
	private static String getStartDate(String tradeDate){
		try {
			Date td = sdf.parse(tradeDate);
			Calendar c = Calendar.getInstance();
			c.setTime(td);
			c.set(Calendar.MONTH, c.get(Calendar.MONTH)-3);
			return sdf.format(c.getTime());
		} catch (ParseException e) {
			logger.error("Parse trade date failed", e);
			return null;
		}
	}
	
}
