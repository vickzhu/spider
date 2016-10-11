package com.gesangwu.spider.engine.task;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.biz.service.LongHuTypeService;

@Component
public class XinLangLongHuTask {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	private LongHuTypeService typeService;
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhdService;
	
	public void execute(){
		String url="http://vip.stock.finance.sina.com.cn/q/go.php/vInvestConsult/kind/lhb/index.phtml";
		Date now = new Date();
		String date = sdf.format(now);
		String r = "showDetail\\('([0-9]{2})','([0-9]{6})','" + date + "',this\\)";
		Pattern p = Pattern.compile(r);
		String result = HttpTool.get(url);
		Matcher m = p.matcher(result);
		Map<String,List<String>> longHuMap = new HashMap<String,List<String>>();
		while(m.find()){
			String type = m.group(1);
			String code = m.group(2);
			List<String> list = longHuMap.get(code);
			if(list == null){
				list = new ArrayList<String>();
				longHuMap.put(code, list);
			}
			list.add(type);
		}
		for (Map.Entry<String,List<String>> entry : longHuMap.entrySet()) {//单条龙虎详情
			String code = entry.getKey();
			List<String> typeList = entry.getValue();
			StringBuilder sb1 = new StringBuilder();
			StringBuilder sb2 = new StringBuilder();
			StringBuilder sb3 = new StringBuilder();
			buildLhType(sb1, sb2, sb3, typeList);
			LongHu longHu = new LongHu();
			Date d = new Date();
			longHu.setGmtCreate(d);
			
			if(sb1.length() > 0){//有一日行情
				String type = sb1.substring(sb1.lastIndexOf(SymbolConstant.COMMA)+1);
				longHu.setYrType(sb1.substring(1));
				lhService.insert(longHu);
				getLongHuDetail(type, code, date, 1, longHu.getId());
			}
			if(sb2.length() > 0){//有二日行情
				String type = sb2.substring(sb2.lastIndexOf(SymbolConstant.COMMA)+1);
				longHu.setErType(sb2.substring(1));
				lhService.insert(longHu);
				getLongHuDetail(type, code, date, 2, longHu.getId());
			}
			if(sb3.length() > 0){//有三日行情
				String type = sb3.substring(sb3.lastIndexOf(SymbolConstant.COMMA)+1);
				longHu.setSrType(sb3.substring(1));
				lhService.insert(longHu);
				getLongHuDetail(type, code, date, 3, longHu.getId());
			}
		}
	}
	
	private void buildLhType(StringBuilder sb1, StringBuilder sb2, StringBuilder sb3, List<String> typeList){
		for (String type : typeList) {
			LongHuType lhType = typeService.selectByType(type);
			if(lhType.getDateType() == 1){//一日
				sb1.append(SymbolConstant.COMMA);
				sb1.append(type);
			} else if(lhType.getDateType() == 3){//三日
				sb3.append(SymbolConstant.COMMA);
				sb3.append(type);
			} else if(lhType.getDateType() == 2){//二日
				sb2.append(SymbolConstant.COMMA);
				sb2.append(type);
			}
		}
	}
	
	private void saveLongHu(int dateType, String lhTypeArr, String code, String tradeDate){
		
	}
	
	private static final String r1 = "SYMBOL\\:\"([0-9]{6})\",type\\:\"([0-9]{2})\",comCode\\:\"([0-9]*)\",comName\\:\"(\"*)\",buyAmount\\:\"([0-9\\.]*)\",sellAmount\\:\"([0-9\\.]*)\",netAmount\\:([0-9\\.\\-]*)";
	private Pattern p1 = Pattern.compile(r1);
	
	private void getLongHuDetail(String lhType, String code, String date, int dateType, long longHuId){
		Date now = new Date();
		String url = buildDetailUrl(lhType, code, date);
		String result = HttpTool.get(url);
		Matcher m = p1.matcher(result);
		List<LongHuDetail> lhdList = new ArrayList<LongHuDetail>();
		BigDecimal buyTotal = BigDecimal.ZERO;
		BigDecimal sellTotal = BigDecimal.ZERO;
		Set<String> detpCodeSet = new HashSet<String>();
		while(m.find()){
			String deptCode = m.group(3);
//			String deptName = m.group(4);TODO 这里是否需要更新营业部的名称
			String buyAmtStr = m.group(5);
			String sellAmtStr = m.group(6);
			String netAmtStr = m.group(7);
			if(detpCodeSet.contains(deptCode)){//重复了
				continue;
			} else {
				detpCodeSet.add(deptCode);
			}
			BigDecimal buyAmt = new BigDecimal(buyAmtStr);
			BigDecimal sellAmt = new BigDecimal(sellAmtStr);
			BigDecimal netAmt = new BigDecimal(netAmtStr);
			buyTotal = buyTotal.add(buyAmt);
			sellTotal = sellTotal.add(sellAmt);
			LongHuDetail detail = new LongHuDetail();
			detail.setBuyAmt(buyAmt);
			detail.setSellAmt(sellAmt);
			detail.setNetBuy(netAmt);
			detail.setDateType(dateType);
			detail.setGmtCreate(now);
//			detail.setLongHuId(longHuId);
			detail.setSecDeptCode(deptCode);
			detail.setSymbol(StockUtil.code2Symbol(code));
			detail.setTradeDate(date);
			lhdList.add(detail);
		}
		buyTotal = buyTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
		sellTotal = sellTotal.setScale(2, BigDecimal.ROUND_HALF_UP);
		BigDecimal netTotal = buyTotal.subtract(sellTotal);
		LongHu longHu = new LongHu();
		
	}
	
	private List<LongHuDetail> longHuMap2List(Map<String, LongHuDetail> drMap){
		List<LongHuDetail> detailList = new ArrayList<LongHuDetail>();
		for (Map.Entry<String, LongHuDetail> entry : drMap.entrySet()) {
			detailList.add(entry.getValue());
		}
		return detailList;
	}
	
	private String buildDetailUrl(String type, String code, String date){
		StringBuilder sb = new StringBuilder();
		sb.append("http://vip.stock.finance.sina.com.cn/q/api/jsonp.php/var%20details=/InvestConsultService.getLHBComBSData?symbol=");
		sb.append(code);
		sb.append("&tradedate=");
		sb.append(date);
		sb.append("&type=");
		sb.append(type);
		return sb.toString();
	}
	
	public static void main(String[] args){
		String str = ",abc";
		System.out.println(str.substring(str.lastIndexOf(",")+1));
	}
}
