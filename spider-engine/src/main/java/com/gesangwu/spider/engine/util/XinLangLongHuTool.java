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
		headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
		headerMap.put("Accept-Encoding", "gzip, deflate, sdch");
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
		String url="http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/lhb/index.phtml";
		long start = System.currentTimeMillis();
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
		headerMap.put("Accept-Encoding", "gzip, deflate, sdch");
		String result = HttpTool.get(url, headerMap, Charset.forName("GBK"));
		long end = System.currentTimeMillis();
		System.out.println(result);
		System.out.println(result.length()/1024);
		System.out.println(end-start);
	}
}
