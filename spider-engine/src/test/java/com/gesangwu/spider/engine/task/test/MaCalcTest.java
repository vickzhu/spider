package com.gesangwu.spider.engine.task.test;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gandalf.framework.mybatis.KeyValue;
import com.gandalf.framework.util.CalculateUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.engine.test.BaseTest;

public class MaCalcTest extends BaseTest {
	
	private int cpp = 500;

	@Resource
	private CompanyService companyService;

	@Resource
	private KLineService klService;
	
	@Test
	public void execute(){
		long start = System.currentTimeMillis();
		String tradeDate = "2017-07-24";
		int curPage = 1;
		query(curPage, tradeDate);
		long end = System.currentTimeMillis();
		System.out.println("用时：" + (end - start)/1000);
	}
	
	private void query(int curPage, String tradeDate){
		Page<KLine> page = new Page<KLine>(curPage, cpp);
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		klService.selectByPagination(example, page);
		int totalPages = page.getTotalPages();
		List<KLine> klList = page.getRecords();
		for (KLine kl : klList) {
			calc(kl);			
		}
		if(curPage < totalPages){
			query(++curPage, tradeDate);
		}
	}
	
	private void calc(KLine kl){
		String symbol = kl.getSymbol();
		String tradeDate = kl.getTradeDate();
		List<KeyValue<String, Double>> kvList = getLatest30List(symbol, tradeDate);
		Double ma5 = calcMA(kvList, 5);			
		Double ma10 = calcMA(kvList, 10);
		Double ma20 = calcMA(kvList, 20);
		Double ma30 = calcMA(kvList, 30);
		kl.setMa5(ma5);
		kl.setMa10(ma10);
		kl.setMa20(ma20);
		kl.setMa30(ma30);
		klService.updateByPrimaryKey(kl);
	}
	
	public List<KeyValue<String, Double>> getLatest30List(String symbol, String tradeDate){
		KLineExample example = new KLineExample();
		example.setOrderByClause("trade_date desc");
		example.setOffset(0);
		example.setRows(30);
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateLessThanOrEqualTo(tradeDate);
		return klService.selectLastestClose(example);
	}
	
	public static Double calcMA(List<KeyValue<String, Double>> kvList, int period){
		if(kvList.size() < period){
			return null;
		}
		int sum = 0;
		for (int i = 0; i < period; i++) {
			sum += kvList.get(i).getValue() * 100;
		}
		double d = CalculateUtil.div(sum, 100, 2);
		return CalculateUtil.div(d, period, 2);
	}
	
	public static void main(String[] args){
		List<KeyValue<String, Double>> kvList = new ArrayList<KeyValue<String, Double>>();
		kvList.add(new KeyValue<String, Double>("",3.27));
		kvList.add(new KeyValue<String, Double>("",3.29));
		kvList.add(new KeyValue<String, Double>("",3.29));
		kvList.add(new KeyValue<String, Double>("",3.28));
		kvList.add(new KeyValue<String, Double>("",3.26));
		kvList.add(new KeyValue<String, Double>("",3.28));
		kvList.add(new KeyValue<String, Double>("",3.27));
		kvList.add(new KeyValue<String, Double>("",3.28));
		kvList.add(new KeyValue<String, Double>("",3.32));
		kvList.add(new KeyValue<String, Double>("",3.31));
		double result = calcMA(kvList, 10);
		System.out.println(result);
	}
	
}
