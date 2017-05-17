package com.gesangwu.spider.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.common.LongHuDateType;
import com.gesangwu.spider.biz.common.LongHuDetailPair;
import com.gesangwu.spider.biz.dao.model.CliqueDept;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExt;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.dao.model.SecDept;
import com.gesangwu.spider.biz.dao.model.ext.LongHuDetailDept;
import com.gesangwu.spider.biz.service.CliqueDeptService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.biz.service.LongHuTypeService;
import com.gesangwu.spider.biz.service.SecDeptService;
import com.gesangwu.spider.engine.task.LongHuTaskSina;
import com.gesangwu.spider.web.common.DeptAmount;

@Controller
@RequestMapping(value="/longhu")
public class LongHuController {
	
	private static final Logger logger = LoggerFactory.getLogger(LongHuController.class);
	
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhDetailService;
	@Resource
	private LongHuTypeService lhTypeService;
	@Resource
	private SecDeptService secDeptService;
	@Resource
	private CliqueDeptService cliqueDeptService;
	@Resource
	private KLineService kLineService;
	@Resource
	private LongHuTaskSina sinaTask;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, String tradeDate, Company company){
		ModelAndView mav = new ModelAndView("longHu");
		List<LongHu> lhList = lhService.selectByTradeDate(tradeDate);
		mav.addObject("lhList", lhList);
		if(CollectionUtils.isNotEmpty(lhList)){
			mav.addObject("tradeDate", lhList.get(0).getTradeDate());
		} else {
			mav.addObject("tradeDate", tradeDate);
		}
		return mav;
	}
	
	@RequestMapping(value="/detail", method = RequestMethod.GET)
	public ModelAndView detail(HttpServletRequest request){
		String symbol = request.getParameter("symbol");
		String tradeDate = request.getParameter("tradeDate");
		if(StringUtil.isBlank(symbol)){
			logger.error("Miss symbol!");
			return null;
		}
		LongHu longHu = selectLongHu(symbol, tradeDate);
		if(longHu == null){
			logger.error("Can't find LongHu with given symbol ["+symbol+"] and tradeDate ["+tradeDate+"]!");
			return null;
		}
		List<String> yrTypeList = getTypeDes(longHu.getYrType());
		List<String> erTypeList = getTypeDes(longHu.getErType());
		List<String> srTypeList = getTypeDes(longHu.getSrType());
		List<String> dateList = lhService.selectTradeDate(symbol);
		ModelAndView mav = new ModelAndView("longHuDetail");
		if(CollectionUtils.isNotEmpty(yrTypeList)){
			LongHuDetailPair p = lhDetailService.selectDetailPairs(symbol, longHu.getTradeDate(), LongHuDateType.YIRI.getCode());
			mav.addObject("yrBuyList", p.getBuyList());
			mav.addObject("yrSellList", p.getSellList());
		}
		if(CollectionUtils.isNotEmpty(erTypeList)){
			LongHuDetailPair p = lhDetailService.selectDetailPairs(symbol, longHu.getTradeDate(), LongHuDateType.ERRI.getCode());
			mav.addObject("erBuyList", p.getBuyList());
			mav.addObject("erSellList", p.getSellList());
		}
		if(CollectionUtils.isNotEmpty(srTypeList)){
			LongHuDetailPair p = lhDetailService.selectDetailPairs(symbol, longHu.getTradeDate(), LongHuDateType.SANRI.getCode());
			mav.addObject("srBuyList", p.getBuyList());
			mav.addObject("srSellList", p.getSellList());
		}
		mav.addObject("longHu", longHu);
		mav.addObject("yrTypeList", yrTypeList);
		mav.addObject("erTypeList", erTypeList);
		mav.addObject("srTypeList", srTypeList);
		mav.addObject("dateList", dateList);
		return mav;
	}
	
	private LongHu selectLongHu(String symbol, String tradeDate){
		if(StringUtil.isBlank(tradeDate)){
			return lhService.selectLatestBySymbol(symbol);
		} else {
			return lhService.selectBySymbolAndTradeDate(symbol, tradeDate);
		}
	}
	
	@RequestMapping(value = "/dept", method = RequestMethod.GET)
	public ModelAndView bySec(HttpServletRequest request){
		String deptCode = request.getParameter("deptCode");
		int curPage = 1;
		String pageStr = request.getParameter("curPage");
		if(StringUtil.isNotBlank(pageStr)){
			curPage = Integer.valueOf(pageStr);
		}
		Page<LongHuDetailExt> page = new Page<LongHuDetailExt>(curPage, 20);
		LongHuDetailExample example = new LongHuDetailExample();
		example.setOrderByClause("trade_date desc");
		LongHuDetailExample.Criteria criteria = example.createCriteria();
		criteria.andSecDeptCodeEqualTo(deptCode);
		lhDetailService.selectDetailExtByExample(example, page);
		SecDept secDept = secDeptService.selectByCode(deptCode);
		List<CliqueDept> cliqueDeptList = cliqueDeptService.selectByDeptCode(deptCode);
		ModelAndView mav = new ModelAndView("longHuDept");
		mav.addObject("page", page);
		mav.addObject("secDept", secDept);
		mav.addObject("cliqueDeptList", cliqueDeptList);
		return mav;
	}
	
	private List<String> getTypeDes(String types){
		if(StringUtil.isBlank(types)){
			return null;
		}
		String[] typeArr = types.split(SymbolConstant.COMMA);
		List<String> typeList = new ArrayList<String>();
		for (String type : typeArr) {
			LongHuType lhType = lhTypeService.selectByType(type);
			typeList.add(lhType.getLhDesc());
		}
		return typeList;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/fetch/sina", method = RequestMethod.GET)
	public void fetchSina(HttpServletRequest request){
		sinaTask.execute();
	}
	
	@ResponseBody
	@RequestMapping(value="/refresh", method = RequestMethod.POST)
	public void joinClique(HttpServletRequest request){
		String longHuIdStr = request.getParameter("id");
		if(StringUtil.isBlank(longHuIdStr)){
			return;
		}
		long longhuId = Long.valueOf(longHuIdStr);
		LongHu longhu = lhService.selectByPrimaryKey(longhuId);
		lhDetailService.clearClique(longhu.getId());
		lhService.analyzeClique(longhu);
	}
	
	/**
	 * 分析营业部的情况
	 * @param request
	 */
	public void analyzeDept(HttpServletRequest request){
		String symbol = request.getParameter("symbol");
		String beginDate = request.getParameter("beginDate");
		String endDate = request.getParameter("endDate");
		List<KLine> kLineList = selKLineList(symbol, beginDate, endDate);
		List<LongHuDetailDept> detailList = selLongHuDetailList(symbol, beginDate, endDate);
		Map<String, DeptAmount> daMap = new HashMap<String, DeptAmount>();
		for (LongHuDetailDept lhdd : detailList) {
			DeptAmount da = daMap.get(lhdd.getSecDeptCode());
			if(da == null){
				da = new DeptAmount(lhdd.getSecDeptCode(), lhdd.getDeptAddr());
			}
			da.addLongHu(lhdd);
			daMap.put(lhdd.getTradeDate(), da);
		}
		for(int i = 0; i< kLineList.size(); i++){
			KLine kl = kLineList.get(i);
			String tradeDate = kl.getTradeDate();
			for(DeptAmount da : daMap.values()){
				double maxRemain = calcAmount(da.getRemainAmount(), kl.getPercent());
				String sAmounts = da.getDateSell().get(tradeDate);
				//存在卖出
				//有底仓
				if(StringUtil.isNotBlank(sAmounts) && da.getRemainAmount() > 0){
					double reduce = 0;
					String[] smArr = sAmounts.split(SymbolConstant.COMMA);
					for (String sm : smArr) {
						String[] smPair = sm.split(SymbolConstant.U_LINE);
						int dateType = Integer.valueOf(smPair[0]);
						double amount = Double.valueOf(smPair[1]);
						if(dateType == 1){
							reduce = amount;
						} else {
							double diff = Math.abs(da.getRemainAmount() - amount);
							double scale = diff / da.getRemainAmount();
							if(scale <= 2){//20%范围内的金额则认为全出完了
								reduce = da.getRemainAmount();
							} else {//实际减仓需要计算
								reduce = amount;
							}
						}
					}
				}
				
				String bAmounts = da.getDateBuy().get(tradeDate);
				if(StringUtil.isNotBlank(bAmounts)){//存在当天的买入龙虎榜
					double add = 0;
					String[] bmArr = bAmounts.split(SymbolConstant.COMMA);
					for (String bm : bmArr) {
						String[] bmPair = bm.split(SymbolConstant.U_LINE);
						double amount = Double.valueOf(bmPair[1]);
						if(amount > add) {
							add = amount;
						}
					}
				}
				
			}
		}
	}
	
	private double calcAmount(double remainAmount, double changeRate){
		BigDecimal bd = new BigDecimal(remainAmount/100);
		bd = bd.multiply(new BigDecimal(changeRate)).setScale(2, BigDecimal.ROUND_HALF_UP);
		double result = remainAmount + bd.doubleValue(); 
		return result > 0 ? result : 0;
	}
	
	private List<KLine> selKLineList(String symbol, String beginDate, String endDate){
		KLineExample example = new KLineExample();
		example.setOrderByClause("trade_date");
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateGreaterThanOrEqualTo(beginDate);
		criteria.andTradeDateLessThanOrEqualTo(endDate);
		return kLineService.selectByExample(example);
	}
	
	private List<LongHuDetailDept> selLongHuDetailList(String symbol, String beginDate, String endDate){
		
		LongHuDetailExample example = new LongHuDetailExample();
		example.setOrderByClause("trade_date, date_type");
		LongHuDetailExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateGreaterThanOrEqualTo(beginDate);
		criteria.andTradeDateLessThanOrEqualTo(endDate);
		
		return lhDetailService.selectDetailDeptByExample(example);
	}
	
}
