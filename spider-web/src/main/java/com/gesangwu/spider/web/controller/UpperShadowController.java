package com.gesangwu.spider.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.UpperShadow;
import com.gesangwu.spider.biz.dao.model.UpperShadowExample;
import com.gesangwu.spider.biz.service.UpperShadowService;

@Controller
@RequestMapping("/upper-shadow")
public class UpperShadowController {
	
	@Resource
	private UpperShadowService usService;

	@RequestMapping(value = "/")
	public ModelAndView list(HttpServletRequest request){
		int curPage = 1;
		String curPageStr = request.getParameter("curPage");
		if(StringUtil.isNotBlank(curPageStr)){
			curPage = Integer.valueOf(curPageStr);
		}
		Page<UpperShadow> page = new Page<UpperShadow>(curPage, 15);
		UpperShadowExample example = new UpperShadowExample();
		example.setOrderByClause("trade_date desc");
		usService.selectByPagination(example, page);
		ModelAndView mav = new ModelAndView("upperShadowList");
		mav.addObject("page", page);
		return mav;
	}
}
