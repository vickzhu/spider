package com.gesangwu.spider.web.controller;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.LargeVolStatis;
import com.gesangwu.spider.biz.dao.model.LargeVolStatisExample;
import com.gesangwu.spider.biz.service.LargeVolStatisService;

@Controller
@RequestMapping("/largeVol")
public class LargeVolController {
	
	private static final Logger logger = LoggerFactory.getLogger(LargeVolController.class);
	
	@Resource
	private LargeVolStatisService lvsService;

	@RequestMapping(value = "/r1", method = RequestMethod.GET)
	public ModelAndView list1(HttpServletRequest request,String tradeDate) throws ParseException{
		List<LargeVolStatis> statisList = lvsService.selectByTradeDate(tradeDate,0,25);
		ModelAndView mav = new ModelAndView("largeVolStatisR1List");
		mav.addObject("list", statisList);
		if(CollectionUtils.isNotEmpty(statisList)){
			mav.addObject("tradeDate", statisList.get(0).getTradeDate());
		} else {
			mav.addObject("tradeDate", tradeDate);
		}
		return mav;
	}
	
	@RequestMapping(value = "/r2", method = RequestMethod.GET)
	public ModelAndView list2(HttpServletRequest request,String tradeDate) throws ParseException{
		List<LargeVolStatis> statisList = lvsService.selectByTradeDate(tradeDate,25,50);
		ModelAndView mav = new ModelAndView("largeVolStatisR2List");
		mav.addObject("list", statisList);
		if(CollectionUtils.isNotEmpty(statisList)){
			mav.addObject("tradeDate", statisList.get(0).getTradeDate());
		} else {
			mav.addObject("tradeDate", tradeDate);
		}
		return mav;
	}
	
	@RequestMapping(value="/history", method = RequestMethod.GET)
	public ModelAndView history(HttpServletRequest request, String symbol){
		if(StringUtil.isBlank(symbol)){
			logger.debug("No symbol find for largeVol history!");
		}
		String curPageStr = request.getParameter("curPage");
		int curPage = 1;
		if(StringUtil.isNotBlank(curPageStr)){
			curPage = Integer.valueOf(curPageStr);
		}
		Page<LargeVolStatis> lvsPage = new Page<LargeVolStatis>(curPage, 20);
		ModelAndView mav = new ModelAndView("largeVolHistory");
		LargeVolStatisExample example = new LargeVolStatisExample();
		example.setOrderByClause("trade_date desc");
		LargeVolStatisExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		lvsService.selectByPagination(example, lvsPage);
		mav.addObject("page", lvsPage);
		mav.addObject("symbol", symbol);
		return mav;
	}
}
