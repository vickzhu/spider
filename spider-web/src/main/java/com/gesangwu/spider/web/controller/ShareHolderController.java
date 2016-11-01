package com.gesangwu.spider.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gesangwu.spider.biz.dao.model.HolderNumExample;
import com.gesangwu.spider.biz.service.HolderNumService;
import com.gesangwu.spider.biz.service.ShareHolderService;

@Controller
@RequestMapping("/shareholder")
public class ShareHolderController {
	
	@Resource
	private ShareHolderService holderService;
	@Resource
	private HolderNumService hnService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request){
		HolderNumExample example = new HolderNumExample();
		HolderNumExample.Criteria criteria = example.createCriteria();
		
		hnService.selectByExample(example);
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
}
