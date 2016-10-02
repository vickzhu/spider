package com.gesangwu.spider.biz.common;

import com.gandalf.framework.util.StringUtil;

public class StockUtil {

	public static String code2Symbol(String code){
		String prefix = StringUtil.EMPTY;
		if(code.startsWith("60")){
			prefix = "sh";
		} else if(code.startsWith("00")||code.startsWith("30")){
			prefix = "sz";
		}
		return prefix + code;
	}
}
