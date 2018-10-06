package com.gesangwu.spider.web.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.AjaxResult;
import com.gesangwu.spider.biz.common.LianBanStatus;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.dao.model.LianBan;
import com.gesangwu.spider.biz.dao.model.LianBanExample;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.service.LianBanService;

@Controller
@RequestMapping("/zt")
public class LianBanController {
	
	@Resource
	private LianBanService lbService;
	@Resource
	private KLineService klService;
	@Resource
	private CompanyService companyService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView zt(HttpServletRequest request){
		String tradeDate = request.getParameter("tradeDate");
		if(StringUtil.isBlank(tradeDate)){			
			tradeDate = klService.selectLatestDate();
		}
		LianBanExample example = new LianBanExample();
		LianBanExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		criteria.andStatusEqualTo(LianBanStatus.ZT.getCode());
		List<LianBan> lbList = lbService.selectByExample(example);
		ModelAndView mav = new ModelAndView("zhangtingban");
		mav.addObject("lbList", lbList);
		mav.addObject("tradeDate", tradeDate);
		return mav;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request){
		String tradeDate = request.getParameter("tradeDate");
		ModelAndView mav = new ModelAndView("zhangtingAdd");
		mav.addObject("tradeDate", tradeDate);
		mav.addObject("statusArray", LianBanStatus.values());
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public AjaxResult doAdd(HttpServletRequest request){
		String stockName = request.getParameter("stockName");
		String tradeDate = request.getParameter("tradeDate");
		Company company = companyService.selectByName(stockName);
		String symbol = company.getSymbol();
		String statusStr = request.getParameter("status");
		int status = Integer.valueOf(statusStr);
		Date now = new Date();
		LianBan lb = new LianBan();
		if(status == LianBanStatus.TP.getCode()){
			lb.setGmtCreate(now);
			lb.setTradeDate(tradeDate);
			lb.setStockName(stockName);
			lb.setSymbol(symbol);
			lb.setStatus(LianBanStatus.TP.getCode());
		} else {
			int days = 1;
			String daysStr = request.getParameter("days");
			if(StringUtil.isNotBlank(daysStr)){
				days = Integer.valueOf(daysStr);
			}
			String shape = request.getParameter("shape");
			String reason = request.getParameter("reason");
			KLineExample klExample = new KLineExample();
			KLineExample.Criteria criteria = klExample.createCriteria();
			criteria.andSymbolEqualTo(symbol);
			criteria.andTradeDateEqualTo(tradeDate);
			List<KLine> klList = klService.selectByExample(klExample);
			if(CollectionUtils.isNotEmpty(klList)){
				KLine kl = klList.get(0);
				lb.setDays(days);
				lb.setGmtCreate(now);
				lb.setPercent(kl.getPercent());
				lb.setShape(shape);
				lb.setStatus(status);
				lb.setSymbol(symbol);
				lb.setStockName(stockName);
				lb.setTradeDate(tradeDate);
				lb.setPlate(null);
				lb.setReason(reason);
			} else {
				return new AjaxResult(false, "没有对应日期的K线！");
			}
		}
		int count = lbService.insert(lb);
		return new AjaxResult(count == 1, null);
	}
	
	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public AjaxResult del(HttpServletRequest request){
		Long id = Long.valueOf(request.getParameter("id"));
		int count = lbService.deleteByPrimaryKey(id);
		return new AjaxResult(count == 1, null);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request){
		long id = Long.valueOf(request.getParameter("id"));
		LianBan lb = lbService.selectByPrimaryKey(id);
		ModelAndView mav = new ModelAndView("zhangtingEdit");
		mav.addObject("lb", lb);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public AjaxResult doUpdate(HttpServletRequest request){
		long id = Long.valueOf(request.getParameter("id"));
		int days = Integer.valueOf(request.getParameter("days"));
		String plateStr = request.getParameter("plate");
		Long plate = null;
		if(StringUtil.isNotBlank(plateStr)){
			plate = Long.valueOf(request.getParameter("plate"));
		}
		String shape = request.getParameter("shape");
		String reason = request.getParameter("reason");
		LianBan lb = lbService.selectByPrimaryKey(id);
		lb.setDays(days);
		lb.setPlate(plate);
		lb.setReason(reason);
		lb.setGmtUpdate(new Date());
		lb.setShape(shape);
		lbService.updateByPrimaryKey(lb);
		return new AjaxResult(true, null);
	}
}
