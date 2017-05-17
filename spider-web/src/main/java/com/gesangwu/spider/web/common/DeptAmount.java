package com.gesangwu.spider.web.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;

/**
 * 营业部持仓金额
 * @author zhuxb
 *
 */
public class DeptAmount {
	
	private String deptCode;
	private String deptName;
	/**
	 * 剩余仓位
	 */
	private double remainAmount = 0;
	
	private Map<String, String> dateBuy = new TreeMap<String, String>();
	private Map<String, String> dateSell = new TreeMap<String, String>();
	
	private DeptAmount(){}
	
	public DeptAmount(String deptCode, String deptName){
		this.deptCode = deptCode;
		this.deptName = deptName;
	}
	
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public double getRemainAmount() {
		return remainAmount;
	}

	public void setRemainAmount(double remainAmount) {
		this.remainAmount = remainAmount;
	}

	public Map<String, String> getDateBuy() {
		return dateBuy;
	}

	public void setDateBuy(Map<String, String> dateBuy) {
		this.dateBuy = dateBuy;
	}

	public Map<String, String> getDateSell() {
		return dateSell;
	}

	public void addLongHu(LongHuDetail lhDetail){
		
		int dateType = lhDetail.getDateType();
		String tradeDate = lhDetail.getTradeDate();
		
		if(lhDetail.getBuyAmt().doubleValue() > 100){//大于100万才计算
			String amount = formatAmount(dateType, lhDetail.getBuyAmt().doubleValue());
			String amounts = dateBuy.get(tradeDate);
			if(StringUtil.isBlank(amounts)){
				dateBuy.put(tradeDate, amount);
			} else {
				amounts = amounts+SymbolConstant.COMMA + amount;
				dateBuy.put(tradeDate, amounts);
			}
		}
		//1、卖出日期大于0，表示第一天个龙虎榜的卖出不予计算
		//2、大于100万才计算在内
		if(dateSell.size() > 0 && lhDetail.getSellAmt().doubleValue() > 100){
			String amount = formatAmount(dateType, lhDetail.getSellAmt().doubleValue());
			String amounts = dateSell.get(tradeDate);
			if(StringUtil.isBlank(amounts)){
				dateSell.put(tradeDate, amount);
			} else {
				amounts = amounts+SymbolConstant.COMMA + amount;
				dateSell.put(tradeDate, amounts);
			}
		}
	}
	
	private String formatAmount(int dateType, double amount){
		StringBuilder sb = new StringBuilder();
		sb.append(dateType);
		sb.append(SymbolConstant.U_LINE);
		sb.append(amount);
		return sb.toString();
	}
	
	public static void main(String[] args){
		Map<String, Double> dm = new TreeMap<String, Double>();
		dm.put("2017-05-16", null);
		dm.put("2017-05-15", null);
		dm.put("2017-05-18", null);
		for (String key : dm.keySet()) {
			System.out.println(key);
		}
		List<String> list = new ArrayList<String>();
		list.add("2017-05-01");
		list.add("2017-05-02");
		list.add("2017-05-03");
		list.add("2017-05-04");
		list.add("2017-05-05");
		System.out.println(list.indexOf("2017-05-04"));
		
	}
}
