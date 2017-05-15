package com.gesangwu.spider.web.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 席位缓存
 * @author zhuxb
 *
 */
public class SecDeptSeatCache {
	
	private static Map<String, String> seatMap = new HashMap<String, String>();

	public static void setSeatMap(Map<String, String> seatMap) {
		SecDeptSeatCache.seatMap = seatMap;
	}
	
	public static String getSeat(String deptCode){
		return seatMap.get(deptCode);
	}
}
