package com.gesangwu.spider.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gesangwu.spider.biz.service.StockDiaryService;

/**
 * 股票日记
 * @author zhuxb
 *
 */
@Controller
@RequestMapping("/diary")
public class StockDiaryController {
	
	@Resource
	private StockDiaryService diaryService;

	@RequestMapping(value = "/", method=RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
}
