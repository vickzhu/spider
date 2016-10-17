package com.gesangwu.spider.web.controller;

import java.text.ParseException;
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
import com.gesangwu.spider.biz.dao.model.LargeVolStatis;
import com.gesangwu.spider.biz.dao.model.LargeVolStatisExample;
import com.gesangwu.spider.biz.service.LargeVolStatisService;

@Controller
@RequestMapping("/largeVol")
public class LargeVolStatisController {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	private LargeVolStatisService lvsService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request,String date) throws ParseException{
		Date time = new Date();
		if(StringUtil.isNotBlank(date)){
			time = sdf.parse(date);
		}
		LargeVolStatisExample example = new LargeVolStatisExample();
		LargeVolStatisExample.Criteria criteria = example.createCriteria();
		criteria.andDateEqualTo(time);
		example.setOrderByClause("gmt_update desc");
		List<LargeVolStatis> statisList = lvsService.selectByExample(example);
		ModelAndView mav = new ModelAndView("largeVolStatisList");
		mav.addObject("list", statisList);
		return mav;
	}
}
