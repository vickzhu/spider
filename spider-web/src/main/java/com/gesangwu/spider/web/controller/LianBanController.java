package com.gesangwu.spider.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.dao.model.LianBan;
import com.gesangwu.spider.biz.dao.model.LianBanExample;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.service.LianBanService;

@Controller
@RequestMapping("/zt")
public class LianBanController {
	
	@Resource
	private LianBanService lbService;
	@Resource
	private KLineService klService;
	@Resource
	private CompanyService companyService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView zt(HttpServletRequest request){
		String tradeDate = request.getParameter("date");
		if(StringUtil.isBlank(tradeDate)){			
			tradeDate = klService.selectLatestDate();
		}
		LianBanExample example = new LianBanExample();
		LianBanExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		List<LianBan> lbList = lbService.selectByExample(example);
		ModelAndView mav = new ModelAndView("zhangtingban");
		mav.addObject("lbList", lbList);
		mav.addObject("date", tradeDate);
		return mav;
	}
	
	@RequestMapping(value = "/add")
	public void add(HttpServletRequest request){
		String stockName = request.getParameter("stockName");
		String tradeDate = request.getParameter("tradeDate");
		Company company = companyService.selectByName(stockName);
		String symbol = company.getSymbol();
		KLineExample klExample = new KLineExample();
		KLineExample.Criteria criteria = klExample.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateEqualTo(tradeDate);
		List<KLine> klList = klService.selectByExample(klExample);
		if(CollectionUtils.isEmpty(klList)){
			KLine kl = klList.get(0);
			LianBan lb = new LianBan();
			lb.setDays(1);
			lb.setGmtCreate(new Date());
			lb.setMemo(null);
			lb.setPercent(kl.getPercent());
			lb.setShape(null);
			lb.setStatus(1);
			lb.setStockCode(kl.getSymbol());
			lb.setStockName(stockName);
			lb.setTradeDate(tradeDate);
			lbService.insert(lb);
		}
	}
	
	@RequestMapping(value = "/del")
	public void del(HttpServletRequest request){
		Long id = Long.valueOf(request.getParameter("id"));
		lbService.deleteByPrimaryKey(id);
	}
	
	@RequestMapping(value = "/update")
	public void update(HttpServletRequest request){
		
	}
}
