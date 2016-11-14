package com.gesangwu.spider.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView doLogin(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value="/exit", method = RequestMethod.GET)
	public ModelAndView exit(HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		return mav;
	}
}
