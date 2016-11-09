package com.gesangwu.spider.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.common.LongHuDateType;
import com.gesangwu.spider.biz.common.LongHuDetailPair;
import com.gesangwu.spider.biz.dao.model.CliqueDept;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExt;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.dao.model.SecDept;
import com.gesangwu.spider.biz.service.CliqueDeptService;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.biz.service.LongHuTypeService;
import com.gesangwu.spider.biz.service.SecDeptService;
import com.gesangwu.spider.engine.task.LongHuTaskSina;

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
	@Resource
	private CliqueDeptService cliqueDeptService;
	@Resource
	private LongHuTaskSina sinaTask;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, String tradeDate){
		ModelAndView mav = new ModelAndView("longHu");
		List<LongHu> lhList = lhService.selectByTradeDate(tradeDate);
		mav.addObject("lhList", lhList);
		if(CollectionUtils.isNotEmpty(lhList)){
			mav.addObject("tradeDate", lhList.get(0).getTradeDate());
		} else {
			mav.addObject("tradeDate", tradeDate);
		}
		return mav;
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request){
		String symbol = request.getParameter("symbol");
		String tradeDate = request.getParameter("tradeDate");
		if(StringUtil.isBlank(symbol)){
			logger.error("Miss symbol!");
			return null;
		}
		LongHu longHu = selectLongHu(symbol, tradeDate);
		if(longHu == null){
			logger.error("Can't find LongHu with given symbol ["+symbol+"] and tradeDate ["+tradeDate+"]!");
			return null;
		}
		List<String> yrTypeList = getTypeDes(longHu.getYrType());
		List<String> erTypeList = getTypeDes(longHu.getErType());
		List<String> srTypeList = getTypeDes(longHu.getSrType());
		List<String> dateList = lhService.selectTradeDate(symbol);
		ModelAndView mav = new ModelAndView("longHuDetail");
		if(CollectionUtils.isNotEmpty(yrTypeList)){
			LongHuDetailPair p = lhDetailService.selectDetailPairs(symbol, longHu.getTradeDate(), LongHuDateType.YIRI.getCode());
			mav.addObject("yrBuyList", p.getBuyList());
			mav.addObject("yrSellList", p.getSellList());
		}
		if(CollectionUtils.isNotEmpty(erTypeList)){
			LongHuDetailPair p = lhDetailService.selectDetailPairs(symbol, longHu.getTradeDate(), LongHuDateType.ERRI.getCode());
			mav.addObject("erBuyList", p.getBuyList());
			mav.addObject("erSellList", p.getSellList());
		}
		if(CollectionUtils.isNotEmpty(srTypeList)){
			LongHuDetailPair p = lhDetailService.selectDetailPairs(symbol, longHu.getTradeDate(), LongHuDateType.SANRI.getCode());
			mav.addObject("srBuyList", p.getBuyList());
			mav.addObject("srSellList", p.getSellList());
		}
		mav.addObject("longHu", longHu);
		mav.addObject("yrTypeList", yrTypeList);
		mav.addObject("erTypeList", erTypeList);
		mav.addObject("srTypeList", srTypeList);
		mav.addObject("dateList", dateList);
		return mav;
	}
	
	private LongHu selectLongHu(String symbol, String tradeDate){
		if(StringUtil.isBlank(tradeDate)){
			return lhService.selectLatestBySymbol(symbol);
		} else {
			return lhService.selectBySymbolAndTradeDate(symbol, tradeDate);
		}
	}
	
	@RequestMapping(value = "/dept", method = RequestMethod.GET)
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
		criteria.andSecDeptCodeEqualTo(deptCode);
		lhDetailService.selectDetailExtByExample(example, page);
		SecDept secDept = secDeptService.selectByCode(deptCode);
		List<CliqueDept> cliqueDeptList = cliqueDeptService.selectByDeptCode(deptCode);
		ModelAndView mav = new ModelAndView("longHuDept");
		mav.addObject("page", page);
		mav.addObject("secDept", secDept);
		mav.addObject("cliqueDeptList", cliqueDeptList);
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
	
	
	@RequestMapping(value = "/fetch/sina", method = RequestMethod.GET)
	public void fetchSina(HttpServletRequest request){
		sinaTask.execute();
	}
	
}
