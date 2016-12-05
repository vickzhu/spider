package com.gesangwu.spider.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gesangwu.spider.biz.service.HolderNumService;

@Controller("/holder-num")
public class HolderNumController {
	
	@Resource
	private HolderNumService hnService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView mav(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		return mav;
	}
}
