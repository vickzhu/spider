package com.gesangwu.spider.biz.common;

/**
 * 连板状态
 * @author bran
 *
 */
public enum LianBanStatus {

	ZT(1, "涨停"), FZT(2, "未涨停"), TP(3, "停牌");
	
	private int code;
	private String desc;
	
	private LianBanStatus(int code, String desc){
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	
}
