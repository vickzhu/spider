package com.gesangwu.spider.engine.task.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.CompanyExample;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.engine.test.BaseTest;
/**
 * 修复没有昨收数据
 * @author bran
 *
 */
public class FixXueQiuKLineTest extends BaseTest {
	
	@Resource
	private CompanyService companyService;
	@Resource
	private KLineService klService;

	@Test
	public void execute(){
		CompanyExample example = new CompanyExample();
		List<Company> companyList = companyService.selectByExample(example);
		for (Company company : companyList) {
			String symbol = company.getSymbol();
			updateKLine(symbol);
		}
	}
	
	public void updateKLine(String symbol){
		KLineExample example = new KLineExample();
		example.setOrderByClause("trade_date desc");
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateGreaterThanOrEqualTo("2018-02-07");
		criteria.andTradeDateLessThanOrEqualTo("2018-02-14");
		List<KLine> klList = klService.selectByExample(example);
		for(int i = 0; i < klList.size(); i++){
			if(i == klList.size() - 1){
				break;
			}
			KLine kl = klList.get(i);
			kl.setYesterdayClose(klList.get(i+1).getClose());
			klService.updateByPrimaryKey(kl);
		}
	}
}
