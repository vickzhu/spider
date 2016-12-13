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

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.biz.common.DecimalUtil;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.engine.util.UnicodeUtil;

/**
 * 公司信息更新
 * @author zhuxb
 *
 */
@Component
public class CompanyUpdateTask {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyUpdateTask.class);
	
	private static final String r1 = "\"day\"\\:\"([0-9\\-]*)\",\"count\"\\:([0-9]*).*\"items\"\\:\\[\\[(.*)\\]\\]\\}\\]\\)";
	private static final String r2 = "\"items\"\\:\\[\\[(.*)\\]\\]\\}\\]\\)";
	private static final Pattern p1 = Pattern.compile(r1);
	private static final Pattern p2 = Pattern.compile(r2);
	private static int cpp = 80;
	
	@Resource
	private CompanyService companyService;

	@Scheduled(cron="0 15 9 * * ?")
	public void execute(){
		long start = System.currentTimeMillis();
		
		String result = HttpTool.get(buildUrl(1));
		Matcher matcher = p1.matcher(result);
		if(!matcher.find()){
			return;
		}
		String totalCounts = matcher.group(2);
		int pages = (Integer.valueOf(totalCounts)+cpp-1)/cpp;
		String detailList = matcher.group(3);
		sou(detailList);
		for(int curPage = 2; curPage <= pages; curPage++){
			result = HttpTool.get(buildUrl(curPage));
			matcher = p2.matcher(result);
			if(!matcher.find()){
				continue;
			}
			detailList = matcher.group(1);
			sou(detailList);
		}	
		long end = System.currentTimeMillis();
		logger.info("Update Company use:" + (end-start)+"ms!");
	}
	
	private String buildUrl(int curPage){
		StringBuilder sb = new StringBuilder();
		sb.append("http://money.finance.sina.com.cn/d/api/openapi_proxy.php/?__s=[[%22hq%22,%22hs_a%22,%22%22,0,");
		sb.append(curPage);
		sb.append(SymbolConstant.COMMA);
		sb.append(cpp);
		sb.append("]]&callback=FDC_DC.theTableData");
		return sb.toString();
	}
	
	/**
	 * save or update
	 * @param detailList
	 */
	private void sou(String detailList){
		detailList = detailList.replaceAll("\"", "");
		String[] details = detailList.split("\\],\\[");
		Date now = new Date();
		for(String detail : details) {
			String[] columns = detail.split(SymbolConstant.COMMA);
			String symbol = columns[0];
			String code = columns[1];
			String stockName = columns[2];
			String encodeStockName = UnicodeUtil.decodeUnicode(stockName);
			String marketValue = columns[19];
			String circMarketValue = columns[20];
			String lastPrice = columns[8];
			double mv = Double.valueOf(marketValue)*10000;
			double fmv = DecimalUtil.format(Double.valueOf(circMarketValue)*10000, 2).doubleValue();
			Company company = companyService.selectBySymbol(symbol);
			if(company == null){
				company = new Company();
				company.setSymbol(symbol);
				company.setStockCode(code);
				company.setStockName(encodeStockName);
				company.setMarketValue(mv);
				company.setFloatMarketValue(fmv);
				company.setLastPrice(new BigDecimal(lastPrice));
				company.setGmtCreate(now);
				companyService.insert(company);
			} else {
				company.setStockName(encodeStockName);
				company.setMarketValue(mv);
				company.setFloatMarketValue(fmv);
				company.setLastPrice(new BigDecimal(lastPrice));
				company.setGmtUpdate(now);
				companyService.updateByPrimaryKey(company);
			}
		}
	}
}
