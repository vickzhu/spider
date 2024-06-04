package com.gesangwu.spider.engine.test;

import java.util.HashMap;
import java.util.Map;

import com.gandalf.framework.net.HttpTool;

public class Kouzhao {

	public static void main (String[] args){
		//02-21
		fetch(1, fcmMap);
		fetch(2, zhyMap);
		fetch(3, jzMap);
		
	}
	
	private static void fetch(int index, Map<String, String> paramMap){
		while(true){
			System.out.print(index);
			String result = HttpTool.post(url, paramMap);
			if(result.indexOf("error") < 0){
				System.out.println();
				System.out.println(result);
				break;
			}
		}
	}
	
	private static final String url = "http://ecg.yfdyf.com/order.php";
	private static final String storeCode = "9";
	private static final String rangeTime = "13";
	
	private static Map<String, String> fcmMap = new HashMap<String, String>();
	private static Map<String, String> zhyMap = new HashMap<String, String>();
	private static Map<String, String> jzMap = new HashMap<String, String>();
	
	static{
		fcmMap.put("name", "");
		fcmMap.put("mobile", "");
		fcmMap.put("idNumber", "");
		fcmMap.put("store_code", storeCode);
		fcmMap.put("range_time", rangeTime);
		
		zhyMap.put("name", "");
		zhyMap.put("mobile", "");
		zhyMap.put("idNumber", "");
		zhyMap.put("store_code", storeCode);
		zhyMap.put("range_time", rangeTime);
	}
}
