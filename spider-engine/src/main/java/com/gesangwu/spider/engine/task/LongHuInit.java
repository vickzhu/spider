package com.gesangwu.spider.engine.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.CompanyExample;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.LongHuTypeService;

@Component
public class LongHuInit {
	
	@Resource
	private CompanyService companyService;
	@Resource
	private LongHuTypeService longHuTypeService;

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
	
	private static final String r3 = "\"chgtype\"\\:\"([0-9]*)\",\"bizsunitcode\"\\:\"([0-9]*)\",\"bizsunitname\"\\:\"([^\"]*)\",\"amount\"\\:([^,]*),\"buyvol\"\\:([^,]*),\"buyamt\"\\:([^,]*),\"salevol\"\\:([^,]*),\"saleamt\"\\:([^,]*),\"typedesc\"\\:\"([^\"]*)\"";
	private static Pattern p3 = Pattern.compile(r3);
	
	private void getLongHu(String symbol, String date){
		String url = assembleLongHuUrl(symbol, date);
		String result = HttpTool.get(url);
		Matcher m1 = p2.matcher(result);
		if(m1.find()){
			String buyList = m1.group(1);
			Matcher m2 = p3.matcher(buyList);
			Map<String, LongHuType> typeMap = new HashMap<String, LongHuType>();
			List<LongHuDetail> drDetail = new ArrayList<LongHuDetail>();
			List<LongHuDetail> srDetail = new ArrayList<LongHuDetail>();
			Map<String, List<LongHuDetail>> buyMap = new HashMap<String, List<LongHuDetail>>();
			Map<String, List<LongHuDetail>> sellMap = new HashMap<String, List<LongHuDetail>>();
			while(m2.find()){
				String longHuType = m2.group(1);
				String secCode = m2.group(2);
				String secName = m2.group(3);
				String typeDesc = m2.group(9);
				LongHuDetail detail = new LongHuDetail();
				LongHuType lhType = getLongHuType(longHuType, typeDesc);
				typeMap.put(longHuType, lhType);
				List<LongHuDetail> detailList = buyMap.get(longHuType);
				if(CollectionUtils.isEmpty(detailList)){
					detailList = new ArrayList<LongHuDetail>();
				}
				detailList.add(detail);
				//TODO
				
				System.out.println(secCode + secName);
			}
			String sellList = m1.group(2);
			System.out.println(sellList);
		}
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
	
	public static void main(String[] args){
		LongHuInit init = new LongHuInit();
		String cookieUrl = "https://xueqiu.com/account/lostpasswd";
		HttpTool.get(cookieUrl);
//		getDates("sz002265");
		init.getLongHu("sz002265", "20160923");
	}
}
