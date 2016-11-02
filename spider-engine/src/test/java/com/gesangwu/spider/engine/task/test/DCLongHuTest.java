package com.gesangwu.spider.engine.task.test;

import java.math.BigDecimal;
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

import org.junit.Before;
import org.junit.Test;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.spring.ContextHolder;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.DecimalUtil;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.dao.model.SecDept;
import com.gesangwu.spider.biz.service.LongHuTypeService;
import com.gesangwu.spider.biz.service.SecDeptService;
import com.gesangwu.spider.engine.test.BaseTest;

public class DCLongHuTest extends BaseTest {
	
	@Resource
	private LongHuTypeService lhtService;
	
	private static final String r1 = "<div class=\"div_remark\" style=\"display\\:table;\"><div style=\"display\\:table-cell;vertical-align\\:middle;\"><span>(.*)</span>";
	private static Pattern p1 = Pattern.compile(r1);
	private Map<String,LongHuType> typeMap = new HashMap<String,LongHuType>();
	private static final String SPLITER = SymbolConstant.U_LINE;
	
	@Before
	public void init(){
		List<LongHuType> lhtList = lhtService.selectByExample(null);
		for (LongHuType type : lhtList) {
			typeMap.put(type.getLhDesc(), type);
		}
	}
	
	@Test
	public void execute(){
		String symbol="000918";
		String tradeDate = "";
		long start = System.currentTimeMillis();
		String url = buildDetailUrl(symbol, tradeDate);
		String result = HttpTool.get(url);
		List<LongHuType> typeList = buildType(result);
		List<String> yrList = new ArrayList<String>();
		List<String> erList = new ArrayList<String>();
		List<String> srList = new ArrayList<String>();
		String[] strs = result.split("<div class=\"div_remark\" style=\"display\\:table;\"><div style=\"display\\:table-cell;vertical-align\\:middle;\"><span>");
		for(int i = 1;i < strs.length;i++){
			LongHu longHu = new LongHu();
			
			LongHuType lhType = typeList.get(i);
			if(lhType.getDateType() == 1){//一日
				yrList.add(lhType.getLhType());
				if(yrList.size()>1){
					continue;
				}
			} else if(lhType.getDateType() == 3){//三日
				srList.add(lhType.getLhType());
				if(srList.size()>1){
					continue;
				}
			} else if(lhType.getDateType() == 2){//二日
				erList.add(lhType.getLhType());
				if(erList.size()>1){
					continue;
				}
			}
			List<String> deptList = parseDept(strs[i]);
			List<String> amtList = parseAmt(strs[i]);
			Set<String> deptSet = new HashSet<String>();
			for(int j = 0; j< deptList.size(); j++){
				String deptCode = deptList.get(j);
				if(deptSet.contains(deptCode)){
					continue;
				}
				LongHuDetail detail = new LongHuDetail();
				String amtStr = amtList.get(i).replaceAll(SymbolConstant.COMMA, StringUtil.EMPTY);
				String[] amtPair = amtStr.split(SPLITER);
				BigDecimal buyAmt = DecimalUtil.parse(amtPair[0]);
				BigDecimal sellAmt = DecimalUtil.parse(amtPair[1]);
				BigDecimal netBuy = buyAmt.subtract(sellAmt);
				detail.setBuyAmt(buyAmt);
				detail.setSellAmt(sellAmt);
				detail.setNetBuy(netBuy);
				detail.setDateType(lhType.getDateType());
				detail.setSymbol(symbol);
				detail.setGmtCreate(new Date());
				detail.setSecDeptCode(deptCode);
				detail.setTradeDate(tradeDate);
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println("用时："+(end-start));
//		System.out.println(result);
	}
	
	private List<LongHuType> buildType(String result){
		List<LongHuType> typeList = new ArrayList<LongHuType>();
		Matcher m1 = p1.matcher(result);
		while(m1.find()){
			String typeDesc = m1.group(1);
			LongHuType lhType = typeMap.get(typeDesc);
			typeList.add(lhType);
			if(lhType == null){//防止有龙虎类型没有入库
				logger.error("Can't find Dept with desc:" + typeDesc);
				continue;
			}
		}
		return typeList;
	}
	
	/**
	 * 更新营业部信息
	 * @param deptCode
	 * @param deptName
	 */
	private void souDept(String deptCode, String deptName){
		SecDeptService deptService = ContextHolder.getContext().getBean(SecDeptService.class);
		SecDept dept = deptService.selectByCode(deptCode);
		if(dept == null){
			dept = new SecDept();
			dept.setCode(deptCode);
			dept.setDeptAddr(deptName);
			dept.setGmtCreate(new Date());
			deptService.insert(dept);
		} else {
			dept.setDeptAddr(deptName);
			dept.setGmtUpdate(new Date());
			deptService.updateByPrimaryKey(dept);
		}
	}
	
	private static final String r2 = "<a href=\"javascript\\:location.href='/Lhb/JgSearch/([0-9]*)'\">(.*)</a>";
	private static Pattern p2 = Pattern.compile(r2);
	
	private List<String> parseDept(String detail){
		Matcher m2 = p2.matcher(detail);
		List<String> deptCodeList = new ArrayList<String>();
		while(m2.find()){
			deptCodeList.add(m2.group(1));
			souDept(m2.group(1), m2.group(2));
		}
		return deptCodeList;
	}
	
	private static final String r3 = "<tbody id=\"data_list\">([\\s\\S]*)</tbody>";
	private static Pattern p3 = Pattern.compile(r3);
	
	private static final String r4 = "<tr><td class=\"(?:f-red|)\"><span>([0-9,\\.\\-]*)</span></td><td class=\"(?:f-green|)\"><span>([0-9,\\.\\-]*)</span></td>";
	private static Pattern p4 = Pattern.compile(r4);
	
	private List<String> parseAmt(String detail){
		List<String> amtList = new ArrayList<String>();
		Matcher m3 = p3.matcher(detail);
		while(m3.find()){
			String content = m3.group(1);
			content = content.replaceAll("[\\r\\n\\t]|  ", StringUtil.EMPTY);
			Matcher m4 = p4.matcher(content);
			while(m4.find()){
				amtList.add(m4.group(1)+SPLITER+m4.group(2));
				System.out.println(m4.group(1)+SPLITER+m4.group(2));
			}
		}
		return amtList;
	}
	
	private static String buildDetailUrl(String code, String date){
		StringBuilder sb = new StringBuilder();
		sb.append("http://m.data.eastmoney.com/lhb/StockDetailList/");
		sb.append(code);
		if(StringUtil.isNotBlank(date)){
			sb.append("?date=");
			sb.append(date);
		}
		return sb.toString();
	}
}
