package com.gesangwu.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gesangwu.spider.biz.dao.model.JdStatis;
import com.gesangwu.spider.biz.service.JdStatisService;

@Controller
@RequestMapping("/jd")
public class JiadanController {
	
	@Resource
	private JdStatisService jdStatisService;
	
	@RequestMapping(value = "/r{range}", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request, String tradeDate, @PathVariable("range")String range){
		double min = 0;
		double max = 50;
		if("1".equals(range)){//一档
			max = 25;
		} else if("2".equals(range)){//二档
			min = 25;
		} else {
			if(!"a".equals(range)){
				range = "a";
			}
		}
		List<JdStatis> jdStatisList = jdStatisService.selectByTradeDate(tradeDate, min, max);
		ModelAndView mav = new ModelAndView("jdStatisList");
		mav.addObject("jdStatisList", jdStatisList);
		mav.addObject("amvRange", range);
		if(CollectionUtils.isNotEmpty(jdStatisList)){
			mav.addObject("tradeDate", jdStatisList.get(0).getTradeDate());
		} else {
			mav.addObject("tradeDate", tradeDate);
		}
		return mav;
	}
	
}
