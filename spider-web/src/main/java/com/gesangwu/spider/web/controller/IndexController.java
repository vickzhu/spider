package com.gesangwu.spider.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 这controller没什么用
 * @author zhuxb
 *
 */
@Controller
public class IndexController {
	
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public void index(HttpServletRequest request, HttpServletResponse response){
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
			System.out.println(cookie.getName() + ":" + cookie.getValue());
		}
	}

}
