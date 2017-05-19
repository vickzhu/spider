package com.gesangwu.spider.web.common;

import java.util.HashMap;
import java.util.Map;

import com.gandalf.framework.constant.SymbolConstant;

public class DeptAmountFormat {

	public static String formatAmount(int dateType, double amount){
		StringBuilder sb = new StringBuilder();
		sb.append(dateType);
		sb.append(SymbolConstant.U_LINE);
		sb.append(amount);
		return sb.toString();
	}
	
	public static Map<Integer,Double> parse(String amountArr){
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		String[] amounts = amountArr.split(SymbolConstant.COMMA);
		for (String as : amounts) {
			String[] ap = as.split(SymbolConstant.U_LINE);
			int dateType = Integer.valueOf(ap[0]);
			double amount = Double.valueOf(ap[1]);
			map.put(dateType, amount);
		}
		return map;
	}
}
