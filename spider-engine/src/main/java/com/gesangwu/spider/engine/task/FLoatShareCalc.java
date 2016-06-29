package com.gesangwu.spider.engine.task;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.CompanyExample;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.ShareHolderService;

/**
 * 浮动筹码计算
 * select stock_code,sum(hold_float_rate),end_date from shareholder where stock_code='sz002265' group by end_date order by end_date desc limit 1;
 * @author zhuxb
 *
 */
@Component
public class FLoatShareCalc {
	
	@Resource
	private CompanyService companyService;
	@Resource
	private ShareHolderService holderService;
	
	public void execute(){
		int cpp = 20;
		int count = companyService.countByExample(null);
		int totalPages = (count + cpp -1)/cpp;
		Date now = new Date();
		for(int cur = 1; cur<=totalPages; cur++){
			System.out.println(cur);
			Page<Company> page = new Page<Company>(cur);
			companyService.selectByPagination(new CompanyExample(), page);
			List<Company> companyList = page.getRecords();
			for (Company company : companyList) {
				
			}
		}
	}
	
}
