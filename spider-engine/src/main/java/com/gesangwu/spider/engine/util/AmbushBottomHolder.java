package com.gesangwu.spider.engine.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gesangwu.spider.biz.dao.model.KLine;

/**
 * 
 * @author zhuxb
 *
 */
public class AmbushBottomHolder {

	private static Map<String, KLine> klMap = new HashMap<String, KLine>();
	
	public static void set(List<KLine> klList){
		klMap.clear();
		for (KLine kl : klList) {
			klMap.put(kl.getSymbol(), kl);
		}
	}
	
	public static KLine get(String symbol){
		return klMap.get(symbol);
	}
}
