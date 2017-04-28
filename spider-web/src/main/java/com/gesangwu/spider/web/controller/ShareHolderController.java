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
import com.gesangwu.spider.biz.dao.model.ShareHolder;
import com.gesangwu.spider.biz.dao.model.StockShareHolder;
import com.gesangwu.spider.biz.dao.model.StockShareHolderExample;
import com.gesangwu.spider.biz.dao.model.ext.StockShareHolderExt;
import com.gesangwu.spider.biz.service.HolderNumService;
import com.gesangwu.spider.biz.service.ShareHolderService;
import com.gesangwu.spider.biz.service.StockShareHolderService;

@Controller
@RequestMapping("/shareholder")
public class ShareHolderController {
	
	@Resource
	private ShareHolderService holderService;
	@Resource
	private HolderNumService hnService;
	@Resource
	private StockShareHolderService sshService;
	
	/**
	 * 帮派
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/clique", method = RequestMethod.GET)
	public ModelAndView clique(HttpServletRequest request){
		int curPage = 1;
		String pageStr = request.getParameter("curPage");
		if(StringUtil.isNotBlank(pageStr)){
			curPage = Integer.valueOf(pageStr);
		}
		Page<StockShareHolderExt> page = new Page<StockShareHolderExt>(curPage, 20);
		StockShareHolderExample example = new StockShareHolderExample();
		sshService.selectCliqueByPagination(example, page);
		ModelAndView mav = new ModelAndView("cliqueHolder");
		mav.addObject("page", page);
		return mav;
	}
	
	/**
	 * 牛散
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/retail", method = RequestMethod.GET)
	public ModelAndView retail(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("shareHolder");
		return mav;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request, long holderId){
		ShareHolder holder = holderService.selectByPrimaryKey(holderId);
		List<StockShareHolder> sshExtList = sshService.selectByShareHolder(holderId);
		ModelAndView mav = new ModelAndView("shareHolderDetail");
		mav.addObject("sshExtList", sshExtList);
		mav.addObject("holder", holder);
		return mav;
	}
	
	@RequestMapping(value = "/stock", method = RequestMethod.GET)
	public ModelAndView stockShareHolder(HttpServletRequest request, String symbol){
		StockShareHolderExample example = new StockShareHolderExample();
		StockShareHolderExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		ModelAndView mav = new ModelAndView("holderDetail");
		return mav;
	}
	
}
