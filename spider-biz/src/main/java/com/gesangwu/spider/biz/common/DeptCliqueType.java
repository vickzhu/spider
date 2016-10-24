package com.gesangwu.spider.biz.common;

public enum DeptCliqueType {

	MAIN(1,"主力"),ASSIST(2,"辅力");
	
	private int code;
	private String desc;
	
	private DeptCliqueType(int code, String desc){
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public static String getDesc(int code){
		for (DeptCliqueType type : DeptCliqueType.values()) {
			if(type.getCode() == code){
				return type.getDesc();
			}
		}
		return null;
	}
}
