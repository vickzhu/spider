package com.gesangwu.spider.biz.common;

import java.math.BigDecimal;

import com.gandalf.framework.util.StringUtil;

public class DecimalUtil {
	/**
	 * 将金额换算为万为单位
	 * @param amt
	 * @return
	 */
	public static BigDecimal toUnitWan(String decimal){
		if("null".equals(decimal)||StringUtil.isBlank(decimal)){
			return BigDecimal.ZERO;
		}
		BigDecimal buy = new BigDecimal(decimal);
		return buy.divide(new BigDecimal(10000)).setScale(2,BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * 四舍五入保留两位小数
	 * @param decimal
	 * @return
	 */
	public static BigDecimal parse(String decimal){
		return new BigDecimal(decimal).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
