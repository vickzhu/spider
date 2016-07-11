package com.gesangwu.spider.engine.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gesangwu.spider.biz.dao.model.Company;

public class LittleCompanyHolder {
	
	private static List<Company> companyList;
	private static Map<String, String> symbolNameMap = new HashMap<String, String>();
	private static Map<String, String> codeNameMap = new HashMap<String, String>();

	public static List<Company> getCompanyList() {
		return companyList;
	}

	public static void setCompanyList(List<Company> list) {
		companyList = list;
		for (Company company : list) {
			symbolNameMap.put(company.getSymbol(), company.getStockName());
			codeNameMap.put(company.getSymbol(), company.getStockName());
		}
	}
	
	public static String getNameBySymbol(String symbol){
		return symbolNameMap.get(symbol);
	}
	
	public static String getNameByCode(String code){
		return codeNameMap.get(code);
	}
	
}
