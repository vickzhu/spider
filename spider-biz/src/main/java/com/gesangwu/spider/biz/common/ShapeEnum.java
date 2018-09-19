package com.gesangwu.spider.biz.common;

public enum ShapeEnum {
	
	FIRST_NEG(12, "龙头首阴"), YI_ZI(13, "一字"), FALL_RISE(14, "先抑后扬"), COVER_NEG(15, "解放套牢"), BIG_DIVERGENCE(17,"大分歧"),UPPER_SHADOW(21,"长上影"),SKY_BIG_VOLUME(19,"飞天巨量"),
	IMMORTAL_GUIDER(18,"仙人指路"), JUMP_UP(22, "跳空高开"), GROUND_SKY(25, "地天");

	private int code;
	private String desc;
	
	private ShapeEnum(int code, String desc){
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
