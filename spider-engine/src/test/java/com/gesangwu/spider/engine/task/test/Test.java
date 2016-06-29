package com.gesangwu.spider.engine.task.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gandalf.framework.net.HttpTool;

public class Test {
	
	private static final String r = "\"publishdate\"\\:\"[0-9]*\",\"enddate\"\\:\"([0-9]*)\",\"compcode\"\\:\"[0-9]*\",\"shholdercode\"\\:(\"[0-9]*\"|null),\"shholdername\"\\:\"([^\"]*)\",\"shholdertype\"\\:\"[^\"]*\",\"rank1\":null,\"rank2\"\\:([0-9]{1,2}),\"holderamt\"\\:([0-9E\\.]*),\"holderrto\"\\:[0-9\\.]*,\"pctoffloatshares\"\\:([0-9\\.]*),";

	private static Pattern p = Pattern.compile(r);
	
	public static void main(String[] args){
		String cookieUrl = "https://xueqiu.com/account/lostpasswd";
		HttpTool.get(cookieUrl);//这个链接只是为了获得cookie信息，因为后面的请求需要用到cookie
		String url = "https://xueqiu.com/stock/f10/otsholder.json?symbol=sz002265";
		String result = HttpTool.get(url);
		Matcher m = p.matcher(result);
		while(m.find()){    		
    		String endDate = m.group(1);
    		String holderCode = m.group(2);
    		String holderName = m.group(3);
    		String rank = m.group(4);
    		String counts = m.group(5);//持股数量
    		String pctOfFloatShares = m.group(6);//流通比例
    		
		}
	}
}
