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

import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.CompanyExample;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.dao.model.SecDept;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.LongHuDetailService;
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
	private LongHuDetailService lfDetailService;

	public void execute(){
		int cpp = 10;
		int count = companyService.countByExample(null);
		int totalPages = (count + cpp -1)/cpp;
		for(int cur = 1; cur <= totalPages; cur++){
		String cookieUrl = "https://xueqiu.com/account/lostpasswd";
		HttpTool.get(cookieUrl);//这个链接只是为了获得cookie信息，因为后面的请求需要用到cookie
		Page<Company> page = new Page<Company>(cur);
			companyService.selectByPagination(new CompanyExample(), page);
			List<Company> companyList = page.getRecords();
			for (Company company : companyList) {
				String[] datesArr = getDates(company.getSymbol());
				if(datesArr == null){
					continue;
				}
				for (int i = datesArr.length-1; i > 0; i--) {
					getLongHu(company.getSymbol(), datesArr[i]);
				}
			}
		}
	}
	
	private static final String longHuUrl = "https://xueqiu.com/stock/f10/bizunittrdinfo.json";
	private static final String r2 = "tqQtBizunittrdinfoBuyList\"\\:\\[(.*)\\],\"tqQtBizunittrdinfoSaleList\"\\:\\[(.*)\\]";
	private static Pattern p2 = Pattern.compile(r2);
	
	private static final String r3 = "\"chgtype\"\\:\"([0-9]*)\",\"bizsunitcode\"\\:\"([0-9]*)\",\"bizsunitname\"\\:\"([^\"]*)\",\"amount\"\\:[^,]*,\"buyvol\"\\:[^,]*,\"buyamt\"\\:([^,]*),\"salevol\"\\:[^,]*,\"saleamt\"\\:([^,]*),\"typedesc\"\\:\"([^\"]*)\"";
	private static Pattern p3 = Pattern.compile(r3);
	
	public void getLongHu(String symbol, String date){
		String url = assembleLongHuUrl(symbol, date);
		String result = HttpTool.get(url);
		Matcher m2 = p3.matcher(result);
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
			
			detail.setBuyAmt(buyAmt.equals(BigDecimal.ZERO)?null:buyAmt);
			detail.setSellAmt(sellAmt.equals(BigDecimal.ZERO)?null:sellAmt);
			detail.setNetBuy(netBuy);
			
			checkSecDept(secCode, secName);
			
		}
		if(drMap.size()>0){
			List<LongHuDetail> drList = longHuMap2List(drMap);
			lfDetailService.batchInsert(drList);
		}
		if(srMap.size()>0){
			List<LongHuDetail> srList = longHuMap2List(srMap);
			lfDetailService.batchInsert(srList);
		}
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
	
	public static void main(String[] args){
		LongHuInit init = new LongHuInit();
		String cookieUrl = "https://xueqiu.com/account/lostpasswd";
		HttpTool.get(cookieUrl);
//		getDates("sz002265");
		init.getLongHu("sz002265", "20160923");
//		BigDecimal buy = init.formatAmt("3.3151128153E8");
//		BigDecimal sell = init.formatAmt("3.4151128153E8");
//		BigDecimal netBuy = buy.subtract(sell);
//		System.out.println(netBuy.doubleValue());
//		BigDecimal bd = new BigDecimal("0");
//		System.out.println(bd.equals(BigDecimal.ZERO));
	}
}
