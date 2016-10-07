package com.gesangwu.spider.engine.util;

import com.gesangwu.spider.biz.common.StockUtil;

public class StockTool {

	public static String codeToSymbol(String code){
		return StockUtil.code2Symbol(code);
	}
	
}
