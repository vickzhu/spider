package com.gesangwu.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.HolderNum;
import com.gesangwu.spider.biz.dao.model.HolderNumExample;
import com.gesangwu.spider.biz.dao.model.StockShareHolderExample;
import com.gesangwu.spider.biz.dao.model.ext.StockShareHolderExt;
import com.gesangwu.spider.biz.service.HolderNumService;
import com.gesangwu.spider.biz.service.StockShareHolderService;

@Controller
public class MainController {
	
	@Resource
	private StockShareHolderService sshService;
	@Resource
	private HolderNumService hnService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request){
		Page<StockShareHolderExt> sshPage = new Page<StockShareHolderExt>(1, 10);
		StockShareHolderExample example = new StockShareHolderExample();
		sshService.selectCliqueByPagination(example, sshPage);
		List<HolderNum> hnList = getHnList();
		ModelAndView mav = new ModelAndView("dashboard");
		mav.addObject("sshList", sshPage.getRecords());
		mav.addObject("hnList", hnList);
		return mav;
	}
	
	private List<HolderNum> getHnList(){
		HolderNumExample example = new HolderNumExample();
		example.setOrderByClause("end_date desc");
		example.setOffset(0);
		example.setRows(10);
		return hnService.selectByExample(example);
	}
	
}
