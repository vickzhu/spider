package com.gesangwu.spider.engine.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.biz.dao.model.BigVolStatis;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.service.BigVolStatisService;
import com.gesangwu.spider.engine.util.LittleCompanyHolder;

@Component
public class BigVolStatisTask {
	
	private static final String regex = "\\{symbol\\:\"([^\"]*)\",name\\:\"([^\"]*)\",ticktime\\:\"([0-9\\:]*)\",price\\:\"([0-9\\.]*)\",volume\\:\"([0-9]*)\",prev_price\\:\"([0-9\\.]*)\",kind\\:\"(D|U|E)\"}";
	private Pattern r = Pattern.compile(regex);
	
	@Resource
	private BigVolStatisService statisService;
	
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	public void execute(){
		long start = System.currentTimeMillis();
		Date now = new Date();
		String date = df.format(now);
		List<Company> companyList = LittleCompanyHolder.getCompanyList();
		for (Company company : companyList) {
			String symbol = company.getSymbol();
			String url = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_Bill.GetBillList?symbol="+company.getSymbol()+"&num=20&page=1&sort=ticktime&asc=0&volume=5000&amount=0&type=0&day="+date;
			String result = HttpTool.get(url);
			Matcher m = r.matcher(result);
			if(!m.matches()){
				continue;
			}
			int buyTotal = 0;
			int sellTotal = 0;
			int equalTotal = 0;
			while(m.find()){
				String mark = m.group(7);
				if("U".equals(mark)){
					buyTotal++;
				} else if("D".equals(mark)){
					sellTotal++;
				} else if("E".equals(mark)){
					equalTotal++;
				}
			}
			BigVolStatis statis = statisService.selectBySymbolAndDate(symbol, date);
			if(statis == null){
				statis = new BigVolStatis();
				statis.setSymbol(symbol);
				statis.setDate(date);
				statis.setGmtCreate(now);
				statisService.insert(statis);
			} 
			statis.setBuyTotal(buyTotal);
			statis.setSellTotal(sellTotal);
			statis.setEqualTotal(equalTotal);
			statis.setGmtUpdate(now);
			statisService.updateByPrimaryKey(statis);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("======================" + (end - start));
		
	}

	
	public static void main(String[] args){
		BigVolStatisTask task = new BigVolStatisTask();
		task.execute();
	}
}
