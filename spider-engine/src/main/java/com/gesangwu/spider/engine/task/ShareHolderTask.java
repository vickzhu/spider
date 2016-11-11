package com.gesangwu.spider.engine.task;

import java.nio.charset.Charset;
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
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.common.DecimalUtil;
import com.gesangwu.spider.biz.common.HolderType;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.CompanyExample;
import com.gesangwu.spider.biz.dao.model.ShareHolder;
import com.gesangwu.spider.biz.dao.model.StockShareHolder;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.ShareHolderService;
import com.gesangwu.spider.biz.service.StockShareHolderService;

/**
 * 十大流通股东
 * https://xueqiu.com/S/SH603099/LTGD
 * 恒生：http://open.hs.net/online-test/arescloud
 * @author zhuxb
 *
 */
@Component
public class ShareHolderTask {
	
	private static final String r = "\"publishdate\"\\:\"([0-9]*)\",\"enddate\"\\:\"([0-9]*)\",\"compcode\"\\:\"[0-9]*\",\"shholdercode\"\\:(\"[0-9]*\"|null),\"shholdername\"\\:\"([^\"]*)\",\"shholdertype\"\\:\"([^\"]*)\",\"rank1\":[^,]*,\"rank2\"\\:([0-9]{1,2}),\"holderamt\"\\:([0-9E\\.]*),\"holderrto\"\\:([0-9E\\.])*,\"pctoffloatshares\"\\:([0-9E\\.]*),\"sharestype\"\\:[^,]*,\"shholdernature\"\\:\"[^\"]*\",\"symbol\"\\:[^,]*,\"name\"\\:[^,]*,\"ishis\"\\:([0|1]),\"chg\"\\:([0-9E\\.\\-]*|null),";

	private static Pattern p = Pattern.compile(r);
	private static final String EARLIEST_DATE = "2015-01-01";
	
	@Resource
	private CompanyService companyService;
	@Resource
	private ShareHolderService shService;
	@Resource
	private StockShareHolderService sshService;
	
	public void execute(){
		String cookieUrl = "https://xueqiu.com/account/lostpasswd";
		HttpTool.get(cookieUrl);//这个链接只是为了获得cookie信息，因为后面的请求需要用到cookie
    	int cpp = 100;
		int count = companyService.countByExample(null);
		int totalPages = (count + cpp -1)/cpp;
		Date now = new Date();
		for(int cur = 1; cur <= totalPages; cur++){
			Page<Company> page = new Page<Company>(cur, cpp);
			companyService.selectByPagination(new CompanyExample(), page);
			List<Company> companyList = page.getRecords();
			for (Company company : companyList) {
				String symbol = company.getSymbol();
				String result = getContent(symbol);
				Matcher m = p.matcher(result);
				List<StockShareHolder> holderList = new ArrayList<StockShareHolder>();
		    	while(m.find()){    	
		    		String publishDate = formatDate(m.group(1));
		    		String endDate = formatDate(m.group(2));
		    		String latestDate = sshService.selectLatestDate(symbol);
		    		if(StringUtil.isNotBlank(latestDate) && latestDate.compareTo(endDate) >= 0){
		    			break;
		    		}
		    		if(EARLIEST_DATE.compareTo(endDate) > 0){
		    			break;
		    		}
		    		String holderCode = m.group(3).replaceAll("\"", StringUtil.EMPTY);
		    		String holderName = m.group(4);
		    		String holderType = m.group(5);
		    		String rank = m.group(6);
		    		String stockCounts = m.group(7);//持股数量
		    		String pctOfShares = m.group(8);//持股比例
		    		String pctOfFloatShares = m.group(9);//持流通股比例
		    		String isNotNew = m.group(10);
		    		String chgCount = m.group(11);
		    		Long holderId = souShareHolder(holderType, holderCode, holderName);
		    		StockShareHolder ssh = new StockShareHolder();
		    		ssh.setShareholder(holderId);
		    		ssh.setSymbol(company.getSymbol());
		    		ssh.setEndDate(endDate);
		    		Double sc = Double.valueOf(stockCounts);
		    		ssh.setHoldCount(sc.intValue());
		    		ssh.setHoldFloatRate(Double.valueOf(pctOfFloatShares));
		    		ssh.setHoldRate(Double.valueOf(pctOfShares));
		    		ssh.setRanking(Integer.valueOf(rank));
		    		ssh.setPublishDate(publishDate);
		    		if("1".equals(isNotNew)){//非新股东
		    			ssh.setIsNewHolder(0);
		    			if("null".equals(chgCount)){
		    				ssh.setChgCount(0);
		    				ssh.setChgRate(0d);
		    			} else {
		    				int changeCount = Double.valueOf(chgCount).intValue();
			    			double preSc = sc - changeCount;
			    			double chgRate = DecimalUtil.format(changeCount/preSc, 4).doubleValue();
			    			ssh.setChgCount(changeCount);
			    			ssh.setChgRate(chgRate);
		    			}		    			
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
	
	private String formatDate(String date){
		StringBuilder sb = new StringBuilder();
		sb.append(date.substring(0, 4));
		sb.append(SymbolConstant.H_LINE);
		sb.append(date.substring(4, 6));
		sb.append(SymbolConstant.H_LINE);
		sb.append(date.substring(6, 8));
		return sb.toString();
	}
	
	private String getContent(String symbol){
		String url = "https://xueqiu.com/stock/f10/otsholder.json?symbol=" + symbol;
		return HttpTool.get(url, Charset.forName("GBK"));
	}
	
	private Long souShareHolder(String holderType, String holderCode, String holderName){
		if("个人".equals(holderType)){//个人
			ShareHolder sh = shService.selectPersonByName(holderName);
			if(sh == null){
				sh = new ShareHolder();
				sh.setGmtCreate(new Date());
				sh.setHolderName(holderName);
				sh.setHolderType(HolderType.PERSON.getType());
				shService.insert(sh);
			}
			return sh.getId();
		} else {//机构
			ShareHolder sh = shService.selectByHoldCode(holderCode);
			if(sh == null){
				sh = new ShareHolder();
				sh.setGmtCreate(new Date());
				sh.setHolderCode(holderCode);
				sh.setHolderName(holderName);
				sh.setHolderType(HolderType.ORG.getType());
				shService.insert(sh);
			}
			return sh.getId();
		}
	}

}
