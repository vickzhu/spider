package com.gesangwu.spider.web.controller;

import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.LongHuService;

@Controller
public class CommonController {
	
	@Resource
	private LongHuService longHuService;
	@Resource
	private CompanyService companyService;
	
	private static final String r1 = "[0-9]{6}";
	private static final String r2 = "(sh|sz)[0-9]{6}";
	private static Pattern p1 = Pattern.compile(r1);
	private static Pattern p2 = Pattern.compile(r2);

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(HttpServletRequest request){
		String keyword = request.getParameter("keyword");
		String symbol = null;
		if(p1.matcher(keyword).matches()){
			symbol = StockUtil.code2Symbol(keyword);
		} else if (p2.matcher(keyword).matches()){
			symbol = keyword;
		} else {
			Company company = companyService.selectByName(keyword);
			if(company != null){
				symbol = company.getSymbol();
			}
		}
		return "forward:/longhu/detail?symbol="+symbol;
	}
}
