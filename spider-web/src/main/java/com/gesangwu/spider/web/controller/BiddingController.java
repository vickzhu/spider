package com.gesangwu.spider.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.Bidding;
import com.gesangwu.spider.biz.dao.model.BiddingExample;
import com.gesangwu.spider.biz.service.BiddingService;

@Controller
@RequestMapping("/bidding")
public class BiddingController {
	
	@Resource
	private BiddingService bidService;

	@RequestMapping
	public ModelAndView list(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("biddingList");
		String pageStr = request.getParameter("curPage");
		int curPage = 1;
		if(StringUtil.isNotBlank(pageStr)){
			curPage = Integer.valueOf(pageStr);
		}
		Page<Bidding> page = new Page<Bidding>(curPage);
		BiddingExample example = new BiddingExample();
		example.setOrderByClause("gmt_create desc");
		bidService.selectByPagination(example, page);
		mav.addObject("page", page);
		mav.addObject("curPage", curPage);
		return mav;
	}
	
}
