package com.gesangwu.spider.engine.task.init;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;
/**
 * 由于新浪数据部分缺失，所以需要雪球的数据来做修复
 * url:https://xueqiu.com/stock/forchartk/stocklist.json?symbol=SH600526&period=1day&type=before&begin=1437148330951&end=1468684330951&_=1468684330951
 * @author zhuxb
 *
 */
@Component
public class KLineInit {
	
	private static final String r = "\\{\"volume\"\\:([0-9]*),\"open\"\\:([0-9\\.]*),\"high\"\\:([0-9\\.]*),\"close\"\\:([0-9\\.]*),\"low\"\\:([0-9\\.]*),\"chg\"\\:(\\-?[0-9\\.]*),\"percent\"\\:(\\-?[0-9\\.]*),\"turnrate\"\\:([0-9\\.]*),\"ma5\"\\:([0-9\\.]*),\"ma10\"\\:([0-9\\.]*),\"ma20\"\\:([0-9\\.]*),\"ma30\"\\:([0-9\\.]*),\"dif\"\\:\\-?[0-9\\.]*,\"dea\"\\:\\-?[0-9\\.]*,\"macd\"\\:\\-?[0-9\\.]*,\"time\"\\:\"([^\"]*)\"\\}";
	private static Pattern p = Pattern.compile(r);
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy",Locale.US);
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	private CompanyService companyService;
	@Resource
	private KLineService kLineService;
	
	public void execute(){
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		long start = c.getTimeInMillis();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		long end = c.getTimeInMillis();
		execute(start, end);
	}
	
	public void execute(long start, long end) {
		String cookieUrl = "https://xueqiu.com/account/lostpasswd";
		HttpTool.get(cookieUrl);//这个链接只是为了获得cookie信息，因为后面的请求需要用到cookie
		List<Company> companyList = companyService.selectByExample(null);
		for (Company company : companyList) {
			String symbol = company.getSymbol();
			String url = buildUrl(symbol, start, end);
			String result = HttpTool.get(url);
			System.out.println(result);
			Matcher m = p.matcher(result);
			List<KLine> kLineList = new ArrayList<KLine>();
			while (m.find()){
				String volumn = m.group(1);
				String open = m.group(2);
				String high = m.group(3);
				String close = m.group(4);
				String low = m.group(5);
				String chg = m.group(6);
				String percent = m.group(7);
				String turnrate = m.group(8);
				String ma5 = m.group(9);
				String ma10 = m.group(10);
				String ma20 = m.group(11);
				String ma30 = m.group(12);
				String date = m.group(13);
				KLine kLine = new KLine();
				kLine.setSymbol(symbol);
				kLine.setChangeAmount(Double.valueOf(chg));
				kLine.setClose(Double.valueOf(close));
				kLine.setGmtCreate(new Date());
				kLine.setHigh(Double.valueOf(high));
				kLine.setLow(Double.valueOf(low));
				kLine.setPercent(Double.valueOf(percent));
				kLine.setTurnrate(Double.valueOf(turnrate));
				kLine.setMa10(Double.valueOf(ma10));
				kLine.setMa20(Double.valueOf(ma20));
				kLine.setMa30(Double.valueOf(ma30));
				kLine.setMa5(Double.valueOf(ma5));
				kLine.setOpen(Double.valueOf(open));
				try {
					Date tradeDate = sdf1.parse(date);
					kLine.setTransDate(sdf2.format(tradeDate));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				kLine.setVolume(Long.valueOf(volumn));
				kLineList.add(kLine);
			}
			if(CollectionUtils.isNotEmpty(kLineList)){
				kLineService.batchInsert(kLineList);
			}
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String buildUrl(String symbol, long start, long end){
		StringBuffer sb = new StringBuffer();
		sb.append("https://xueqiu.com/stock/forchartk/stocklist.json?symbol=");
		sb.append(symbol);
		sb.append("&period=1day&type=before&begin=");
		sb.append(start);
		sb.append("&end=");
		sb.append(end);
		return sb.toString();
	}
}
