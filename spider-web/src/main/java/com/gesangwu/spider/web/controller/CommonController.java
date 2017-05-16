package com.gesangwu.spider.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.AjaxResult;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.FiveRangeStatis;
import com.gesangwu.spider.biz.dao.model.FiveRangeStatisExample;
import com.gesangwu.spider.biz.dao.model.HolderNum;
import com.gesangwu.spider.biz.dao.model.HolderNumExample;
import com.gesangwu.spider.biz.dao.model.JdStatis;
import com.gesangwu.spider.biz.dao.model.JdStatisExample;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.dao.model.LargeVolStatis;
import com.gesangwu.spider.biz.dao.model.LargeVolStatisExample;
import com.gesangwu.spider.biz.dao.model.StockNameInitial;
import com.gesangwu.spider.biz.dao.model.StockNameInitialExample;
import com.gesangwu.spider.biz.dao.model.ext.StockShareHolderExt;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.FiveRangeStatisService;
import com.gesangwu.spider.biz.service.HolderNumService;
import com.gesangwu.spider.biz.service.JdStatisService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.service.LargeVolStatisService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.biz.service.StockNameInitialService;
import com.gesangwu.spider.biz.service.StockShareHolderService;

@Controller
public class CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Resource
	private LongHuService longHuService;
	@Resource
	private CompanyService companyService;
	@Resource
	private LargeVolStatisService lvsService;
	@Resource
	private FiveRangeStatisService frss;
	@Resource
	private StockShareHolderService sshService;
	@Resource
	private HolderNumService hnService;
	@Resource
	private KLineService kLineService;
	@Resource
	private JdStatisService jdStatisService;
	@Resource
	private StockNameInitialService initialService;
	
	private static final String r1 = "[0-9]{6}";
	private static final String r2 = "(sh|sz)[0-9]{6}";
	private static final String r3 = "[a-zA-Z]+";
	
	private static Pattern p1 = Pattern.compile(r1);
	private static Pattern p2 = Pattern.compile(r2);
	private static Pattern p3 = Pattern.compile(r3);

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(HttpServletRequest request){
		String keyword = request.getParameter("keyword");
		String symbol = null;
		if(p1.matcher(keyword).matches()){
			symbol = StockUtil.code2Symbol(keyword);
		} else if (p2.matcher(keyword).matches()){
			symbol = keyword;
		} else {
			Company company = companyService.selectByName(keyword);
			if(company != null){
				symbol = company.getSymbol();
			}
		}
		return "forward:/stock?symbol="+symbol;
	}
	
	@ResponseBody
	@RequestMapping(value = "/suggestion", method = RequestMethod.GET)
	public AjaxResult suggestion(HttpServletRequest request){
		String keyword = request.getParameter("keyword");
		if(StringUtil.isBlank(keyword)){
			return null; 
		}
		keyword = keyword.trim().toLowerCase();
		StockNameInitialExample example = new StockNameInitialExample();
		example.setOffset(0);
		example.setRows(20);
		StockNameInitialExample.Criteria criteria = example.createCriteria();
		if(p3.matcher(keyword).matches()){
			criteria.andInitialGroupLike(keyword + "%");
		}
		List<StockNameInitial> initialList = initialService.selectByExample(example);
		Map<String, String> resultMap = new HashMap<String, String>();
		for (StockNameInitial initial : initialList) {
			resultMap.put(initial.getSymbol(), initial.getStockName());
		}
		return new AjaxResult(Boolean.TRUE, null, resultMap);
	}
	
	@RequestMapping(value = "/stock", method = RequestMethod.GET)
	public ModelAndView stock(HttpServletRequest request){
		String symbol = request.getParameter("symbol");
		
		Company company = companyService.selectBySymbol(symbol);
		List<LargeVolStatis> lvsList = getLvsList(symbol);
		List<FiveRangeStatis> frsList = getFrsList(symbol);
		List<StockShareHolderExt> sshExtList = getSshList(symbol);
		List<HolderNum> hnList = getHnList(symbol);
		List<JdStatis> jdList = getJdList(symbol);
		
		ModelAndView mav = new ModelAndView("stockDetail");
		mav.addObject("company", company);
		mav.addObject("lvsList", lvsList);
		mav.addObject("frsList", frsList);
		mav.addObject("sshExtList", sshExtList);
		mav.addObject("hnList", hnList);
		mav.addObject("jdList", jdList);
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/kline", method = RequestMethod.GET)
	public ModelAndView showKLine(HttpServletRequest request,String symbol){
		String klJson = getKLineList(symbol);
		ModelAndView mav = new ModelAndView("kline");
		mav.addObject("klData", klJson);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping(value = "/kline2", method = RequestMethod.GET)
	public ModelAndView showKLine2(HttpServletRequest request,String symbol){
		String klJson = getKLineList(symbol);
		ModelAndView mav = new ModelAndView("kline2");
		mav.addObject("klData", klJson);
		return mav;
	}
	
	private List<LargeVolStatis> getLvsList(String symbol){
		Page<LargeVolStatis> lvsPage = new Page<LargeVolStatis>(1, 10);
		LargeVolStatisExample lvsExample = new LargeVolStatisExample();
		lvsExample.setOrderByClause("trade_date desc");
		LargeVolStatisExample.Criteria criteria = lvsExample.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		lvsService.selectByPagination(lvsExample, lvsPage);
		return lvsPage.getRecords();
	}
	
	private List<FiveRangeStatis> getFrsList(String symbol){
		Page<FiveRangeStatis> page = new Page<FiveRangeStatis>(1, 10);
		FiveRangeStatisExample example = new FiveRangeStatisExample();
		example.setOrderByClause("trade_date desc");
		FiveRangeStatisExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		frss.selectByPagination(example, page);
		return page.getRecords();
	}
	
	private List<StockShareHolderExt> getSshList(String symbol){
		return sshService.selectLatestBySymbol(symbol);
	}
	
	private List<HolderNum> getHnList(String symbol){
		HolderNumExample example = new HolderNumExample();
		example.setOrderByClause(" end_date desc ");
		example.setOffset(0);
		example.setRows(10);
		HolderNumExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		return hnService.selectByExample(example);
	}
	
	private List<JdStatis> getJdList(String symbol){
		JdStatisExample example = new JdStatisExample();
		example.setOrderByClause(" trade_date desc ");
		example.setOffset(0);
		example.setRows(10);
		JdStatisExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		return jdStatisService.selectByExample(example);
	}
	
	private String getKLineList(String symbol){
		KLineExample example = new KLineExample();
		example.setOrderByClause("trade_date");
		KLineExample.Criteria criteria = example.createCriteria();
		
		criteria.andSymbolEqualTo(symbol);
		List<KLine> klList = kLineService.selectByExample(example);
		List<Object[]> objList = new ArrayList<Object[]>();
		//日期,昨收,开盘价,高,低，收,量，额
		for (KLine kLine : klList) {
			Object[] objArr = new Object[9];
			objArr[0] = kLine.getTradeDate();
			objArr[1] = kLine.getOpen();
			objArr[2] = kLine.getHigh();
			objArr[3] = kLine.getLow();
			objArr[4] = kLine.getClose();
			objArr[5] = kLine.getVolume();
			objArr[6] = kLine.getPercent();
			objArr[7] = kLine.getChangeAmount();
			objArr[8] = kLine.getYesterdayClose();
			objList.add(objArr);
		}
		ObjectMapper om = new ObjectMapper();
		try {
			return om.writeValueAsString(objList);
		} catch (IOException e) {
			logger.error("Parse kline to json failed!",e);
			return StringUtil.EMPTY;
		}
	}
	
	
}
