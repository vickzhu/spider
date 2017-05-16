package com.gesangwu.spider.web.common;

import java.util.Map;
import java.util.TreeMap;

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
	
	private Map<String, Double> dateBuy = new TreeMap<String, Double>();
	private Map<String, Double> dateSell = new TreeMap<String, Double>();
	
	private DeptAmount(){}
	
	public DeptAmount(String deptCode, String deptName){
		
	}
	
	public void addLongHu(LongHuDetail lhDetail){
		
		int dateType = lhDetail.getDateType();
		if(lhDetail.getBuyAmt().doubleValue() > 100){//大于100万才计算
			dateBuy.put(lhDetail.getTradeDate(), lhDetail.getBuyAmt().doubleValue());
		}
		if(lhDetail.getSellAmt().doubleValue() > 100){//大于100万才计算
			dateSell.put(lhDetail.getTradeDate(), lhDetail.getSellAmt().doubleValue());
		}
		if(dateType == 1){
			
		} else if(dateType == 2){
			//FIXME 去掉前两日的龙虎数据
			
		} else if(dateType == 3){
			//FIXME 去掉前三日的龙虎数据
			
		}
		
	}
	
	public static void main(String[] args){
		Map<String, Double> dm = new TreeMap<String, Double>();
		dm.put("2017-05-16", null);
		dm.put("2017-05-15", null);
		dm.put("2017-05-18", null);
		for (String key : dm.keySet()) {
			System.out.println(key);
		}
	}
}
