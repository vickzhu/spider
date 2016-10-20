package com.gesangwu.spider.engine.task.init;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.engine.util.UnicodeUtil;

/**
 * 公司初始化
 * <code>
 * http://finance.sina.com.cn/data/#stock-schq-hsgs-qbag
 * </code>
 * @author zhuxb
 *
 */
@Component
public class CompanyInit {
	
	@Resource
	private CompanyService companyService;
	/**
	 * 编码，代码，股票名称，最新价，涨跌额，涨跌幅，买入，卖出，昨收，今开，最高，最低，成交量，成交额，时间，，，，市净率，总市值，流通市值，换手率，favor，guba
	 * */
	private static final String r1 = "\"day\"\\:\"([0-9\\-]*)\",\"count\"\\:([0-9]*).*\"items\"\\:\\[\\[(.*)\\]\\]\\}\\]\\)";
	private static final String r2 = "\"items\"\\:\\[\\[(.*)\\]\\]\\}\\]\\)";
	private static final Pattern p1 = Pattern.compile(r1);
	private static final Pattern p2 = Pattern.compile(r2);
	private static int cpp = 80;
	
//	@Scheduled(cron="0/5 * *  * * ? ") 
	public void execute() {
		companyService.deleteByExample(null);
		String result = HttpTool.get("http://money.finance.sina.com.cn/d/api/openapi_proxy.php/?__s=[[%22hq%22,%22hs_a%22,%22%22,0,1,"+cpp+"]]&callback=FDC_DC.theTableData");
		System.out.println(result);
		Matcher matcher = p1.matcher(result);
		if(!matcher.find()){
			return;
		}
//		String date = matcher.group(1);
		String totalCounts = matcher.group(2);
		int pages = (Integer.valueOf(totalCounts)+cpp-1)/cpp;
		String detailList = matcher.group(3);
		List<Company> companyList = parse(detailList);
		for(int curPage = 2; curPage <= pages; curPage++){
			result = HttpTool.get("http://money.finance.sina.com.cn/d/api/openapi_proxy.php/?__s=[[%22hq%22,%22hs_a%22,%22%22,0,"+curPage+","+cpp+"]]&callback=FDC_DC.theTableData");
			matcher = p2.matcher(result);
			if(!matcher.find()){
				continue;
			}
			detailList = matcher.group(1);
			companyList.addAll(parse(detailList));
			System.out.println(companyList.size());
		}	
		companyService.batchInsert(companyList);
	}
	
	private static List<Company> parse(String detailList){
		List<Company> companyList = new ArrayList<Company>();
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
			Company company = new Company();
			company.setSymbol(symbol);
			company.setStockCode(code);
			company.setStockName(encodeStockName);
			company.setMarketValue(Double.valueOf(marketValue));
			company.setFloatMarketValue(Double.valueOf(circMarketValue));
			company.setLastPrice(new BigDecimal(lastPrice));
			company.setGmtCreate(now);
			companyList.add(company);
			
		}
		return companyList;
	}

}
