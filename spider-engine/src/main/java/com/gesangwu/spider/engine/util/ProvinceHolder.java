package com.gesangwu.spider.engine.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gesangwu.spider.biz.dao.model.Province;

public class ProvinceHolder {
	
	private static List<Province> provinceList;
	private static Map<String, String> nameCodeMap = new HashMap<String, String>();
	private static Map<String, String> codeNameMap = new HashMap<String, String>();
	
	public static List<Province> getProvinceList() {
		return provinceList;
	}
	
	public static void setProvinceList(List<Province> provinceList) {
		ProvinceHolder.provinceList = provinceList;
		for (Province province : provinceList) {
			nameCodeMap.put(province.getProvinceName(), province.getCode());
			codeNameMap.put(province.getCode(), province.getProvinceName());
		}
	}

	public static Map<String, String> getNameCodeMap() {
		return nameCodeMap;
	}

	public static Map<String, String> getCodeNameMap() {
		return codeNameMap;
	}
	
}
