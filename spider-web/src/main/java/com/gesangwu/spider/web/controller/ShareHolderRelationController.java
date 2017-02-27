package com.gesangwu.spider.web.controller;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.web.tool.AjaxResult;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.ext.StockShareHolderExt;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.StockShareHolderService;

/**
 * 股东关联
 * @author zhuxb
 *
 */
@Controller
@RequestMapping("/shareholder/relation")
public class ShareHolderRelationController {
	
	private static final String r1 = "[0-9]{6}";
	private static final String r2 = "(sh|sz)[0-9]{6}";
	private static Pattern p1 = Pattern.compile(r1);
	private static Pattern p2 = Pattern.compile(r2);
	
	@Resource
	private CompanyService companyService;
	@Resource
	private StockShareHolderService sshService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("shareholderRelation");
		return mav;
	}
	
	/**
	 * 搜索股票
	 * @param request
	 */
	@RequestMapping(value="/search", method = RequestMethod.GET)
	public AjaxResult search(HttpServletRequest request){
		String keyword = request.getParameter("keyword");
		Company company = null;
		if(p1.matcher(keyword).matches()){
			String symbol = StockUtil.code2Symbol(keyword);
			company = companyService.selectBySymbol(symbol);
		} else if (p2.matcher(keyword).matches()){
			company = companyService.selectBySymbol(keyword);
		} else {
			company = companyService.selectByName(keyword);
		}
		return new AjaxResult(company == null, null, company);
	}
	
	/**
	 * 分析股票
	 * @param request
	 */
	@RequestMapping(value="/analyze", method = RequestMethod.GET)
	public void analyze(HttpServletRequest request){
		String[] symbolArr = request.getParameterValues("");
	}
	
	
	private List<StockShareHolderExt> getSshList(String symbol){
		return sshService.selectLatestBySymbol(symbol);
	}
}
