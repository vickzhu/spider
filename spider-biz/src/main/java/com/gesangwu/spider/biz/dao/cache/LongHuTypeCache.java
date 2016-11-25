package com.gesangwu.spider.biz.dao.cache;

import java.util.HashMap;
import java.util.Map;

import com.gesangwu.spider.biz.dao.model.LongHuType;

public class LongHuTypeCache {

	private static Map<String, LongHuType> typeMap = new HashMap<String, LongHuType>();

	public static Map<String, LongHuType> getTypeMap() {
		return typeMap;
	}

	public static void setTypeMap(Map<String, LongHuType> typeMap) {
		LongHuTypeCache.typeMap = typeMap;
	}
	
	public static LongHuType get(String lhType){
		return typeMap.get(lhType);
	}
	
	public static void add(LongHuType type){
		typeMap.put(type.getLhType(), type);
	}
}
