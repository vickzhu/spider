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
		
		//02-23
//		fetch(4, jlbMap);
		
		//02-22
//		fetch(5, zlbMap);
//		fetch(6, fcxMap);
//		fetch(7, zyMap);
		
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
	private static Map<String, String> jlbMap = new HashMap<String, String>();
	private static Map<String, String> zlbMap = new HashMap<String, String>();
	private static Map<String, String> fcxMap = new HashMap<String, String>();
	private static Map<String, String> zyMap = new HashMap<String, String>();
	
	static{
		fcmMap.put("name", "方春梅");
		fcmMap.put("mobile", "13107200278");
		fcmMap.put("idNumber", "430626195712287580");
		fcmMap.put("store_code", storeCode);
		fcmMap.put("range_time", rangeTime);
		
		zhyMap.put("name", "朱红雨");
		zhyMap.put("mobile", "13762083284");
		zhyMap.put("idNumber", "430611198206255606");
		zhyMap.put("store_code", storeCode);
		zhyMap.put("range_time", rangeTime);
		
		jzMap.put("name", "金枝");
		jzMap.put("mobile", "13107200278");
		jzMap.put("idNumber", "430921200907200148");
		jzMap.put("store_code", storeCode);
		jzMap.put("range_time", rangeTime);
		
		jlbMap.put("name", "金龙兵");
		jlbMap.put("mobile", "13762083284");
		jlbMap.put("idNumber", "43092119800824235");
		jlbMap.put("store_code", storeCode);
		jlbMap.put("range_time", rangeTime);
		
		zlbMap.put("name", "张立兵");
		zlbMap.put("mobile", "15073022069");
		zlbMap.put("idNumber", "430611196610146517");
		zlbMap.put("store_code", storeCode);
		zlbMap.put("range_time", rangeTime);
		
		fcxMap.put("name", "方从喜");
		fcxMap.put("mobile", "15073022069");
		fcxMap.put("idNumber", "430611196710306522");
		fcxMap.put("store_code", storeCode);
		fcxMap.put("range_time", rangeTime);
		
		zyMap.put("name", "张亚");
		zyMap.put("mobile", "15073022069");
		zyMap.put("idNumber", "430602199009230520");
		zyMap.put("store_code", storeCode);
		zyMap.put("range_time", rangeTime);
	}
}
