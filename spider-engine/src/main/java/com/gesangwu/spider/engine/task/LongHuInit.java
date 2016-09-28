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
		criteria.andIdGreaterThan(1481l);
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
	
	public void getLongHu(String symbol, String date){
		String url = assembleLongHuUrl(symbol, date);
		String result = HttpTool.get(url);
		Matcher m2 = p2.matcher(result);
		Map<String, LongHuType> typeMap = new HashMap<String, LongHuType>();
		Map<String, LongHuDetail> drMap = new HashMap<String, LongHuDetail>();
		Map<String, LongHuDetail> srMap = new HashMap<String, LongHuDetail>();
		String srType = null;
		String drType = null;
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
			if(lhType.getSrType() == 1){//三日
				if(srType == null){
					srType = lhType.getLhType();
				} else if(!srType.equals(lhType.getLhType())){
					continue;
				}
				detail.setIsSr(1);
				srMap.put(secCode, detail);
			}else{//当日
				if(drType == null){
					drType = lhType.getLhType();
				} else if (!drType.equals(lhType.getLhType())){
					continue;
				}
				detail.setIsSr(0);
				drMap.put(secCode, detail);
			}
			
			detail.setGmtCreate(now);
			detail.setSecDeptAddr(secName);
			detail.setSecDeptCode(Integer.valueOf(secCode));
			detail.setSymbol(symbol);
			detail.setTradeDate(date);
			
			BigDecimal buyAmt = formatAmt(buy);
			BigDecimal sellAmt = formatAmt(sell);
			BigDecimal netBuy = buyAmt.subtract(sellAmt);
			
			detail.setBuyAmt(buyAmt);
			detail.setSellAmt(sellAmt);
			detail.setNetBuy(netBuy);
			
			checkSecDept(secCode, secName);
			
		}
		LongHu longHu = initLongHu(symbol, result, typeMap);
		longHu.setTradeDate(date);
		if(drMap.size() > 0){
			List<LongHuDetail> drList = longHuMap2List(drMap);
			lhDetailService.batchInsert(drList);
			BigDecimal buyTotal = BigDecimal.ZERO;
			BigDecimal sellTotal = BigDecimal.ZERO;
			for (LongHuDetail longHuDetail : drList) {
				buyTotal = buyTotal.add(longHuDetail.getBuyAmt());
				sellTotal = sellTotal.add(longHuDetail.getSellAmt());
			}
			double netBuy = buyTotal.subtract(sellTotal).doubleValue();
			longHu.setDrBuyAmt(buyTotal.doubleValue());
			longHu.setDrSellAmt(sellTotal.doubleValue());
			longHu.setDrNetBuy(netBuy);
		}
		if(srMap.size() > 0){
			List<LongHuDetail> srList = longHuMap2List(srMap);
			lhDetailService.batchInsert(srList);
			BigDecimal buyTotal = BigDecimal.ZERO;
			BigDecimal sellTotal = BigDecimal.ZERO;
			for (LongHuDetail longHuDetail : srList) {
				buyTotal = buyTotal.add(longHuDetail.getBuyAmt());
				sellTotal = sellTotal.add(longHuDetail.getSellAmt());
			}
			double netBuy = buyTotal.subtract(sellTotal).doubleValue();
			longHu.setSrBuyAmt(buyTotal.doubleValue());
			longHu.setSrSellAmt(sellTotal.doubleValue());
			longHu.setSrNetBuy(netBuy);
		}
		lhService.insert(longHu);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
			longHu.setChgPercent(Double.valueOf(chgPercent));
			longHu.setAmplitude(Double.valueOf(amplitude));
			longHu.setTurnover(Double.valueOf(turnrate));
			longHu.setTotMktVal(Double.valueOf(totmktcap));
			longHu.setNegMktVal(Double.valueOf(negotiablemv));
		}
		StringBuilder drSB = new StringBuilder();
		StringBuilder srSB = new StringBuilder();
		for(Map.Entry<String, LongHuType> entry : typeMap.entrySet()){
			LongHuType longHuType = entry.getValue();
			if(longHuType.getSrType()==1){//三日类型
				srSB.append(SymbolConstant.COMMA);
				srSB.append(longHuType.getLhType());
			} else {//当日类型
				drSB.append(SymbolConstant.COMMA);
				drSB.append(longHuType.getLhType());
			}
		}
		if(drSB.length() > 0){
			longHu.setDrLhType(drSB.substring(1));
		}
		if(srSB.length() > 0){
			longHu.setSrLhType(srSB.substring(1));
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
			lhType.setSrType(typeDesc.contains("三") ? 1 : 0);
			lhType.setLhDesc(typeDesc);
			longHuTypeService.insert(lhType);
		}
		return lhType;
	}
	
	private void checkSecDept(String secCode, String secName){
		SecDept secDept = secDeptService.selectByCode(secCode);
		if(secDept == null){
			secDept = new SecDept();
			secDept.setCode(secCode);
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
	
	private BigDecimal formatAmt(String amt){
		if("null".equals(amt)){
			return BigDecimal.ZERO;
		}
		BigDecimal buy = new BigDecimal(amt);
		return buy.divide(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_HALF_UP);
	}

}
