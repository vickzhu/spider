package com.gesangwu.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
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
		int shape = ShapeEnum.FIRST_NEG.getCode();
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
		mav.addObject("shapeEnum", ShapeEnum.values());
		mav.addObject("curShape", shape);
		return mav;
	}
	
	@RequestMapping(value = "/byPage", method = RequestMethod.GET)
	public ModelAndView listByPage(HttpServletRequest request){
		String pageStr = request.getParameter("curPage");
		int curPage = 1;
		if(StringUtil.isNotEmpty(pageStr)){
			curPage = Integer.valueOf(pageStr);
		}
		String shapeStr = request.getParameter("shape");
		ModelAndView mav = new ModelAndView("shapeByPage");
		if(StringUtil.isNotEmpty(shapeStr)){
			int shape = Integer.valueOf(shapeStr);
			KLineExample example = new KLineExample();
			example.setOrderByClause("trade_date desc, id desc");
			KLineExample.Criteria criteria = example.createCriteria();
			criteria.andShapeEqualTo(shape);
			Page<KLine> page = new Page<KLine>(curPage, 10);
			klService.selectByPagination(example, page);
			mav.addObject("page", page);
			mav.addObject("shape", shape);
		}
		mav.addObject("shapeEnum", ShapeEnum.values());
		return mav;
	}
}
