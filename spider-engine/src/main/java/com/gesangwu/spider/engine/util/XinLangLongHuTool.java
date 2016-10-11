package com.gesangwu.spider.engine.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gandalf.framework.net.HttpTool;

public class XinLangLongHuTool {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 该方法只能获得最近的龙虎榜类型
	 */
	public static Map<String,List<String>> getLongHuType(){
		String url="http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/lhb/index.phtml";
		Date now = new Date();
		String date = sdf.format(now);
		String r = "showDetail\\('([0-9]{2})','([0-9]{6})','" + date + "',this\\)";
		Pattern p = Pattern.compile(r);
		String result = HttpTool.get(url);
		System.out.println(result);
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
		HttpTool.get("http://corp.sina.com.cn/chn/sina_intr.html");
		String url="http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/lhb/index.phtml";
		long start = System.currentTimeMillis();
		String result = HttpTool.get(url);
		long end = System.currentTimeMillis();
		System.out.println(result.length()/1024);
		System.out.println(end-start);
	}
}
