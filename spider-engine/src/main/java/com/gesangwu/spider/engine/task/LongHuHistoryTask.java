package com.gesangwu.spider.engine.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.gandalf.framework.net.HttpTool;

/**
 * 龙虎榜历史数据
 * @author zhuxb
 *"SCode":"000571",
"SName":"新大洲A",
"ClosePrice":"7.57",收盘价
"Chgradio":"10.0291",涨跌幅
"Dchratio":"7.812",换手率
"JmMoney":"-4736919.37",龙虎榜净买入
"Turnover":"438853106",总成交额
"Ntransac":"58929212",总成交量
"Ctypedes":"连续三个交易日内，涨幅偏离值累计达到20%的证券",
"Oldid":"2327182",
"Smoney":"48265370.28",卖出
"Bmoney":"43528450.91",买入
"ZeMoney":"91793821.19",龙虎榜成交
"Tdate":"2016-07-21",
"JmRate":"-1.08",净买额占总成交比
"ZeRate":"20.92",成交额占总成交比
"Ltsz":"5569122679.41",流通市值
"Rchange1dc":"3.963","Rchange1do":"3.4839",
"Rchange2dc":"5.5482","Rchange2do":"0.9032",
"Rchange3dc":"3.8309","Rchange3do":"1.5484",
"Rchange5dc":"-1.4531","Rchange5do":"-3.871",
"Rchange10dc":"4.0951","Rchange10do":"1.2903",
"Rchange15dc":"3.0383","Rchange15do":"-0.129",
"Rchange20dc":"11.3606","Rchange20do":"8.129",
"Rchange30dc":"","Rchange30do":"",
"Rchange1m":"33.15251702",
"Rchange3m":"33.15251702",
 */
@Component
public class LongHuHistoryTask {	

	public void execute(){
		String url = "http://data.eastmoney.com/DataCenter_V3/stock2016/TradeDetail/pagesize=50,page=1,sortRule=-1,sortType=,startDate=2016-06-21,endDate=2016-07-21,gpfw=0,js=.html";
		String result = HttpTool.get(url);
		System.out.println(result);
		Matcher matcher = p1.matcher(result);
		if(!matcher.find()){
			return;
		}
		int pages = Integer.valueOf(matcher.group(1));
		System.out.println(matcher.group(2));
		String content = matcher.group(2);
		String[] details = content.split("\\},\\{");
		for (String detail : details) {
			System.out.println(detail);
			String[] pairs = detail.split("\",\"");
			
		}
	}
	
	private static final String r1 = "\"pages\"\\:([0-9]*),\"data\"\\:\\[\\{(.*)\\}\\],\"url\"\\:\"";
	private static Pattern p1 = Pattern.compile(r1);
	
	private void fetch(int page){
		String url = "http://data.eastmoney.com/DataCenter_V3/stock2016/TradeDetail/pagesize=50,page=1,sortRule=-1,sortType=,startDate=2016-06-21,endDate=2016-07-21,gpfw=0,js=.html";
		String result = HttpTool.get(url);
		System.out.println(result);
		Matcher matcher = p1.matcher(result);
		if(!matcher.find()){
			return;
		}
		int pages = Integer.valueOf(matcher.group(1));
		System.out.println(matcher.group(2));
		String content = matcher.group(2);
		parse(content);
		if(page >= page){
			return;
		} else {
			fetch(++page);
		}
	}
	
	private static final String r2 = "\"SCode\"\\:\"([0-9]*)\",\"SName\"\\:\"([^\"]*)\",\"ClosePrice\"\\:\"([0-9\\.]*)\",\"Chgradio\"\\:\"([\\-0-9\\.]*)\",\"Dchratio\"\\:\"([0-9\\.]*)\",\"JmMoney\"\\:\"[\\-0-9\\.]*\",\"Turnover\"\\:\"([\\-0-9\\.]*)\",\"Ntransac\"\\:\"([\\-0-9\\.]*)\",\"Ctypedes\"\\:\"[^\"]*\",\"Oldid\"\\:\"([\\-0-9\\.]*)\",\"Smoney\"\\:\"([0-9\\.]*)\",\"Bmoney\"\\:\"([0-9\\.]*)\"";
	private static Pattern p2 = Pattern.compile(r2);
	
	private void parse(String content){
		Matcher m =  p2.matcher(content);
		while(m.find()){
			String code = m.group(1);
			String stockName = m.group(2);
			String price = m.group(3);
			String chg = m.group(4);
			String turnover = m.group(5);
			
		}
	}
	
	public static void main(String[] args){
		LongHuHistoryTask task = new LongHuHistoryTask();
		task.execute();
	}
	
}
