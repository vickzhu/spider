package com.gesangwu.spider.biz.common;

import com.gandalf.framework.util.StringUtil;

public class StockUtil {

	public static String code2Symbol(String code){
		String prefix = StringUtil.EMPTY;
		if(code.startsWith("60") || code.startsWith("90") || code.startsWith("688")){
			prefix = "sh";
		} else if(code.startsWith("00")||code.startsWith("30")||code.startsWith("20")){
			prefix = "sz";
		} else if(code.startsWith("43")) {
			prefix = "bj";
		}
		return prefix + code;
	}
	
	public static String symbol2Code(String symbol){
		return symbol.substring(2);
	}
	
	public static boolean isLittleMarketValue(double activeMarketValue){
		return activeMarketValue < 5000000000d;
	}
	
}
