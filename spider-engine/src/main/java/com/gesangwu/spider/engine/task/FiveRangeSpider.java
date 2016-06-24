package com.gesangwu.spider.engine.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;

/**
 * 五档
 * <pre>
 * 这里的定时时间最长一分钟
 * </pre>
 * @author zhuxb
 *
 */
public class FiveRangeSpider {
	
	private static final String regex = "var hq_str_([\\w]{8})=\"(.*)\";";
	private static final Pattern r = Pattern.compile(regex);
	
	public void execute(){
		long time = System.currentTimeMillis();
		String result = HttpTool.get("http://hq.sinajs.cn/etag.php?_="+time+"&list=sz002265,sz002495");
		System.out.println(result);
		Matcher matcher = r.matcher(result);
		while(matcher.find()){
			String code = matcher.group(1);
			String detail = matcher.group(2);
			String[] details = detail.split(SymbolConstant.COMMA);
			String buy_1_count = details[10];
			String buy_1_price = details[11];
			System.out.println(details[0]+"买一："+buy_1_price+"   "+buy_1_count);
			String buy_2_count = details[12];
			String buy_2_price = details[13];
			
			String buy_3_count = details[14];
			String buy_3_price = details[15];
			
			String buy_4_count = details[16];
			String buy_4_price = details[17];
			
			String buy_5_count = details[18];
			String buy_5_price = details[19];
			System.out.println(details[0]+"买五："+buy_5_price+"   "+buy_5_count);
			
			String sell_1_count = details[20];
			String sell_1_price = details[21];
			System.out.println(details[0]+"卖一："+sell_1_price+"   "+sell_1_count);
			
			String sell_2_count = details[22];
			String sell_2_price = details[23];
			
			String sell_3_count = details[24];
			String sell_3_price = details[25];
			
			String sell_4_count = details[26];
			String sell_4_price = details[27];
			
			String sell_5_count = details[28];
			String sell_5_price = details[29];
			System.out.println(details[0]+"卖五："+sell_5_price+"   "+sell_5_count);
		}
	}

	public static void main(String[] args) {
		FiveRangeSpider spider = new FiveRangeSpider();
		spider.execute();

	}

}
