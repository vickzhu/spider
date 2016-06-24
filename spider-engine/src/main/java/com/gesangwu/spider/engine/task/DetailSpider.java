package com.gesangwu.spider.engine.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gandalf.framework.net.HttpTool;

/**
 * 逐笔详情，其实详情无所谓，关键是取得内外盘数据
 * @author zhuxb
 *
 */
public class DetailSpider {
	
	private static final String regex = "var trade_INVOL_OUTVOL=\\[(\\d*),(\\d*)\\];";
	private static final Pattern r = Pattern.compile(regex);
	
	public void execute(){
		long time = System.currentTimeMillis();
		String result = HttpTool.get("http://vip.stock.finance.sina.com.cn/quotes_service/view/CN_TransListV2.php?num=1&symbol=sz002265&rn="+time);
		System.out.println(result);
		Matcher matcher = r.matcher(result);
		if(matcher.find()){
			System.out.println(matcher.group(1));//外盘
			System.out.println(matcher.group(2));//内盘
		}
	}

	public static void main(String[] args) {
		DetailSpider spider = new DetailSpider();
		spider.execute();

	}

}
