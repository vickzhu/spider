package com.gesangwu.spider.engine.task;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.util.StringUtil;
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
			execute(code);
		}
	}

	public void execute(String code){
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
				} else {
					if(endDate.compareTo(hn.getEndDate()) > 0){//大于数据库中的最大日期
						
					} else {//最新的数据都没有数据库中的新，直接退出
						break;
					}
				}
			}
		}
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
