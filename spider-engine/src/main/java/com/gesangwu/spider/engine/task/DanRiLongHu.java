package com.gesangwu.spider.engine.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.engine.util.StockTool;
/**
 * 读取某一天的龙虎榜，有个问题，龙虎详情中没有longHuId
 * @author zhuxb
 *
 */
@Component
public class DanRiLongHu {
	
	@Resource
	private LongHuInit longHuInit;
	private static final String r1 = "\"SCode\"\\:\"([0-9]*)\",\"SName\"\\:\"([^\"]*)\",\"ClosePrice\"\\:\"([0-9\\.]*)\",\"Chgradio\"\\:\"([\\-0-9\\.]*)\",\"Dchratio\"\\:\"([0-9\\.]*)\",\"JmMoney\"\\:\"[\\-0-9\\.]*\",\"Turnover\"\\:\"([\\-0-9\\.]*)\",\"Ntransac\"\\:\"([\\-0-9\\.]*)\",\"Ctypedes\"\\:\"[^\"]*\",\"Oldid\"\\:\"[\\-0-9\\.]*\",\"Smoney\"\\:\"([0-9\\.]*)\",\"Bmoney\"\\:\"([0-9\\.]*)\",\"ZeMoney\"\\:\"[^\"]*\",\"Tdate\"\\:\"[^\"]*\",\"JmRate\"\\:\"[^\"]*\",\"ZeRate\"\\:\"[^\"]*\",\"Ltsz\"\\:\"([^\"]*)\"";
	private static Pattern p1 = Pattern.compile(r1);

	public void execute(String date){
		String url = buildUrl(date, 2);
		String result = HttpTool.get(url);
		Matcher m =  p1.matcher(result);
		while(m.find()){
			String code = m.group(1);
			String symbol = StockTool.codeToSymbol(code);
			longHuInit.getLongHu(symbol, date);
		}
	}
	
	/**
	 * 
	 * @param date
	 * @param bazaar	gpfw:0-全部，1-上证，2-深证
	 * @return
	 */
	private String buildUrl(String date, int bazaar){
		StringBuilder sb = new StringBuilder();
		sb.append("http://data.eastmoney.com/DataCenter_V3/stock2016/TradeDetail/pagesize=200,page=1,sortRule=-1,sortType=,startDate=");
		sb.append(date);
		sb.append(",endDate=");
		sb.append(date);
		sb.append(",gpfw=");
		sb.append(bazaar);
		sb.append(",js=.html");
		return sb.toString();
	}
}
