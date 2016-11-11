package com.gesangwu.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.HolderType;
import com.gesangwu.spider.biz.dao.model.ShareHolder;
import com.gesangwu.spider.biz.dao.model.ShareHolderExample;
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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request){
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
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request,long holderId){
		List<StockShareHolderExt> sshExtList = sshService.selectExtByShareHolder(holderId);
		ModelAndView mav = new ModelAndView("shareHolderDetail");
		mav.addObject("sshExtList", sshExtList);
		return mav;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(HttpServletRequest request){
		String keyword = request.getParameter("keyword");
		List<StockShareHolderExt> sshExtList = sshService.selectByPersonalName(keyword);
		ModelAndView mav = new ModelAndView();
		mav.addObject("sshExtList", sshExtList);
		return mav;
	}
	
}
