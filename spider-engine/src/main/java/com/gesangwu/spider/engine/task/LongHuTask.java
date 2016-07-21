package com.gesangwu.spider.engine.task;

import org.springframework.stereotype.Component;

import com.gandalf.framework.net.HttpTool;

/**
 * <ul>
 * 	<li>东方财富</li>
 *  <li>http://data.eastmoney.com/stock/tradedetail.html</li>
 * </ul>
 * @author zhuxb
 *
 */
@Component
public class LongHuTask {

	/**
	 * gpfw:0-全部，1-上证，2-深证
	 */
	public void execute(){
		String url = "http://data.eastmoney.com/DataCenter_V3/stock2016/TradeDetail/pagesize=200,page=1,sortRule=-1,sortType=,startDate=2016-07-21,endDate=2016-07-21,gpfw=0,js=.html";
		String result = HttpTool.get(url);
		System.out.println(result);
	}
	
	public static void main(String[] args){
		LongHuTask task = new LongHuTask();
		task.execute();
	}
}
