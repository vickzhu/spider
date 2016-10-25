package com.gesangwu.spider.engine.task;

import java.math.BigDecimal;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.engine.util.UnicodeUtil;

/**
 * 公司信息更新，新浪的公司缺失，163的数据不准确
 * 参数
 * SYMBOL	代码
	NAME	
	PRICE	收盘价	
	PERCENT	涨跌幅	
	UPDOWN	涨跌额
	OPEN	开盘
	HIGH	最高
	LOW		最低
	VOLUME	成交量
	HS		换手率
	MCAP	流通市值
	TCAP	总市值
 * http://quotes.money.163.com/hs/service/diyrank.php?page=0&query=STYPE%3AEQA&fields=SYMBOL%2CNAME%2CPRICE%2CPERCENT%2CUPDOWN%2COPEN%2CHIGH%2CLOW%2CVOLUME%2CHS%2CMCAP%2CTCAP&sort=PERCENT&order=desc&count=24&type=query
 * @author zhuxb
 *
 */
@Component
public class CompanyUpdateTask_163 {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyUpdateTask_163.class);

	private static final String r1 = "\"total\"\\:([0-9]*),\"pagecount\"\\:([0-9]*)";
	private static final String r2 = "\"MCAP\"\\:([0-9\\.]*),\"NAME\"\\:\"([^\"]*)\",\"PRICE\"\\:([0-9\\.]*),\"SYMBOL\"\\:\"([0-9]*)\",\"TCAP\"\\:([0-9\\.]*)";
	private static final Pattern p1 = Pattern.compile(r1);
	private static final Pattern p2 = Pattern.compile(r2);
	private static final String r3 = "\"HIGH\"\\:([0-9\\.]*),\"HS\"\\:([0-9\\.]*),\"LOW\"\\:([0-9\\.]*),\"MCAP\"\\:([0-9\\.]*),\"NAME\"\\:\"([^\"]*)\",\"OPEN\"\\:([0-9\\.]*),\"PERCENT\"\\:([0-9\\.]*),\"PRICE\"\\:([0-9\\.]*),\"SYMBOL\"\\:\"([0-9]*)\",\"TCAP\"\\:([0-9\\.]*),\"UPDOWN\"\\:([0-9\\.]*),\"VOLUME\"\\:([0-9\\.]*),";
	private static final Pattern p3 = Pattern.compile(r3);
	
	@Resource
	private CompanyService companyService;

	@Scheduled(cron="0 15 9 * * ?")
	public void execute(){
		long start = System.currentTimeMillis();
		String result = HttpTool.get(buildUrl(0));
		Matcher matcher = p1.matcher(result);
		if(!matcher.find()){
			return;
		}
		int pages = Integer.valueOf(matcher.group(2));
		sou(result);
		for(int curPage = 1; curPage < pages; curPage++){
			result = HttpTool.get(buildUrl(curPage));
			sou(result);
		}	
		long end = System.currentTimeMillis();
		logger.info("Update Company use:" + (end-start)+"ms!");
	}
	
	private String buildUrl(int page){
		StringBuilder sb = new StringBuilder();
		sb.append("http://quotes.money.163.com/hs/service/diyrank.php?query=STYPE%3AEQA&fields=NO%2CSYMBOL%2CNAME%2CPRICE%2CTCAP%2CMCAP&sort=SYMBOL&order=asc&count=100&type=query&page="+page);
		return sb.toString();
	}
	
	/**
	 * save or update
	 * @param detailList
	 */
	private void sou(String result){
		Matcher m = p2.matcher(result);
		Date now = new Date();
		while(m.find()){
			String code = m.group(4);
			String symbol = StockUtil.code2Symbol(code);
			String stockName = m.group(2);
			String encodeStockName = UnicodeUtil.decodeUnicode(stockName);
			String marketValue = m.group(5);
			String circMarketValue = m.group(1);
			String lastPrice = m.group(3);
			Company company = companyService.selectBySymbol(symbol);
			if(company == null){
				company = new Company();
				company.setSymbol(symbol);
				company.setStockCode(code);
				company.setStockName(encodeStockName);
				company.setMarketValue(Double.valueOf(marketValue));
				company.setFloatMarketValue(Double.valueOf(circMarketValue));
				company.setLastPrice(new BigDecimal(lastPrice));
				company.setGmtCreate(now);
				companyService.insert(company);
			} else {
				company.setStockName(encodeStockName);
				company.setMarketValue(Double.valueOf(marketValue));
				company.setFloatMarketValue(Double.valueOf(circMarketValue));
				company.setLastPrice(new BigDecimal(lastPrice));
				company.setGmtUpdate(now);
				companyService.updateByPrimaryKey(company);
			}
		}
	
	}

}
