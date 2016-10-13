package com.gesangwu.spider.engine.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.common.DecimalUtil;
import com.gesangwu.spider.biz.common.LongHuDateType;
import com.gesangwu.spider.biz.common.SecDeptType;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.CompanyExample;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.dao.model.SecDept;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.biz.service.LongHuTypeService;
import com.gesangwu.spider.biz.service.SecDeptService;

@Component
public class LongHuInit {
	
	@Resource
	private CompanyService companyService;
	@Resource
	private LongHuTypeService longHuTypeService;
	@Resource
	private SecDeptService secDeptService;
	@Resource
	private LongHuDetailService lhDetailService;
	@Resource
	private LongHuService lhService;

	public void execute(){
		int cpp = 10;
		CompanyExample example = new CompanyExample();
		CompanyExample.Criteria criteria = example.createCriteria();
//		criteria.andIdGreaterThan(3312l);
		 
		int count = companyService.countByExample(example);
		int totalPages = (count + cpp -1)/cpp;
		for(int cur = 1; cur <= totalPages; cur++){
			String cookieUrl = "https://xueqiu.com/account/lostpasswd";
			HttpTool.get(cookieUrl);//这个链接只是为了获得cookie信息，因为后面的请求需要用到cookie
			Page<Company> page = new Page<Company>(cur);
			companyService.selectByPagination(example, page);
			List<Company> companyList = page.getRecords();
			for (Company company : companyList) {
				String[] datesArr = getDates(company.getSymbol());
				if(datesArr == null){
					continue;
				}
				for (int i = datesArr.length - 1; i >= 0; i--) {
					getLongHu(company.getSymbol(), datesArr[i]);
				}
			}
		}
	}
	
	private static final String longHuUrl = "https://xueqiu.com/stock/f10/bizunittrdinfo.json";
	
	private static final String r2 = "\"chgtype\"\\:\"([0-9]*)\",\"bizsunitcode\"\\:\"([0-9]*)\",\"bizsunitname\"\\:\"([^\"]*)\",\"amount\"\\:[^,]*,\"buyvol\"\\:[^,]*,\"buyamt\"\\:([^,]*),\"salevol\"\\:[^,]*,\"saleamt\"\\:([^,]*),\"typedesc\"\\:\"([^\"]*)\"";
	private static Pattern p2 = Pattern.compile(r2);
	
