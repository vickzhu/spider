package com.gesangwu.spider.engine.task;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.FiveRangeStatis;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.FiveRangeStatisService;
import com.gesangwu.spider.engine.util.LittleCompanyHolder;
import com.gesangwu.spider.engine.util.TradeTimeUtil;

/**
 * 五档
 * <pre>
 * 这里的定时时间最长一分钟
 * </pre>
 * @author zhuxb
 *
 */
@Component
public class FiveRangeSpider {
	
	private static final Logger logger = LoggerFactory.getLogger(FiveRangeSpider.class);
	
	private static final String regex = "var hq_str_([\\w]{8})=\"(.*)\";";
	private static final Pattern r = Pattern.compile(regex);
	
	private static final int minVol = 500000;
	
	@Resource
	private CompanyService companyService;
	@Resource
	private FiveRangeStatisService statisService;
	
//	@Scheduled(cron = "0 0/1 9-14 * * MON-FRI")
	public void execute(){
		if(!TradeTimeUtil.checkTime()){
			return;
		}
		long start = System.currentTimeMillis();
		List<Company> companyList = LittleCompanyHolder.getCompanyList();
		int size = companyList.size();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < size; i++) {
			sb.append(companyList.get(i).getSymbol());
			sb.append(SymbolConstant.COMMA);
			if(i == size - 1){
				fetch(sb.toString());
				sb = new StringBuffer();
			} else {
				if(i != 0 && i % 499 == 0){
					fetch(sb.toString());
					sb = new StringBuffer();
				}
			}
		}
		long end = System.currentTimeMillis();
		logger.info("Fetch the FiveRange use:"+(end-start)+"ms!");
	}
	
	private void fetch(String symbolArr){
		String result = HttpTool.get("http://hq.sinajs.cn/etag.php?list=" + symbolArr);
		Matcher matcher = r.matcher(result);
		while(matcher.find()){
			boolean b = false;
			boolean s = false;
			String symbol = matcher.group(1);
			String detail = matcher.group(2);
			String[] details = detail.split(SymbolConstant.COMMA);
			String b_1_c = details[10];
			String b_2_c = details[12];
			String b_3_c = details[14];
			String b_4_c = details[16];
			String b_5_c = details[18];
			if(Integer.valueOf(b_1_c)>=minVol||Integer.valueOf(b_2_c)>=minVol||Integer.valueOf(b_3_c)>=minVol
					||Integer.valueOf(b_4_c)>=minVol||Integer.valueOf(b_5_c)>=minVol){
				b = true;
			}
			String s_1_c = details[20];
			String s_2_c = details[22];
			String s_3_c = details[24];
			String s_4_c = details[26];
			String s_5_c = details[28];
			if(Integer.valueOf(s_1_c)>=minVol||Integer.valueOf(s_2_c)>=minVol||Integer.valueOf(s_3_c)>=minVol
					||Integer.valueOf(s_4_c)>=minVol||Integer.valueOf(s_5_c)>=minVol){
				s = true;
			}
//			String buy_1_price = details[11];
//			String buy_2_price = details[13];
//			String buy_3_price = details[15];
//			String buy_4_price = details[17];
//			String buy_5_price = details[19];
//			String sell_1_price = details[21];
//			String sell_2_price = details[23];
//			String sell_3_price = details[25];
//			String sell_4_price = details[27];
//			String sell_5_price = details[29];
			String date = details[30];
			if(b||s){
				FiveRangeStatis statis = statisService.selectBySymbolAndDate(symbol, date);
				if(statis == null){
					statis = new FiveRangeStatis();
					if(b){
						statis.setBigBuy(1);
					}
					if(s){
						statis.setBigSell(1);
					}
					statis.setDate(date);
					statis.setStockName(details[0]);
					statis.setSymbol(symbol);
					statis.setGmtCreate(new Date());
					statisService.insert(statis);
				} else {
					if(b){
						statis.setBigBuy(statis.getBigBuy()+1);
					}
					if(s){
						statis.setBigSell(statis.getBigSell()+1);
					}
					statisService.updateByPrimaryKey(statis);
				}
			}
		}
	}

}
