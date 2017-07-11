package com.gesangwu.spider.engine.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gandalf.framework.util.CalculateUtil;
import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.KLineService;

@Component
public class CalcMATask {
	
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
		Page<KLine> page = new Page<KLine>(1,500);
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		klService.selectByPagination(example, page);
		List<KLine> klList = page.getRecords();
		for (KLine kLine : klList) {
			String symbol = kLine.getSymbol();
			List<Double> closeList = klService.selectLastest30Close(symbol, tradeDate);
			Double ma5 = calc(closeList, 5);
			Double ma10 = calc(closeList, 10);
			Double ma20 = calc(closeList, 20);
			Double ma30 = calc(closeList, 30);
			kLine.setMa5(ma5);
			kLine.setMa10(ma10);
			kLine.setMa20(ma20);
			kLine.setMa30(ma30);
			klService.updateByPrimaryKey(kLine);
		}
	}

	
	public Double calc(List<Double> closeList, int period){
		if(closeList.size() < period){
			return null;
		}
		int sum = 0;
		for (int i = 0; i < period; i++) {
			sum += closeList.get(i) * 100;
		}
		double d = CalculateUtil.div(sum, 100, 2);
		return CalculateUtil.div(d, period, 2);
	}
	
}
