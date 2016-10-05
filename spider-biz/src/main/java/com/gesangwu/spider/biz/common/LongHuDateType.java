package com.gesangwu.spider.biz.common;

/**
 * 龙虎榜日期类型
 * @author bran
 *
 */
public enum LongHuDateType {
	
	YIRI(1),ERRI(2),SANRI(3);
	
	private int code;
	private LongHuDateType(int code){
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
}
