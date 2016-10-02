package com.gesangwu.spider.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.common.LongHuDetailPair;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExt;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.dao.model.SecDept;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.biz.service.LongHuTypeService;
import com.gesangwu.spider.biz.service.SecDeptService;

@Controller
@RequestMapping(value="/longhu")
public class LongHuController {
	
	private static final Logger logger = LoggerFactory.getLogger(LongHuController.class);
	
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhDetailService;
	@Resource
	private LongHuTypeService lhTypeService;
	@Resource
	private SecDeptService secDeptService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, String tradeDate){
		ModelAndView mav = new ModelAndView("longHu");
		List<LongHu> lhList = lhService.selectByTradeDate(tradeDate);
		mav.addObject("lhList", lhList);
		mav.addObject("tradeDate", StringUtil.isBlank(tradeDate)?lhList.get(0).getTradeDate():tradeDate);
		return mav;
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request){
		String symbol = request.getParameter("symbol");
		String tradeDate = request.getParameter("tradeDate");
		if(StringUtil.isBlank(symbol) || StringUtil.isBlank(tradeDate)){
			logger.error("Miss symbol or tradeDate!");
			return null;
		}
		LongHu longHu = lhService.selectBySymbolAndTradeDate(symbol, tradeDate);
		String drTypes = longHu.getDrLhType();
		List<String> drTypeList = getTypeDes(drTypes);
		String mlTypes = longHu.getSrLhType();
		List<String> mlTypeList = getTypeDes(mlTypes);
		LongHuDetailPair pairs = lhDetailService.selectDetailPairs(symbol, tradeDate, 0);
		
		ModelAndView mav = new ModelAndView("longHuDetail");
		mav.addObject("buyList", pairs.getBuyList());
		mav.addObject("sellList", pairs.getSellList());
		mav.addObject("longHu", longHu);
		mav.addObject("drTypeList", drTypeList);
		mav.addObject("mlTypeList", mlTypeList);
		return mav;
	}
	
	@RequestMapping(value = "/sec-dept", method = RequestMethod.GET)
	public ModelAndView bySec(HttpServletRequest request){
		String deptCode = request.getParameter("deptCode");
		int curPage = 1;
		String pageStr = request.getParameter("curPage");
		if(StringUtil.isNotBlank(pageStr)){
			curPage = Integer.valueOf(pageStr);
		}
		Page<LongHuDetailExt> page = new Page<LongHuDetailExt>(curPage, 20);
		LongHuDetailExample example = new LongHuDetailExample();
		example.setOrderByClause("trade_date desc");
		LongHuDetailExample.Criteria criteria = example.createCriteria();
		criteria.andSecDeptCodeEqualTo(Integer.valueOf(deptCode));
		lhDetailService.selectDetailExtByExample(example, page);
		List<LongHuDetailExt> detailList = page.getRecords();
		SecDept secDept = secDeptService.selectByCode(deptCode);
		ModelAndView mav = new ModelAndView("longHuDept");
		mav.addObject("detailList", detailList);
		mav.addObject("totalPages", page.getTotalPages());
		mav.addObject("curPage", pageStr);
		mav.addObject("secDept", secDept);
		return mav;
	}
	
	private List<String> getTypeDes(String types){
		if(StringUtil.isBlank(types)){
			return null;
		}
		String[] typeArr = types.split(SymbolConstant.COMMA);
		List<String> typeList = new ArrayList<String>();
		for (String type : typeArr) {
			LongHuType lhType = lhTypeService.selectByType(type);
			typeList.add(lhType.getLhDesc());
		}
		return typeList;
	}
	
}
