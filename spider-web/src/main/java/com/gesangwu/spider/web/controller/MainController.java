package com.gesangwu.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.ActiveDeptOperation;
import com.gesangwu.spider.biz.dao.model.ActiveDeptOperationExample;
import com.gesangwu.spider.biz.dao.model.HolderNum;
import com.gesangwu.spider.biz.dao.model.HolderNumExample;
import com.gesangwu.spider.biz.dao.model.StockShareHolderExample;
import com.gesangwu.spider.biz.dao.model.ext.StockShareHolderExt;
import com.gesangwu.spider.biz.service.ActiveDeptOperationService;
import com.gesangwu.spider.biz.service.HolderNumService;
import com.gesangwu.spider.biz.service.StockShareHolderService;

@Controller
public class MainController {
	
	@Resource
	private StockShareHolderService sshService;
	@Resource
	private HolderNumService hnService;
	@Resource
	private ActiveDeptOperationService adoService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request){
		Page<StockShareHolderExt> sshPage = new Page<StockShareHolderExt>(1, 10);
		StockShareHolderExample example = new StockShareHolderExample();
		sshService.selectCliqueByPagination(example, sshPage);
		List<HolderNum> hnList = getHnList();
		ModelAndView mav = new ModelAndView("dashboard");
		mav.addObject("sshList", sshPage.getRecords());
		mav.addObject("hnList", hnList);
		mav.addObject("adoList", getAdoList(1));
		return mav;
	}
	
	private List<ActiveDeptOperation> getAdoList(int curPage){
		ActiveDeptOperationExample example = new ActiveDeptOperationExample();
		example.setOrderByClause("trade_date desc");
		Page<ActiveDeptOperation> page = new Page<ActiveDeptOperation>(curPage,20);
		adoService.selectByPagination(example, page);
		return page.getRecords();
	}
	
	private List<HolderNum> getHnList(){
		HolderNumExample example = new HolderNumExample();
		example.setOrderByClause("end_date desc");
		example.setOffset(0);
		example.setRows(10);
		HolderNumExample.Criteria criteria = example.createCriteria();
		criteria.andChgRateNotEqualTo(0d);
		return hnService.selectByExample(example);
	}
	
}
