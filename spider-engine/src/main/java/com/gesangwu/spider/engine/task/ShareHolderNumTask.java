package com.gesangwu.spider.engine.task;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.CompanyExample;
import com.gesangwu.spider.biz.service.CompanyService;

/**
 * 股东人数
 * @author zhuxb
 *
 */
@Component
public class ShareHolderNumTask {
	
	@Resource
	private CompanyService companyService;

	public void execute(){
		String cookieUrl = "https://xueqiu.com/account/lostpasswd";
		HttpTool.get(cookieUrl);//这个链接只是为了获得cookie信息，因为后面的请求需要用到cookie
		int cpp = 10;
		int count = companyService.countByExample(null);
		int totalPages = (count + cpp -1)/cpp;
		Date now = new Date();
		for(int cur = 1; cur <= totalPages; cur++){
			Page<Company> page = new Page<Company>(cur);
			companyService.selectByPagination(new CompanyExample(), page);
			List<Company> companyList = page.getRecords();
			for (Company company : companyList) {
				String symbol = company.getSymbol();
				String url = "https://xueqiu.com/stock/f10/shareholdernum.json?page=1&size=10&symbol="+symbol;
				String result = HttpTool.get(url);
				System.out.println(result);
			}
		}
	}
}
