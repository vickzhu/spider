package com.gesangwu.spider.engine.task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.CompanyExample;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.StockShareholderService;

/**
 * 活跃流通股东
 * <pre>
 * 两种方法，偏向于第二种效率高
 * select stock_code,sum(hold_float_rate),end_date from shareholder where stock_code='sz002265' group by end_date order by end_date desc limit 1;
 * select t.stock_code,sum(t.hold_float_rate),end_date from (select * from shareholder where stock_code='sz002265' order by end_date desc limit 10) as t;
 * </pre>
 * @author zhuxb
 *
 */
@Component
public class FLoatShareCalc {
	
	@Resource
	private CompanyService companyService;
	@Resource
	private StockShareholderService sshService;	
	
//	@Scheduled(cron = "0 0 4 * * MON-FRI")
	public void execute(){
		int cpp = 20;
		int count = companyService.countByExample(null);
		int totalPages = (count + cpp -1)/cpp;
		Date now = new Date();
		for(int cur = 1; cur<=totalPages; cur++){
			System.out.println(cur);
			Page<Company> page = new Page<Company>(cur, cpp);
			companyService.selectByPagination(new CompanyExample(), page);
			List<Company> companyList = page.getRecords();
			for (Company company : companyList) {
				Double totalRate = sshService.calcFloatRate(company.getSymbol());
				if(totalRate == null){
					continue;
				}
				double floatMarketValue = company.getFloatMarketValue();
				double activeMarketValue = calc(floatMarketValue, totalRate);
				company.setActiveMarketValue(activeMarketValue);
				company.setGmtUpdate(now);
				companyService.updateByPrimaryKey(company);
			}
		}
	}
	
	private double calc(double floatMarketValue, double totalRate){
		double remainRate = 1 - totalRate/100;
		BigDecimal mv = BigDecimal.valueOf(floatMarketValue);
		BigDecimal rr = BigDecimal.valueOf(remainRate);
		BigDecimal amv = mv.multiply(rr).setScale(4,RoundingMode.HALF_UP);
		return amv.doubleValue();
	}
	
}
