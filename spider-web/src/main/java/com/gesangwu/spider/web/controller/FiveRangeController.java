package com.gesangwu.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gesangwu.spider.biz.dao.model.FiveRangeStatis;
import com.gesangwu.spider.biz.service.FiveRangeStatisService;

@Controller
@RequestMapping("/fiverange")
public class FiveRangeController {
	
	@Resource
	private FiveRangeStatisService frss;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request,String tradeDate){
		List<FiveRangeStatis> statisList = frss.selectByTradeDate(tradeDate);
		ModelAndView mav = new ModelAndView("fiveRangeList");
		mav.addObject("list", statisList);
		return mav;
	}
}
