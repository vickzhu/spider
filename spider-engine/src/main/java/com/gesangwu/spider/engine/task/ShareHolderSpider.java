package com.gesangwu.spider.engine.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.CompanyExample;
import com.gesangwu.spider.biz.dao.model.ShareHolder;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.ShareHolderService;

/**
 * 股东
 * https://xueqiu.com/S/SH603099/LTGD
 * 恒生：http://open.hs.net/online-test/arescloud
 * @author zhuxb
 *
 */
@Component
public class ShareHolderSpider {
	
	private static final String r = "\"publishdate\"\\:\"[0-9]*\",\"enddate\"\\:\"([0-9]*)\",\"compcode\"\\:\"[0-9]*\",\"shholdercode\"\\:(\"[0-9]*\"|null),\"shholdername\"\\:\"([^\"]*)\",\"shholdertype\"\\:\"[^\"]*\",\"rank1\":null,\"rank2\"\\:([0-9]{1,2}),\"holderamt\"\\:([0-9E\\.]*),\"holderrto\"\\:[0-9\\.]*,\"pctoffloatshares\"\\:([0-9\\.]*),";

	private static Pattern p = Pattern.compile(r);
	
	@Resource
	private CompanyService companyService;
	@Resource
	private ShareHolderService shareHolderService;
	
	public void execute(){
		String cookieUrl = "https://xueqiu.com/account/lostpasswd";
		HttpTool.get(cookieUrl);//这个链接只是为了获得cookie信息，因为后面的请求需要用到cookie
    	int cpp = 10;
		int count = companyService.countByExample(null);
		int totalPages = (count + cpp -1)/cpp;
		Date now = new Date();
		for(int cur = 1; cur<=totalPages; cur++){
			System.out.println(cur);
			Page<Company> page = new Page<Company>(cur);
			companyService.selectByPagination(new CompanyExample(), page);
			List<Company> companyList = page.getRecords();
			for (Company company : companyList) {
				String symbol = company.getSymbol();
				String url = "https://xueqiu.com/stock/f10/otsholder.json?symbol="+symbol;
				String result = HttpTool.get(url);
				Matcher m = p.matcher(result);
				List<ShareHolder> holderList = new ArrayList<ShareHolder>();
		    	while(m.find()){    		
		    		String endDate = m.group(1);
		    		String holderCode = m.group(2);
		    		if("null".equals(holderCode)){
		    			holderCode = null;
		    		} else {
		    			holderCode = holderCode.replaceAll("\"", StringUtil.EMPTY);
		    		}
		    		String holderName = m.group(3);
		    		String rank = m.group(4);
		    		String counts = m.group(5);//持股数量
		    		String pctOfFloatShares = m.group(6);//流通比例
		    		ShareHolder holder = new ShareHolder();
		    		holder.setStockCode(company.getSymbol());
		    		holder.setEndDate(endDate);
		    		holder.setHoldCount(Double.valueOf(counts));
		    		holder.setHolderCode(holderCode);
		    		holder.setHolderName(holderName);
		    		holder.setHoldFloatRate(Double.valueOf(pctOfFloatShares));
//		    		holder.setHoldRate(Double.valueOf(d));
		    		holder.setRanking(Integer.valueOf(rank));
		    		holder.setGmtCreate(now);
		    		holderList.add(holder);
		    	}
		    	shareHolderService.insertShareHolderBatch(holderList);
			}
		}
	}

	public static void main(String[] args) { 
		ShareHolderSpider spider = new ShareHolderSpider();
		spider.execute();
	}

}
