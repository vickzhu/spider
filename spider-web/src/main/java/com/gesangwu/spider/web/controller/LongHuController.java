package com.gesangwu.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;

@Controller
@RequestMapping(value="/longhu")
public class LongHuController {
	
	private static final Logger logger = LoggerFactory.getLogger(LongHuController.class);
	
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhDetailService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, String tradeDate){
		ModelAndView mav = new ModelAndView("longHu");
		List<LongHu> lhList = lhService.selectByTradeDate(tradeDate);
		mav.addObject("lhList", lhList);
		return mav;
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request){
		String symbol = request.getParameter("symbol");
		String tradeDate = request.getParameter("tradeDate");
		if(StringUtil.isBlank(symbol) || StringUtil.isBlank(tradeDate)){
			logger.debug("Miss symbol or tradeDate!");
			return null;
		}
		List<LongHuDetail> detailList = lhDetailService.selectBySymbolAndDate(symbol, tradeDate);
		ModelAndView mav = new ModelAndView("longHuDetail");
		mav.addObject("detailList", detailList);
		return mav;
	}
	
}
