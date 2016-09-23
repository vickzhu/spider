package com.gesangwu.spider.biz.service.test;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.gandalf.framework.net.HttpTool;

public class AdminTest {

	public static void main(String[] args){
		String sessionId = "C86C4F9E96FFCF001715C1E92B02D000";
		Map<String,String> cookieMap = new HashMap<String,String>();
		cookieMap.put("JSESSIONID", sessionId);
		cookieMap.put("TSdf09e4", "6334849b18aef5efb6e664b9181d46ac8ff0ee1716f516b357d903f260ac0ec567f50dde");
		cookieMap.put("WEBTRENDS_ID", "220.248.23.226-2643011760.30519065::F8954F6D65C9FCA8441BEE7BCC0");
//		cookieMap.put("TSb9c1ee", "38f73bc27a2449af56e21703d2a14cde8ff0ee1716f516b357d90d06");
		Map<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Host", "pointbonus.cmbchina.com");
		headerMap.put("Upgrade-Insecure-Requests", "1");
		headerMap.put("Referer", "https://pointbonus.cmbchina.com/IMSPActivities/mgmFollow/topTwenty.action");
		headerMap.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.94 Safari/537.36");
		String url = "https://pointbonus.cmbchina.com/IMSPActivities/mgmFollow/m1seem1.action?openId=oSNb4fjCAqaiUx9xv6P-2uh_GNZw";
		String result = HttpTool.get(url, headerMap, cookieMap, Charset.forName("utf-8"));
		System.out.println(result);
		
	}
	
}
