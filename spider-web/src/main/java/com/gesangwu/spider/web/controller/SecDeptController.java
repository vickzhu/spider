package com.gesangwu.spider.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.AjaxResult;
import com.gesangwu.spider.biz.dao.model.Clique;
import com.gesangwu.spider.biz.dao.model.CliqueDept;
import com.gesangwu.spider.biz.dao.model.SecDept;
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
		List<Clique> cliqueList = new ArrayList<Clique>();
		for (CliqueDept cliqueDept : cliqueDeptList) {
			Clique clique = cliqueService.selectByPrimaryKey(cliqueDept.getCliqueId());
			cliqueList.add(clique);
		}
		List<Clique> allCliqueList = cliqueService.selectByExample(null);
		ModelAndView mav = new ModelAndView("secDeptEdit");
		mav.addObject("secDept", dept);
		mav.addObject("cliqueList", cliqueList);
		mav.addObject("allCliqueList", allCliqueList);
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
				dept.setGmtUpdate(new Date());
				deptService.updateByPrimaryKey(dept);
			}
			String cliqueId = request.getParameter("cliqueId");
			if(StringUtil.isNotBlank(cliqueId)){
				CliqueDept cliqueDept = new CliqueDept();
				cliqueDept.setCliqueId(Long.valueOf(cliqueId));
				cliqueDept.setDeptType(1);
				cliqueDept.setGmtCreate(new Date());
				cliqueDept.setSecDeptCode(dept.getCode());
				cliqueDept.setSecDeptName(dept.getDeptAddr());
				cliqueDeptService.insert(cliqueDept);
			}
		} catch (Exception e) {
			return new AjaxResult(false, e.getMessage());
		}
		return new AjaxResult(true, null);
				
	}
	
}
