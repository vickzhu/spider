package com.gesangwu.spider.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.web.tool.AjaxResult;

@Controller
@RequestMapping("/console")
public class ConsoleController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView console(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("console");
		return mav;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public AjaxResult doConsole(HttpServletRequest request){
		return new AjaxResult(true, null);
	}
}
