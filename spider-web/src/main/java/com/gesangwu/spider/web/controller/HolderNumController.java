package com.gesangwu.spider.web.controller;

import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.HolderNum;
import com.gesangwu.spider.biz.dao.model.HolderNumExample;
import com.gesangwu.spider.biz.service.HolderNumService;

@Controller
@RequestMapping("/holder-num")
public class HolderNumController {
	
	@Resource
	private HolderNumService hnService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView mav(HttpServletRequest request){
		int curPage = 1;
		String pageStr = request.getParameter("curPage");
		if(StringUtil.isNotBlank(pageStr)){
			curPage = Integer.valueOf(pageStr);
		}
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		
		String[][] dateRange = getDateRange();
		if(StringUtil.isBlank(start) || StringUtil.isBlank(end)){
			start = dateRange[0][0];
			end = dateRange[0][1];
		}
		
		HolderNumExample example = new HolderNumExample();
		HolderNumExample.Criteria criteria = example.createCriteria();
		criteria.andEndDateGreaterThanOrEqualTo(start);
		criteria.andEndDateLessThanOrEqualTo(end);
		example.setOrderByClause("chg_rate asc");
		
		Page<HolderNum> page = new Page<HolderNum>(curPage, 10);
		hnService.selectByPagination(example, page);
		
		ModelAndView mav = new ModelAndView("holderNumList");
		mav.addObject("dateRange", dateRange);
		mav.addObject("page", page);
		mav.addObject("start", start);
		mav.addObject("end", end);
		return mav;
	}
	
	private String[][] getDateRange(){
		String[][] dateArr = new String[3][2];
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_MONTH);
		int curMaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		if(day > 15){
			int curMonth = c.get(Calendar.MONTH)+1;
			String curMonthStr = curMonth < 10 ? "0"+curMonth : String.valueOf(curMonth);
			dateArr[0][0] = c.get(Calendar.YEAR)+"-"+curMonthStr+"-"+16;
			dateArr[0][1] = c.get(Calendar.YEAR)+"-"+curMonthStr+"-"+curMaxDay;
			
			dateArr[1][0] = c.get(Calendar.YEAR)+"-"+curMonthStr+"-"+"01";
			dateArr[1][1] = c.get(Calendar.YEAR)+"-"+curMonthStr+"-"+15;
			
			c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1);
			int preMonth = c.get(Calendar.MONTH)+1;
			String preMonthStr = preMonth < 10 ? "0"+preMonth : String.valueOf(preMonth);
			int preMaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			dateArr[2][0] = c.get(Calendar.YEAR)+"-"+preMonthStr+"-"+16;
			dateArr[2][1] = c.get(Calendar.YEAR)+"-"+preMonthStr+"-"+preMaxDay;
		} else {
			int curMonth = c.get(Calendar.MONTH)+1;
			String curMonthStr = curMonth < 10 ? "0"+curMonth : String.valueOf(curMonth);
			
			dateArr[0][0] = c.get(Calendar.YEAR)+"-"+curMonthStr+"-"+"01";
			dateArr[0][1] = c.get(Calendar.YEAR)+"-"+curMonthStr+"-"+15;
			
			c.set(Calendar.MONTH, c.get(Calendar.MONTH)-1);
			int preMonth = c.get(Calendar.MONTH)+1;
			String preMonthStr = preMonth < 10 ? "0"+preMonth : String.valueOf(preMonth);
			int preMaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			
			dateArr[1][0] = c.get(Calendar.YEAR)+"-"+preMonthStr+"-"+16;
			dateArr[1][1] = c.get(Calendar.YEAR)+"-"+preMonthStr+"-"+preMaxDay;
			
			dateArr[2][0] = c.get(Calendar.YEAR)+"-"+preMonthStr+"-"+"01";
			dateArr[2][1] = c.get(Calendar.YEAR)+"-"+preMonthStr+"-"+15;
			
		}
		
		return dateArr;
	}
}
