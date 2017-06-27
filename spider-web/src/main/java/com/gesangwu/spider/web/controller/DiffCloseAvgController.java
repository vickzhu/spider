package com.gesangwu.spider.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.KLineService;

@Controller
@RequestMapping("/dca")
public class DiffCloseAvgController {
	
	@Resource
	private KLineService kLineService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request){
		String date = request.getParameter("tradeDate");
		if(StringUtil.isBlank(date)){
			date = kLineService.selectLatestDate();
		}
		int curPage = 1;
		String curPageStr = request.getParameter("curPage");
		if(StringUtil.isNotBlank(curPageStr)){
			curPage = Integer.valueOf(curPageStr);
		}
		Page<KLine> page = new Page<KLine>(curPage, 20);		
		KLineExample example = new KLineExample();
		example.setOrderByClause("diff_close_avg");
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(date);
		criteria.andDiffCloseAvgLessThan(0d);
		kLineService.selectByPagination(example, page);
		ModelAndView mav = new ModelAndView("diffCloseAvg");
		mav.addObject("page", page);
		mav.addObject("tradeDate", date);
		return mav;
	}
	
	
}
