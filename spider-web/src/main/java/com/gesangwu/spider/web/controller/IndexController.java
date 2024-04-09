package com.gesangwu.spider.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 * 这controller没什么用
 * @author zhuxb
 *
 */
@Controller
public class IndexController {
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
	
	@RequestMapping(value = "/demo",method = RequestMethod.GET)
	public ModelAndView demo(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("demo");
		return mav;
	}

}
