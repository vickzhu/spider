package com.gesangwu.spider.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gesangwu.spider.biz.service.ShareHolderService;

@Controller
@RequestMapping("/shareholder")
public class ShareHolderController {
	
	@Resource
	private ShareHolderService holderService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
}
