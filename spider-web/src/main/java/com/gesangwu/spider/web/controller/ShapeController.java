package com.gesangwu.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.KLineService;

/**
 * 准备用于显示均线形态
 * @author zhuxb
 *
 */
@Controller
public class ShapeController {
	
	@Resource
	private KLineService klService;
	
	@RequestMapping(value = "/shape", method = RequestMethod.GET)
	public ModelAndView shape(HttpServletRequest request){
		int shape = ShapeEnum.DI_BU.getCode();
		String shapeStr = request.getParameter("shape");
		if(StringUtil.isNotBlank(shapeStr)){
			shape = Integer.valueOf(shapeStr);
		}
		String date = request.getParameter("tradeDate");
		if(StringUtil.isBlank(date)){
			date = klService.selectLatestDate();
		}
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andShapeEqualTo(shape);
		criteria.andTradeDateEqualTo(date);
		List<KLine> klList = klService.selectByExample(example);
		ModelAndView mav = new ModelAndView("shape");
		mav.addObject("tradeDate", date);
		mav.addObject("klList", klList);
		return mav;
	}
}
