package com.gesangwu.spider.engine.util;

import java.util.List;

import com.gesangwu.spider.biz.dao.model.Company;

public class LittleCompanyHolder {
	
	private static List<Company> companyList;

	public static List<Company> getCompanyList() {
		return companyList;
	}

	public static void setCompanyList(List<Company> list) {
		companyList = list;
	}
	
}
