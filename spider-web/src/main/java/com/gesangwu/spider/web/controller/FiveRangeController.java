package com.gesangwu.spider.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.dao.model.FiveRangeStatis;
import com.gesangwu.spider.biz.dao.model.FiveRangeStatisExample;
import com.gesangwu.spider.biz.service.FiveRangeStatisService;

@Controller
@RequestMapping("/fiverange")
public class FiveRangeController {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	private FiveRangeStatisService frss;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request,String date){
		if(StringUtil.isBlank(date)){
			Date now = new Date();
			date = sdf.format(now);
		}
		FiveRangeStatisExample example = new FiveRangeStatisExample();
		FiveRangeStatisExample.Criteria criteria = example.createCriteria();
		criteria.andDateEqualTo(date);
		example.setOrderByClause("gmt_update desc");
		List<FiveRangeStatis> statisList = frss.selectByExample(example);
		ModelAndView mav = new ModelAndView("fiveRangeList");
		mav.addObject("list", statisList);
		return mav;
	}
}
