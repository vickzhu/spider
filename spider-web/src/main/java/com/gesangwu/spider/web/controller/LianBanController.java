package com.gesangwu.spider.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
import com.gesangwu.spider.biz.dao.model.LianBanPlate;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.service.LianBanPlateService;
import com.gesangwu.spider.biz.service.LianBanService;

@Controller
@RequestMapping("/zt")
public class LianBanController {
	
	protected static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	private LianBanService lbService;
	@Resource
	private KLineService klService;
	@Resource
	private CompanyService companyService;
	@Resource
	private LianBanPlateService lbpService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView zt(HttpServletRequest request){
		String tradeDate = request.getParameter("tradeDate");
		if(StringUtil.isBlank(tradeDate)){			
			tradeDate = klService.selectLatestDate();
		}
		LianBanExample example = new LianBanExample();
		example.setOrderByClause("days desc");
		LianBanExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		criteria.andStatusEqualTo(LianBanStatus.ZT.getCode());
		List<LianBan> lbList = lbService.selectByExample(example);
		Map<Long, String> lbpMap = lbpService.selectByTradeDateForMap(tradeDate);
		ModelAndView mav = new ModelAndView("zhangtingban");
		mav.addObject("lbList", lbList);
		mav.addObject("tradeDate", tradeDate);
		mav.addObject("lbpMap", lbpMap);
		return mav;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request){
		String tradeDate = request.getParameter("tradeDate");
		List<LianBanPlate> lbpList = lbpService.selectByTradeDate(tradeDate);
		ModelAndView mav = new ModelAndView("zhangtingAdd");
		mav.addObject("tradeDate", tradeDate);
		mav.addObject("statusArray", LianBanStatus.values());
		mav.addObject("lbpList", lbpList);
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
			String plateStr = request.getParameter("plate");
			String plateCustom = request.getParameter("plateCustom");
			Long plate = getPlateId(lb.getTradeDate(), plateStr, plateCustom);
			
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
				lb.setPlate(plate);
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
		String tradeDate = request.getParameter("tradeDate");
		List<LianBanPlate> lbpList = lbpService.selectByTradeDate(tradeDate);
		ModelAndView mav = new ModelAndView("zhangtingEdit");
		mav.addObject("lbpList", lbpList);
		mav.addObject("lb", lb);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public AjaxResult doUpdate(HttpServletRequest request){
		long id = Long.valueOf(request.getParameter("id"));
		int days = Integer.valueOf(request.getParameter("days"));
		LianBan lb = lbService.selectByPrimaryKey(id);
		String plateStr = request.getParameter("plate");
		String plateCustom = request.getParameter("plateCustom");
		Long plate = getPlateId(lb.getTradeDate(), plateStr, plateCustom);
		String shape = request.getParameter("shape");
		String reason = request.getParameter("reason");
		if(plate != null && StringUtil.isBlank(reason)){
			reason = lbpService.selectByPrimaryKey(plate).getPlate();
		}
		lb.setDays(days);
		lb.setPlate(plate);
		lb.setReason(reason);
		lb.setGmtUpdate(new Date());
		lb.setShape(shape);
		lbService.updateByPrimaryKey(lb);
		return new AjaxResult(true, null);
	}
	
	@RequestMapping(value = "/statis", method = RequestMethod.GET)
	public ModelAndView statis(HttpServletRequest request, String startDate, String endDate){
		Calendar c = Calendar.getInstance();
		if(StringUtil.isBlank(startDate) || StringUtil.isBlank(endDate)){
			Date now = new Date();
			c.setTime(now);
			c.set(Calendar.DATE, c.get(Calendar.DATE) - 15);
			startDate = sdf.format(c.getTime());
			endDate = sdf.format(now);
		}
		LianBanExample example = new LianBanExample();
		example.setOrderByClause("trade_date");
		LianBanExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateGreaterThanOrEqualTo(startDate);
		criteria.andTradeDateLessThanOrEqualTo(endDate);
		List<LianBan> lbList = lbService.selectByExample(example);
		List<String> dateList = new ArrayList<String>();
		TreeMap<String, List<LianBan>> lbMap = new TreeMap<String, List<LianBan>>();
		for (LianBan lb : lbList) {
			String tradeDate = lb.getTradeDate();
			List<LianBan> lblt = lbMap.get(tradeDate);
			if(lblt == null){
				dateList.add(tradeDate);
				lblt = new ArrayList<LianBan>();
				lbMap.put(tradeDate, lblt);
			}
		}
		ModelAndView mav = new ModelAndView("zhangtingStatis");
		mav.addObject("startDate", startDate);
		mav.addObject("endDate", endDate);
		mav.addObject("lbMap", lbMap);
		mav.addObject("dateList", dateList);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/statis", method = RequestMethod.POST)
	public AjaxResult doStatis(HttpServletRequest request, String startDate, String endDate){
		LianBanExample example = new LianBanExample();
		example.setOrderByClause("trade_date");
		LianBanExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateGreaterThanOrEqualTo(startDate);
		criteria.andTradeDateLessThanOrEqualTo(endDate);
		List<LianBan> lbList = lbService.selectByExample(example);
		List<String> dateList = new ArrayList<String>();
		TreeMap<String, List<LianBan>> lbMap = new TreeMap<String, List<LianBan>>();
		for (LianBan lb : lbList) {
			String tradeDate = lb.getTradeDate();
			List<LianBan> lblt = lbMap.get(tradeDate);
			if(lblt == null){
				lblt = new ArrayList<LianBan>();
				lbMap.put(tradeDate, lblt);
				dateList.add(tradeDate);
			}
			lblt.add(lb);
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("lbMap", lbMap);
		resultMap.put("dateList", dateList);
		return new AjaxResult(true, null, resultMap);
	}
	
	private Long getPlateId(String tradeDate, String plateStr, String plateCustom){
		Long plate = null;
		if(StringUtil.isNotBlank(plateCustom)){
			plate = savePlate(tradeDate, plateCustom);
		} else {			
			if(StringUtil.isNotBlank(plateStr)){
				plate = Long.valueOf(plateStr);
			}
		}
		return plate;
	}
	
	private Long savePlate(String tradeDate, String plateCustom){
		LianBanPlate lbp = new LianBanPlate();
		lbp.setTradeDate(null);
		lbp.setPlate(plateCustom);
		lbp.setGmtCreate(new Date());
		lbpService.insert(lbp);
		return lbp.getId();
	}
	
}
