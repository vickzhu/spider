package com.gesangwu.spider.web.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于获得股票名称，因为很多表是通过symbol来关联的
 * @author zhuxb
 *
 */
public class StockNameCache {
	
	private static Map<String, String> nameSymbolMap = new HashMap<String, String>();

	public static void setNameSymbolMap(Map<String, String> nameSymbolMap) {
		StockNameCache.nameSymbolMap = nameSymbolMap;
	}
	
	public static String getStockName(String symbol){
		return nameSymbolMap.get(symbol);
	}

}
