package com.gesangwu.spider.engine.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gandalf.framework.net.HttpTool;

public class XinLangLongHuTool {

	/**
	 * 该方法只能获得最近的龙虎榜类型
	 */
	public static Map<String,List<String>> getLongHuType(String tradeDate){
		String url="http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/lhb/index.phtml?tradedate="+tradeDate;
		String r = "showDetail\\('([0-9]{2})','([0-9]{6})','" + tradeDate + "',this\\)";
		Pattern p = Pattern.compile(r);
		Map<String, String> headerMap = new HashMap<String, String>();
		String result = HttpTool.get(url, headerMap, Charset.forName("GBK"));
		Matcher m = p.matcher(result);
		Map<String,List<String>> longHuMap = new HashMap<String,List<String>>();
		while(m.find()){
			String type = m.group(1);
			String code = m.group(2);
			List<String> list = longHuMap.get(code);
			if(list == null){
				list = new ArrayList<String>();
				longHuMap.put(code, list);
			}
			list.add(type);
		}
		return longHuMap;
	}
	
	public static void main(String[] args){
		Map<String,List<String>> typeMap = getLongHuType("2017-01-17");
		System.out.println(typeMap.size());
		for(Map.Entry<String, List<String>> entry : typeMap.entrySet()){
			System.out.println(entry.getKey());
		}
	}
}
