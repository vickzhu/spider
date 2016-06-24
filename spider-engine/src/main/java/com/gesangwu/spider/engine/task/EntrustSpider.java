package com.gesangwu.spider.engine.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gandalf.framework.net.HttpTool;


/**
 * 分时成交
 * @author zhuxb
 *
 */

public class EntrustSpider {
	
	String regex = "\\[\\'([0-9\\:]*)\\', \\'([0-9\\.]*)\\', \\'([0-9]*)\\'\\]";
	Pattern r = Pattern.compile(regex);
	
	public void execute(){
		
		String result = HttpTool.get("http://vip.stock.finance.sina.com.cn/quotes_service/view/vML_DataList.php?asc=j&symbol=sz002265&num=11");
		System.out.println(result);
		Matcher matcher = r.matcher(result);
		while(matcher.find()){
			System.out.println(matcher.group(0));
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
			System.out.println(matcher.group(3));
		}
	}
	
	public static void main(String[] args){
		EntrustSpider spider = new EntrustSpider();
		spider.execute();
	}
}
