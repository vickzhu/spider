package com.gesangwu.spider.biz.common;

public enum HolderType {
	
	PERSON(1, "个人"), ORG(2, "机构");
	
	private int type;
	private String desc;
	
	private HolderType(int type, String desc){
		this.type = type;
		this.desc = desc;
	}

	public int getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}
	
}
