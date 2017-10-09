package com.gesangwu.spider.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.AjaxResult;
import com.gesangwu.spider.biz.common.SecDeptType;
import com.gesangwu.spider.biz.dao.cache.CliqueCache;
import com.gesangwu.spider.biz.dao.model.Clique;
import com.gesangwu.spider.biz.dao.model.CliqueDept;
import com.gesangwu.spider.biz.dao.model.SecDept;
import com.gesangwu.spider.biz.dao.model.SecDeptExample;
import com.gesangwu.spider.biz.service.CliqueDeptService;
import com.gesangwu.spider.biz.service.CliqueService;
import com.gesangwu.spider.biz.service.SecDeptService;

@Controller
@RequestMapping("/sec-dept")
public class SecDeptController {
	
	@Resource
	private SecDeptService deptService;
	@Resource
	private CliqueDeptService cliqueDeptService;
	@Resource
	private CliqueService cliqueService;

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView addSecDept(HttpServletRequest request, long deptId){
		SecDept dept = deptService.selectByPrimaryKey(deptId);
		List<CliqueDept> cliqueDeptList = cliqueDeptService.selectByDeptCode(dept.getCode());
		Map<Long,Clique> allCliqueMap = CliqueCache.getCliqueMap();
		ModelAndView mav = new ModelAndView("secDeptEdit");
		mav.addObject("secDept", dept);
		mav.addObject("cliqueDeptList", cliqueDeptList);
		mav.addObject("allCliqueMap", allCliqueMap);
		mav.addObject("deptTypeArr", SecDeptType.values());
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public AjaxResult doAddSecDept(HttpServletRequest request, long deptId){
		try {
			SecDept dept = deptService.selectByPrimaryKey(deptId);
			String deptType = request.getParameter("deptType");
			if(StringUtil.isNotBlank(deptType)){
				dept.setDeptType(Integer.valueOf(deptType));
			} else {
				dept.setDeptType(null);
			}
			dept.setGmtUpdate(new Date());
			deptService.updateByPrimaryKey(dept);
			String cliqueId = request.getParameter("cliqueId");
			if(StringUtil.isNotBlank(cliqueId)){
				String cliqueType = request.getParameter("cliqueType");
				CliqueDept cliqueDept = new CliqueDept();
				cliqueDept.setCliqueId(Long.valueOf(cliqueId));
				cliqueDept.setDeptType(Integer.valueOf(cliqueType));
				cliqueDept.setGmtCreate(new Date());
				cliqueDept.setSecDeptCode(dept.getCode());
				cliqueDeptService.insert(cliqueDept);
			}
		} catch (Exception e) {
			return new AjaxResult(false, e.getMessage());
		}
		return new AjaxResult(true, null);
	}
	
	@ResponseBody
	@RequestMapping(value = "/exit-clique", method = RequestMethod.POST)
	public AjaxResult exitClique(HttpServletRequest request,long cliqueId, String deptCode){
		try{			
			cliqueDeptService.delete(cliqueId, deptCode);
		}catch(Exception e){
			return new AjaxResult(false, e.getMessage());
		}
		return new AjaxResult(true, null);
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit-clique", method = RequestMethod.POST)
	public AjaxResult editClique(HttpServletRequest request,long cdId, int deptType){
		try{
			CliqueDept cd = cliqueDeptService.selectByPrimaryKey(cdId);
			cd.setDeptType(deptType);
			cd.setGmtUpdate(new Date());
			cliqueDeptService.updateByPrimaryKey(cd);
		}catch(Exception e){
			return new AjaxResult(false, e.getMessage());
		}
		return new AjaxResult(true, null);
	}
	
	/**
	 * 活跃营业部
	 * @return
	 */
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public ModelAndView activeDept(HttpServletRequest request){
		SecDeptExample example = new SecDeptExample();
		SecDeptExample.Criteria criteria = example.createCriteria();
		criteria.andActiveDeptEqualTo(1);
		List<SecDept> deptList = deptService.selectByExample(example);
		ModelAndView mav = new ModelAndView("activeDept");
		mav.addObject("deptList", deptList);
		mav.addObject("deptTypeArr", SecDeptType.values());
		return mav;
	}
}
