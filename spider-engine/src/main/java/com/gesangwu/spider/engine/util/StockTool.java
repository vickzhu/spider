package com.gesangwu.spider.engine.util;

public class StockTool {

	public static String codeToSymbol(String code){
		return code.startsWith("60")?"sh"+code:"sz"+code;
	}
	
}
