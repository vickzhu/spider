package com.gesangwu.spider.engine.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gandalf.framework.net.HttpTool;

/**
 * 股东
 * https://xueqiu.com/S/SH603099/LTGD
 * 恒生：http://open.hs.net/online-test/arescloud
 * @author zhuxb
 *
 */
public class ShareHolderSpider {
	
	private static final String r1 = "\\{\"enddate\"\\:\"([0-9]*)\",.*,\"list\"\\:\\[(.*)\\]\\}";
	private static final String r2 = "\"enddate\"\\:\"[0-9]*\",.*,\"shholdername\"\\:\"(.*)\",.*,\"holderamt\"\\:(.*),.*,\"pctoffloatshares\"\\:(.*),.*,\"ishis\"\\:[0-9]";
	private Pattern p1 = Pattern.compile(r1);
	private Pattern p2 = Pattern.compile(r2);
	
	public void execute(){
		String cookieUrl = "https://xueqiu.com/account/lostpasswd";
		String url = "https://xueqiu.com/stock/f10/otsholder.json?symbol=SZ002265&page=1&size=4&_=1466991669895";
		HttpTool.get(cookieUrl);//这个链接只是为了获得cookie信息，因为后面的请求需要用到cookie
    	String result = HttpTool.get(url);
    	Matcher m1 = p1.matcher(result);
    	while(m1.find()){
    		System.out.println(m1.group(1));
    		System.out.println(m1.group(2));
    		Matcher m2 = p2.matcher(m1.group(2));
    		while(m2.find()){
    			System.out.println(m2.group(1));
    		}
    	}
	}

	public static void main(String[] args) { 
		ShareHolderSpider spider = new ShareHolderSpider();
		spider.execute();
	}

}
