package com.gesangwu.spider.web.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.web.util.StockNameCache;

@Component
public class InitContext implements InitializingBean {
	
	@Resource
	private CompanyService companyService;

	@Override
	public void afterPropertiesSet() throws Exception {
		List<Company> list = companyService.selectByExample(null);
		Map<String, String> nameSymbolMap = new HashMap<String, String>();
		for (Company company : list) {
			String symbol = company.getSymbol();
			String stockName = company.getStockName();
			nameSymbolMap.put(symbol, stockName);
		}
		StockNameCache.setNameSymbolMap(nameSymbolMap);
	}

}
