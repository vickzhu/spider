package com.gesangwu.spider.engine.common;

public enum SrType {
	
	SINGLE(1,"三日"),MULTI(2,"混合");
	
	private int code;
	private String desc;
	
	private SrType(int code, String desc){
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public static SrType getType(int code){
		for (SrType type : SrType.values()) {
			if(type.getCode() == code){
				return type;
			}
		}
		return null;
	}
}
