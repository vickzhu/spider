package com.gesangwu.spider.web.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.SecDept;
import com.gesangwu.spider.biz.dao.model.SecDeptExample;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.SecDeptService;
import com.gesangwu.spider.web.util.SecDeptSeatCache;
import com.gesangwu.spider.web.util.StockNameCache;

@Component
public class InitContext implements InitializingBean {
	
	@Resource
	private CompanyService companyService;
	@Resource
	private SecDeptService sdService;

	@Override
	public void afterPropertiesSet() throws Exception {
		stockNameCache();
		deptSecSeatCache();
	}
	
	/**
	 * 股票名称缓存
	 */
	private void stockNameCache(){
		List<Company> list = companyService.selectByExample(null);
		Map<String, String> nameSymbolMap = new HashMap<String, String>();
		for (Company company : list) {
			String symbol = company.getSymbol();
			String stockName = company.getStockName();
			nameSymbolMap.put(symbol, stockName);
		}
		StockNameCache.setNameSymbolMap(nameSymbolMap);
	}
	
	/**
	 * 席位缓存
	 */
	private void deptSecSeatCache(){
		SecDeptExample example = new SecDeptExample();
		SecDeptExample.Criteria criteria = example.createCriteria();
		criteria.andDeptAddrShortIsNotNull();
		List<SecDept> list = sdService.selectByExample(example);
		Map<String, String> seatMap = new HashMap<String, String>();
		for (SecDept secDept : list) {
			seatMap.put(secDept.getCode(), secDept.getDeptAddrShort());
		}
		SecDeptSeatCache.setSeatMap(seatMap);
	}

}
