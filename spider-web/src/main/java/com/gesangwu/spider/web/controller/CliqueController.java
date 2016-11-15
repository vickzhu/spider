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
import com.gesangwu.spider.biz.common.HolderType;
import com.gesangwu.spider.biz.dao.model.Clique;
import com.gesangwu.spider.biz.dao.model.CliqueStock;
import com.gesangwu.spider.biz.dao.model.ShareHolder;
import com.gesangwu.spider.biz.dao.model.ShareHolderExample;
import com.gesangwu.spider.biz.dao.model.ext.CliqueDeptExt;
import com.gesangwu.spider.biz.service.CliqueDeptService;
import com.gesangwu.spider.biz.service.CliqueService;
import com.gesangwu.spider.biz.service.CliqueStockService;
import com.gesangwu.spider.biz.service.ShareHolderService;

@Controller
@RequestMapping("/clique")
public class CliqueController {
	
	@Resource
	private CliqueService cliqueService;
	@Resource
	private CliqueDeptService cdService;
	@Resource
	private CliqueStockService csService;
	@Resource
	private ShareHolderService holderService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request){
		List<Clique> cliqueList = cliqueService.selectByExample(null);
		ModelAndView mav = new ModelAndView("cliqueList");
		mav.addObject("cliqueList", cliqueList);
		return mav;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request, long cliqueId){
		String pageStr = request.getParameter("curPage");
		int curPage = 1;
		if(StringUtil.isNotBlank(pageStr)){
			curPage = Integer.valueOf(pageStr);
		}
		Clique clique = cliqueService.selectByPrimaryKey(cliqueId);
		Page<CliqueDeptExt> cdPage = new Page<CliqueDeptExt>(curPage, 20);
		cdService.selectExtByCliqueId(cliqueId, cdPage);
		Page<CliqueStock> csPage = new Page<CliqueStock>(1, 20);
		csService.selectByCliqueId(cliqueId, csPage);
		ModelAndView mav = new ModelAndView("cliqueDetail");
		mav.addObject("clique", clique);
		mav.addObject("cdPage", cdPage);
		mav.addObject("csPage", csPage);
		return mav;
	}
	
	/**
	 * 帮派股东
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/shareholder", method = RequestMethod.GET)
	public ModelAndView shareholder(HttpServletRequest request){
		String cliqueId = request.getParameter("cliqueId");
		ShareHolderExample example = new ShareHolderExample();
		ShareHolderExample.Criteria criteria = example.createCriteria();
		criteria.andHolderTypeEqualTo(HolderType.PERSON.getType());
		if(StringUtil.isNotBlank(cliqueId)){
			criteria.andCliqueIdEqualTo(Long.valueOf(cliqueId));
		} else {
			criteria.andCliqueIdIsNotNull();
		}
		List<ShareHolder> holderList = holderService.selectByExample(example);
		ModelAndView mav = new ModelAndView("shareHolder");
		mav.addObject("holderList", holderList);
		return mav;
	}
	
	/**
	 * 将营业部加入帮派
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sec-dept/add", method = RequestMethod.GET)
	public ModelAndView addSecDept(HttpServletRequest request, long secDeptId){
		ModelAndView mav = new ModelAndView();
		return mav;
	}
}
