package com.gesangwu.spider.engine.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;

/**
 * 获取St企业的K线数据
 * "HIGH":最高,
 * "LOW":最低,
 * "OPEN":开盘,
 * "PERCENT":涨跌幅,
 * "PRICE":现价,
 * "SYMBOL":"代码",
 * "TURNOVER":换手率,
 * "UPDOWN":涨跌额,
 * "VOLUME":成交量,
 * "YESTCLOSE":昨收,
 * "NO":编号
 * @author zhuxb
 *
 */
public class StKLineTask {
	
	private static final Logger logger = LoggerFactory.getLogger(StKLineTask.class);

	private static final String r1 = "\"total\"\\:([0-9]*),\"pagecount\"\\:([0-9]*)";
	private static final String r2 = "\"HIGH\"\\:([0-9\\.]*),\"LOW\"\\:([0-9\\.]*),\"OPEN\"\\:([0-9\\.]*),\"PERCENT\"\\:([\\-]?[0-9\\.]*),\"PRICE\"\\:([0-9\\.]*),\"SYMBOL\"\\:\"([0-9]*)\",\"TURNOVER\"\\:([0-9\\.]*),\"UPDOWN\"\\:([\\-]?[0-9\\.]*),\"VOLUME\"\\:([0-9]*),\"YESTCLOSE\"\\:([0-9\\.]*),\"NO\"\\:[0-9]*";
	private static final Pattern p1 = Pattern.compile(r1);
	private static final Pattern p2 = Pattern.compile(r2);
	
	@Resource
	private CompanyService companyService;
	@Resource
	private KLineService kLineService;

	public void execute(){
		long start = System.currentTimeMillis();
		String result = HttpTool.get(buildUrl(0));
		Matcher matcher = p1.matcher(result);
		if(!matcher.find()){
			return;
		}
		int pages = Integer.valueOf(matcher.group(2));
		sou(result);
		for(int curPage = 1; curPage < pages; curPage++){
			result = HttpTool.get(buildUrl(curPage));
			sou(result);
		}	
		long end = System.currentTimeMillis();
		logger.info("Update Company use:" + (end-start)+"ms!");
	}
	
	private String buildUrl(int page){
		StringBuilder sb = new StringBuilder();
		sb.append("http://quotes.money.163.com/hs/service/diyrank.php?query=NODEAL%3AFXJS&fields=SYMBOL%2CPRICE%2CPERCENT%2CUPDOWN%2COPEN%2CYESTCLOSE%2CHIGH%2CLOW%2CVOLUME%2CTURNOVER&count=100&type=query&page="+page);
		return sb.toString();
	}
	
	/**
	 * save or update
	 * @param detailList
	 */
	private void sou(String result){
		Matcher m = p2.matcher(result);
		Date now = new Date();
		List<KLine> kLineList = new ArrayList<KLine>();
		while(m.find()){
			String code = m.group(6);
			String symbol = StockUtil.code2Symbol(code);
			String high = m.group(1);
			String low = m.group(2);
			String open = m.group(3);
			String percent = m.group(4);
			String lastPrice = m.group(5);
			String turnrate = m.group(7);
			String updown = m.group(8);
			String volume = m.group(9);
			String yesterdayClose = m.group(10);
			System.out.println(symbol + ":" + lastPrice);
			
			long vol = Long.valueOf(volume);
			double openPrice = Double.valueOf(open);
			if(vol==0 && openPrice == 0){
				continue;
			}			
			KLine kLine = new KLine();
			kLine.setSymbol(symbol);
			kLine.setYesterdayClose(Double.valueOf(yesterdayClose));
			kLine.setVolume(vol);
//			kLine.setAmount(Double.valueOf(amt));
			kLine.setOpen(openPrice);
			kLine.setClose(Double.valueOf(lastPrice));
			kLine.setHigh(Double.valueOf(high));
			kLine.setLow(Double.valueOf(low));
			kLine.setChangeAmount(Double.valueOf(updown));
			kLine.setPercent(Double.valueOf(percent));
			kLine.setTurnrate(Double.valueOf(turnrate));
//			kLine.setTradeDate(tradeDate);
			kLine.setGmtCreate(now);
			kLineList.add(kLine);
		}
		kLineService.batchInsert(kLineList);
	}
	
	public static void main(String[] args){
		StKLineTask task = new StKLineTask();
		task.execute();
	}
}
