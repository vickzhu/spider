package com.gesangwu.spider.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.MyFollowDept;
import com.gesangwu.spider.biz.dao.model.MyFollowDeptExample;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.MyFollowDeptService;

@Controller
@RequestMapping("/follow/dept")
public class MyFollowDeptController {
	
	private static final long USER_ID = 1000;
	
	@Resource
	private MyFollowDeptService mfdService;
	@Resource
	private LongHuDetailService lhdService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request){
		MyFollowDeptExample example = new MyFollowDeptExample();
		MyFollowDeptExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(USER_ID);
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value = "/longhu", method = RequestMethod.GET)
	public ModelAndView stock(HttpServletRequest request){
		MyFollowDeptExample example = new MyFollowDeptExample();
		MyFollowDeptExample.Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(USER_ID);
		List<MyFollowDept> mfdList = mfdService.selectByExample(example);
		List<String> deptCodeList = new ArrayList<String>();
		for (MyFollowDept myFollowDept : mfdList) {
			deptCodeList.add(myFollowDept.getDeptCode());
		}
		LongHuDetailExample lhdExample = new LongHuDetailExample();
		lhdExample.setOrderByClause("trade_date desc");
		LongHuDetailExample.Criteria lhdCriteria = lhdExample.createCriteria();
		lhdCriteria.andSecDeptCodeIn(deptCodeList);
		
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(HttpServletRequest request){
		
	}
	
}
