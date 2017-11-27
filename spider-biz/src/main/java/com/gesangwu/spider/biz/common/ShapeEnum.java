package com.gesangwu.spider.biz.common;

public enum ShapeEnum {
	
	DI_BU(11, "底部企稳"), FIRST_NEG(12, "龙头首阴"), MA_ADH(13, "均线粘合"), DOUBLE_BREAK(14, "双突破"), COVER_NEG(15, "解放套牢"),
	SHR_FALL(16, "缩跌"), BIG_DIVERGENCE(17,"大分歧"),UPPER_SHADOW(21,"长上影"),SKY_BIG_VOLUME(19,"飞天巨量"),
	IMMORTAL_GUIDER(18,"仙人指路"), ROU_CUO(20,"揉搓线"),JUMP_UP(22, "跳空高开"), DI_XI(23, "低吸"), JI_HUA(24, "季华");

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