	/**
	 * 龙虎榜日期类型有三种，可能三种都有上榜，同时每种日期类型可能有多种上榜理由，而我们保存龙虎详情时，针对每种日期类型只需保存一条详情
	 * @param symbol
	 * @param date
	 */
	public void getLongHu(String symbol, String date){
		date = date.replaceAll(SymbolConstant.H_LINE, StringUtil.EMPTY);
		String url = assembleLongHuUrl(symbol, date);
		String result = HttpTool.get(url);
		Matcher m2 = p2.matcher(result);
		Map<String, LongHuType> typeMap = new HashMap<String, LongHuType>();//记录所有龙虎榜类型
		Map<String, LongHuDetail> yrMap = new HashMap<String, LongHuDetail>();
		Map<String, LongHuDetail> erMap = new HashMap<String, LongHuDetail>();
		Map<String, LongHuDetail> srMap = new HashMap<String, LongHuDetail>();
		String yrType = null;
		String erType = null;
		String srType = null;
		Date now = new Date();
		while(m2.find()){
			String longHuType = m2.group(1);
			String secCode = m2.group(2);
			String secName = m2.group(3);
			String buy = m2.group(4);
			String sell = m2.group(5);
			String typeDesc = m2.group(6);
			LongHuType lhType = getLongHuType(longHuType, typeDesc);
			typeMap.put(longHuType, lhType);
			LongHuDetail detail = new LongHuDetail();
			if(lhType.getDateType() == LongHuDateType.YIRI.getCode()){//一日
				if(yrType == null){
					yrType = lhType.getLhType();
				} else if (!yrType.equals(lhType.getLhType())){
					continue;
				}
				detail.setDateType(LongHuDateType.YIRI.getCode());
				yrMap.put(secCode, detail);
			} else if(lhType.getDateType() == LongHuDateType.ERRI.getCode()){//二日
				if(erType == null){
					erType = lhType.getLhType();
				} else if (!erType.equals(lhType.getLhType())){
					continue;
				}
				detail.setDateType(LongHuDateType.ERRI.getCode());
				erMap.put(secCode, detail);
			} else if(lhType.getDateType() == LongHuDateType.SANRI.getCode()){//三日
				if(srType == null){
					srType = lhType.getLhType();
				} else if(!srType.equals(lhType.getLhType())){
					continue;
				}
				detail.setDateType(LongHuDateType.SANRI.getCode());
				srMap.put(secCode, detail);
			}
			
			detail.setGmtCreate(now);
			detail.setSecDeptCode(secCode);
			detail.setSymbol(symbol);
			detail.setTradeDate(formatDate(date));
			
			BigDecimal buyAmt = DecimalUtil.toUnitWan(buy);
			BigDecimal sellAmt = DecimalUtil.toUnitWan(sell);
			BigDecimal netBuy = buyAmt.subtract(sellAmt);
			
			detail.setBuyAmt(buyAmt);
			detail.setSellAmt(sellAmt);
			detail.setNetBuy(netBuy);
			
			checkSecDept(secCode, secName);
			
		}
		LongHu longHu = initLongHu(symbol, result, typeMap);
		longHu.setTradeDate(formatDate(date));
		String yrAmt = buildTotalAmt(yrMap);
		if(StringUtil.isNotBlank(yrAmt)){
			longHu.setYrAmt(yrAmt);
		}
		String erAmt = buildTotalAmt(erMap);
		if(StringUtil.isNotBlank(erAmt)){
			longHu.setErAmt(erAmt);
		}
		String srAmt = buildTotalAmt(srMap);
		if(StringUtil.isNotBlank(srAmt)){
			longHu.setSrAmt(srAmt);
		}
		lhService.insert(longHu);
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private String buildTotalAmt(Map<String, LongHuDetail> detailMap){
		if(detailMap.size() == 0){
			return StringUtil.EMPTY;
		}
		List<LongHuDetail> detailList = longHuMap2List(detailMap);
		lhDetailService.batchInsert(detailList);
		BigDecimal buyTotal = BigDecimal.ZERO;
		BigDecimal sellTotal = BigDecimal.ZERO;
		for (LongHuDetail longHuDetail : detailList) {
			buyTotal = buyTotal.add(longHuDetail.getBuyAmt());
			sellTotal = sellTotal.add(longHuDetail.getSellAmt());
		}
		BigDecimal netBuy = buyTotal.subtract(sellTotal);
		StringBuilder sb = new StringBuilder();
		sb.append(buyTotal.toString());
		sb.append(SymbolConstant.COMMA);
		sb.append(sellTotal.toString());
		sb.append(SymbolConstant.COMMA);
		sb.append(netBuy.toString());
		return sb.toString();
	}
	
	
	private static final String r3 = "\"tclose\"\\:([^,]*),\"thigh\"\\:([^,]*),\"tlow\"\\:([^,]*),\"vol\"\\:([^,]*),\"amount\"\\:([^,]*),\"change\"\\:([^,]*),\"pchg\"\\:([^,]*),\"amplitude\"\\:([^,]*),\"deals\"\\:([^,]*),\"avgprice\"\\:([^,]*),\"avgvol\"\\:([^,]*),\"avgtramt\"\\:([^,]*),\"turnrate\"\\:([^,]*),\"totmktcap\"\\:([^,]*),\"negotiablemv\"\\:([^\\}]*)";
	private static Pattern p3 = Pattern.compile(r3);
	
	private static final String r4 = ",\"name\"\\:\"([^,]*)\"";
	private static Pattern p4 = Pattern.compile(r4);
	
	/**
	 * 初始化龙虎数据
	 * @param symbol
	 * @param stockName
	 * @param result
	 * @return
	 */
	private LongHu initLongHu(String symbol, String result, Map<String, LongHuType> typeMap){
		Matcher m4 = p4.matcher(result);
		String stockName = null;
		if(m4.find()){
			stockName = m4.group(1);
		}
		LongHu longHu = new LongHu();
		longHu.setSymbol(symbol);
		longHu.setStockName(stockName);
		longHu.setGmtCreate(new Date());
		Matcher m3 = p3.matcher(result);
		if(m3.find()){
			String close = m3.group(1);//收盘价
			String change = m3.group(6);//涨跌额
			String chgPercent = m3.group(7);//涨跌幅
			String amplitude = m3.group(8);//振幅
			String turnrate = m3.group(13);//换手率
			String totmktcap = m3.group(14);//市值
			String negotiablemv = m3.group(15);//流通市值
			longHu.setChg(Double.valueOf(change));
			longHu.setPrice(Double.valueOf(close));
			longHu.setChgPercent(DecimalUtil.parse(chgPercent).doubleValue());
			longHu.setAmplitude(DecimalUtil.parse(amplitude).doubleValue());
			longHu.setTurnover(DecimalUtil.parse(turnrate).doubleValue());
			longHu.setTotMktVal(DecimalUtil.toUnitWan(totmktcap).doubleValue());
			longHu.setNegMktVal(DecimalUtil.toUnitWan(negotiablemv).doubleValue());
		}
		StringBuilder yrSB = new StringBuilder();
		StringBuilder erSB = new StringBuilder();
		StringBuilder srSB = new StringBuilder();
		for(Map.Entry<String, LongHuType> entry : typeMap.entrySet()){
			LongHuType longHuType = entry.getValue();
			if(longHuType.getDateType() == LongHuDateType.YIRI.getCode()){
				yrSB.append(SymbolConstant.COMMA);
				yrSB.append(longHuType.getLhType());
			}else if(longHuType.getDateType() == LongHuDateType.SANRI.getCode()){
				srSB.append(SymbolConstant.COMMA);
				srSB.append(longHuType.getLhType());
			}else if(longHuType.getDateType() == LongHuDateType.ERRI.getCode()){
				erSB.append(SymbolConstant.COMMA);
				erSB.append(longHuType.getLhType());
			}
		}
		if(yrSB.length() > 0){
			longHu.setYrType(yrSB.substring(1));
		}
		if(erSB.length() > 0){
			longHu.setErType(erSB.substring(1));
		}
		if(srSB.length() > 0){
			longHu.setSrType(srSB.substring(1));
		}
		return longHu;
	}
	
	
	private List<LongHuDetail> longHuMap2List(Map<String, LongHuDetail> drMap){
		List<LongHuDetail> detailList = new ArrayList<LongHuDetail>();
		for (Map.Entry<String, LongHuDetail> entry : drMap.entrySet()) {
			detailList.add(entry.getValue());
		}
		return detailList;
	}
	
	private LongHuType getLongHuType(String longHuType, String typeDesc){
		LongHuType lhType = longHuTypeService.selectByType(longHuType);
		if(lhType == null){
			lhType = new LongHuType();
			lhType.setLhType(longHuType);
			lhType.setDateType(judgeDateType(typeDesc));
			lhType.setLhDesc(typeDesc);
			longHuTypeService.insert(lhType);
		}
		return lhType;
	}
	
	/**
	 * 判断龙虎榜日期类型
	 * @param typeDesc
	 * @return
	 */
	private int judgeDateType(String typeDesc){
		if(typeDesc.contains("三个")){
			return LongHuDateType.SANRI.getCode();
		}else if(typeDesc.contains("2个")){
			return LongHuDateType.ERRI.getCode();
		} else {
			return LongHuDateType.YIRI.getCode();
		}
	}
	
	private void checkSecDept(String secCode, String secName){
		SecDept secDept = secDeptService.selectByCode(secCode);
		if(secDept == null){
			secDept = new SecDept();
			secDept.setCode(secCode);
			secDept.setDeptType(SecDeptType.NONE.getCode());
			secDept.setDeptAddr(secName);
			secDept.setGmtCreate(new Date());
			secDeptService.insert(secDept);
		}
	}
	
	private static String assembleLongHuUrl(String symbol, String date){
		StringBuilder sb = new StringBuilder();
		sb.append(longHuUrl);
		sb.append("?symbol=");
		sb.append(symbol);
		sb.append("&date=");
		sb.append(date);
		return sb.toString();
	}
	
	private static final String date_url = "https://xueqiu.com/stock/f10/bizunittrdinfo.json?symbol=";
	private static final String r1 = "\\{\"list\"\\:\\[\"(.*)\"\\]\\}";
	private static Pattern p1 = Pattern.compile(r1);
	/**
	 * 获取得到日期
	 * @return
	 */
	private static String[] getDates(String symbol){
		String url =  date_url + symbol;
		String result = HttpTool.get(url);
		Matcher m = p1.matcher(result);
		if(m.matches()){
			String dates = m.group(1);
			return StringUtil.split(dates,"\",\"");
		}
		return null;
	}
	
	/**
	 * 将雪球的日期类型"yyyyMMdd"格式为"yyyy-MM-dd"
	 * @param date
	 * @return
	 */
	private static String formatDate(String date){
		StringBuilder sb = new StringBuilder();
		sb.append(date.substring(0, 4));
		sb.append(SymbolConstant.H_LINE);
		sb.append(date.substring(4, 6));
		sb.append(SymbolConstant.H_LINE);
		sb.append(date.substring(6));
		return sb.toString();
	}
}
