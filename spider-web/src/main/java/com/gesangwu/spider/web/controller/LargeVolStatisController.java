package com.gesangwu.spider.web.controller;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gesangwu.spider.biz.dao.model.LargeVolStatis;
import com.gesangwu.spider.biz.service.LargeVolStatisService;

@Controller
@RequestMapping("/largeVol")
public class LargeVolStatisController {
	
	@Resource
	private LargeVolStatisService lvsService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request,String tradeDate) throws ParseException{
		List<LargeVolStatis> statisList = lvsService.selectByTradeDate(tradeDate);
		ModelAndView mav = new ModelAndView("largeVolStatisList");
		mav.addObject("list", statisList);
		return mav;
	}
}
