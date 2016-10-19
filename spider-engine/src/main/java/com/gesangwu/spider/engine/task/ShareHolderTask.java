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
import com.gesangwu.spider.biz.dao.model.StockShareholder;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.ShareHolderService;
import com.gesangwu.spider.biz.service.StockShareholderService;

/**
 * 十大流通股东
 * https://xueqiu.com/S/SH603099/LTGD
 * 恒生：http://open.hs.net/online-test/arescloud
 * @author zhuxb
 *
 */
@Component
public class ShareHolderTask {
	
	private static final String r = "\"publishdate\"\\:\"([0-9]*)\",\"enddate\"\\:\"([0-9]*)\",\"compcode\"\\:\"[0-9]*\",\"shholdercode\"\\:(\"[0-9]*\"|null),\"shholdername\"\\:\"([^\"]*)\",\"shholdertype\"\\:\"[^\"]*\",\"rank1\":null,\"rank2\"\\:([0-9]{1,2}),\"holderamt\"\\:([0-9E\\.]*),\"holderrto\"\\:([0-9\\.])*,\"pctoffloatshares\"\\:([0-9\\.]*),\"sharestype\"\\:null,\"shholdernature\"\\:\"[^\"]*\",\"symbol\"\\:null,\"name\"\\:null,\"ishis\"\\:([0|1]),\"chg\"\\:([0-9\\.]*),";

	private static Pattern p = Pattern.compile(r);
	
	@Resource
	private CompanyService companyService;
	@Resource
	private ShareHolderService shService;
	@Resource
	private StockShareholderService sshService;
	
	public void execute(){
		String cookieUrl = "https://xueqiu.com/account/lostpasswd";
		HttpTool.get(cookieUrl);//这个链接只是为了获得cookie信息，因为后面的请求需要用到cookie
    	int cpp = 100;
		int count = companyService.countByExample(null);
		int totalPages = (count + cpp -1)/cpp;
		Date now = new Date();
		for(int cur = 1; cur <= totalPages; cur++){
			Page<Company> page = new Page<Company>(cur);
			companyService.selectByPagination(new CompanyExample(), page);
			List<Company> companyList = page.getRecords();
			for (Company company : companyList) {
				String symbol = company.getSymbol();
				String url = "https://xueqiu.com/stock/f10/otsholder.json?symbol="+symbol;
				String result = HttpTool.get(url);
				Matcher m = p.matcher(result);
				List<StockShareholder> holderList = new ArrayList<StockShareholder>();
		    	while(m.find()){    	
		    		String publishDate = m.group(1);
		    		String endDate = m.group(2);
		    		String holderCode = m.group(3);
		    		String holderName = m.group(4);
		    		String rank = m.group(5);
		    		String stockCounts = m.group(6);//持股数量
		    		String pctOfShares = m.group(7);//持股比例
		    		String pctOfFloatShares = m.group(8);//持流通股比例
		    		String isNotNew = m.group(9);
		    		String chgCount = m.group(10);
		    		Long holderId = souShareHolder(holderCode, holderName);
		    		StockShareholder ssh = new StockShareholder();
		    		ssh.setShareholder(holderId);
		    		ssh.setSymbol(company.getSymbol());
		    		ssh.setEndDate(endDate);
		    		ssh.setHoldCount(Integer.valueOf(stockCounts));
		    		ssh.setHoldFloatRate(Double.valueOf(pctOfFloatShares));
		    		ssh.setHoldRate(Double.valueOf(pctOfShares));
		    		ssh.setRanking(Integer.valueOf(rank));
		    		ssh.setPublishDate(publishDate);
		    		if("1".equals(isNotNew)){//非新股东
		    			ssh.setIsNewHolder(0);
		    			ssh.setChgCount(Integer.valueOf(chgCount));
		    		} else {
		    			ssh.setIsNewHolder(1);
		    		}
		    		ssh.setGmtCreate(now);
		    		holderList.add(ssh);
		    	}
		    	sshService.insertBatch(holderList);
			}
		}
	}
	
	private Long souShareHolder(String holderCode, String holderName){
		if("null".equals(holderCode)){//个人
			holderCode = null;
			ShareHolder sh = shService.selectPersonByName(holderName);
			if(sh == null){
				sh = new ShareHolder();
				sh.setGmtCreate(new Date());
				sh.setHolderName(holderName);
				sh.setHolderType(1);
				shService.insert(sh);
			}
			return sh.getId();
		} else {//机构
			holderCode = holderCode.replaceAll("\"", StringUtil.EMPTY);
			ShareHolder sh = shService.selectByHoldCode(holderCode);
			if(sh == null){
				sh = new ShareHolder();
				sh.setGmtCreate(new Date());
				sh.setHolderCode(holderCode);
				sh.setHolderName(holderName);
				sh.setHolderType(2);
				shService.insert(sh);
			}
			return sh.getId();
		}
	}
}
