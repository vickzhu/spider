package com.gesangwu.spider.engine.task;

import com.gandalf.framework.net.HttpTool;

/**
 * 股东
 * https://xueqiu.com/S/SH603099/LTGD
 * 恒生：http://open.hs.net/online-test/arescloud
 * @author zhuxb
 *
 */
public class ShareHolderSpider {
	
	public void execute(){
		String cookieUrl = "https://xueqiu.com/account/lostpasswd";
		String url = "https://xueqiu.com/stock/f10/otsholder.json?symbol=SZ002265&page=1&size=4&_=1466991669895";
		HttpTool.get(cookieUrl);//这个链接只是为了获得cookie信息，因为后面的请求需要用到cookie
    	String result = HttpTool.get(url);
		System.out.println(result);
	}

	public static void main(String[] args) { 
		ShareHolderSpider spider = new ShareHolderSpider();
		spider.execute();
	}

}
