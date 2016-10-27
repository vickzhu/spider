package com.gesangwu.spider.engine.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.DecimalUtil;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.HolderNum;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.HolderNumService;
import com.gesangwu.spider.engine.util.LittleCompanyHolder;

/**
 * 股东人数
 * @author zhuxb
 *
 */
@Component
public class ShareHolderNumTask {
	
	private static final String r="<div style=\"display\\:none\" id=\"gdrsData\" >\\[\\[(.*)\\]\\]</div>";
	private static final Pattern p = Pattern.compile(r);
	
	@Resource
	private CompanyService companyService;
	@Resource
	private HolderNumService hnService;
	
	public void execute(){
		List<Company> companyList = LittleCompanyHolder.getCompanyList();
		for (Company company : companyList) {
			String code = company.getStockCode();
			String symbol = StockUtil.code2Symbol(code);
			String url = buildUrl(code);
			String result = HttpTool.get(url);
			List<Integer> totalityList = buildTotailty(result);
			List<HolderNum> hnList = buildHolderNum(result);
			HolderNum latestHn = hnService.selectLatestBySymbol(symbol);
			for (int i = 0; i<hnList.size(); i++) {
				HolderNum holderNum = hnList.get(i);
				if(latestHn != null && holderNum.getEndDate().compareTo(latestHn.getEndDate()) < 1){
					break;
				}
				holderNum.setSymbol(symbol);
				holderNum.setTotality(totalityList.get(i));
				
			}
			
		}
	}

	public void execute1(String code){
		String symbol = StockUtil.code2Symbol(code);
		String url = buildUrl(code);
		String result = HttpTool.get(url);
		Matcher m = p.matcher(result);
		if(m.find()){
			String content = m.group(1);
			content = content.replaceAll("\"", StringUtil.EMPTY);
			String[] arr = content.split("\\],\\[");
			for (String columns : arr) {
				String[] columnArr = columns.split(SymbolConstant.COMMA);
				String endDate = columnArr[0];
				String total = columnArr[1];
				String price = columnArr[2];
				HolderNum hn = hnService.selectLatestBySymbol(symbol);
				if(hn == null){//第一条
					HolderNum holderNum = new HolderNum();
					holderNum.setEndDate(endDate);
					holderNum.setGmtCreate(new Date());
					holderNum.setPrice(Double.valueOf(price));
					holderNum.setSymbol(symbol);
					holderNum.setTotality(Integer.valueOf(total));
					//TODO 与上期的不同
				} else {
					if(endDate.compareTo(hn.getEndDate()) > 0){//大于数据库中的最大日期
						
					} else {//最新的数据都没有数据库中的新，直接退出
						break;
					}
				}
			}
		}
	}
	
	private List<Integer> buildTotailty(String result){
		List<Integer> list = new ArrayList<Integer>();
		Matcher m = p.matcher(result);
		String content = m.group(1);
		content = content.replaceAll("\"", StringUtil.EMPTY);
		String[] arr = content.split("\\],\\[");
		for (String columns : arr) {
			String[] columnArr = columns.split(SymbolConstant.COMMA);
			String total = columnArr[1];
			list.add(Integer.valueOf(total));
		}
		return list;
	}
	
	private static final String r3="<ul class=\"swiper-list\"><li>([0-9\\-]*)</li><li>([0-9\\.]*)</li><li>([0-9\\.]*)</li><li>";
	private static final Pattern p3 = Pattern.compile(r3);
	
	/**
	 * 最新股本
	 * @return
	 */
	private double latestLTGB(Company company, String result){
		Matcher m = p3.matcher(result);
		if(m.find()){
			double tmk = Double.valueOf(m.group(2));
			double fmk = Double.valueOf(m.group(3));
		}
		return 0;
	}
	
	private static final String r2="<ul class=\"swiper-list\"><li>([0-9\\-]*)</li><li>[0-9\\.]*</li><li class=\"c-[a-z]+\">([0-9\\.\\-%])</li>";
	private static final Pattern p2 = Pattern.compile(r2);
	
	private void execute(String code){
		String symbol = StockUtil.code2Symbol(code);
		String url = buildUrl(code);
		String result = HttpTool.get(url);
		Matcher m = p2.matcher(result);
		List<Double> list = new ArrayList<Double>();
		while(m.find()){
			String endDate = m.group(1);
			String chgStr = m.group(2);
			chgStr = chgStr.replace("%", StringUtil.EMPTY);
			double chg = Double.valueOf(chgStr);
			list.add(DecimalUtil.format(chg/100, 4).doubleValue());
		}
	}
	
	private List<HolderNum> buildHolderNum(String result){
		Matcher m = p2.matcher(result);
		List<HolderNum> list = new ArrayList<HolderNum>();
		while(m.find()){
			String endDate = m.group(1);
			String chgStr = m.group(2);
			chgStr = chgStr.replace("%", StringUtil.EMPTY);
			double chg = Double.valueOf(chgStr);
			double chgRate = DecimalUtil.format(chg/100, 4).doubleValue();
			HolderNum holderNum = new HolderNum();
			holderNum.setEndDate(endDate);
			holderNum.setGmtCreate(new Date());
			holderNum.setChgRate(chgRate);
		}
		return list;
	}
	
	private String buildUrl(String code){
		StringBuilder sb = new StringBuilder();
		sb.append("http://basic.10jqka.com.cn/mobile/");
		sb.append(code);
		sb.append("/holdern.html");
		return sb.toString();
	}
	
	public static void main(String[] args){
		ShareHolderNumTask task = new ShareHolderNumTask();
		task.execute("002810");
	}
}
