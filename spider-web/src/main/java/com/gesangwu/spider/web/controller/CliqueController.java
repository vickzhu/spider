package com.gesangwu.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.Clique;
import com.gesangwu.spider.biz.dao.model.CliqueDept;
import com.gesangwu.spider.biz.dao.model.CliqueStock;
import com.gesangwu.spider.biz.service.CliqueDeptService;
import com.gesangwu.spider.biz.service.CliqueService;
import com.gesangwu.spider.biz.service.CliqueStockService;

@Controller
@RequestMapping("/clique")
public class CliqueController {
	
	@Resource
	private CliqueService cliqueService;
	@Resource
	private CliqueDeptService cdService;
	@Resource
	private CliqueStockService csService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request){
		List<Clique> cliqueList = cliqueService.selectByExample(null);
		ModelAndView mav = new ModelAndView("cliqueList");
		mav.addObject("cliqueList", cliqueList);
		return mav;
	}
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request,long cliqueId){
		Clique clique = cliqueService.selectByPrimaryKey(cliqueId);
		Page<CliqueDept> cdPage = new Page<CliqueDept>(1, 20);
		cdService.selectByCliqueId(cliqueId, cdPage);
		Page<CliqueStock> csPage = new Page<CliqueStock>(1, 20);
		csService.selectByCliqueId(cliqueId, csPage);
		ModelAndView mav = new ModelAndView("cliqueDetail");
		mav.addObject("clique", clique);
		mav.addObject("cdPage", cdPage);
		mav.addObject("csPage", csPage);
		return mav;
	}
}
