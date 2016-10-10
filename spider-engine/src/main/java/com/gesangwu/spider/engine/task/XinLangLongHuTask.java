package com.gesangwu.spider.engine.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.biz.service.LongHuTypeService;

@Component
public class XinLangLongHuTask {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	private LongHuTypeService typeService;
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhdService;
	
	public void execute(){
		String url="http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/lhb/index.phtml";
		Date now = new Date();
		String date = sdf.format(now);
		String r = "showDetail\\('([0-9]{2})','([0-9]{6})','" + date + "',this\\)";
		Pattern p = Pattern.compile(r);
		String result = HttpTool.get(url);
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
		for (Map.Entry<String,List<String>> entry : longHuMap.entrySet()) {
			String code = entry.getKey();
			List<String> typeList = entry.getValue();
			StringBuilder sb1 = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			StringBuilder sb3 = new StringBuilder();
			LongHu longHu = new LongHu();
			Date d = new Date();
			for (String type : typeList) {
				LongHuType lhType = typeService.selectByType(type);
				if(lhType.getDateType() == 1){//一日
					sb1.append(SymbolConstant.COMMA);
					sb1.append(type);
				} else if(lhType.getDateType() == 3){//三日
					sb3.append(SymbolConstant.COMMA);
					sb3.append(type);
				} else if(lhType.getDateType() == 2){//二日
					sb2.append(SymbolConstant.COMMA);
					sb2.append(type);
				}
			}
			LongHuDetail detail = new LongHuDetail();
			detail.setGmtCreate(d);
			if(sb1.length() > 0){//有一日行情
				String type = sb1.substring(sb1.lastIndexOf(SymbolConstant.COMMA)+1);
				longHu.setYrType(sb1.substring(1));
				detail.setDateType(1);
				getLongHuDetail(type, code, date);
			}
			if(sb2.length() > 0){//有二日行情
				String type = sb2.substring(sb2.lastIndexOf(SymbolConstant.COMMA)+1);
				longHu.setErType(sb2.substring(1));
				detail.setDateType(2);
				getLongHuDetail(type, code, date);
			}
			if(sb3.length() > 0){
				String type = sb3.substring(sb3.lastIndexOf(SymbolConstant.COMMA)+1);
				longHu.setSrType(sb3.substring(1));
				detail.setDateType(3);
				getLongHuDetail(type, code, date);
			}
		}
	}
	
	private void getLongHuDetail(String type, String code, String date){
		String url = buildDetailUrl(type, code, date);
		String result = HttpTool.get(url);
		System.out.println(result);
	}
	
	private String buildDetailUrl(String type, String code, String date){
		StringBuilder sb = new StringBuilder();
		sb.append("http://vip.stock.finance.sina.com.cn/q/api/jsonp.php/var%20details=/InvestConsultService.getLHBComBSData?symbol=");
		sb.append(code);
		sb.append("&tradedate=");
		sb.append(date);
		sb.append("&type=");
		sb.append(type);
		return sb.toString();
	}
	
	public static void main(String[] args){
		String str = ",abc";
		System.out.println(str.substring(str.lastIndexOf(",")+1));
	}
}
