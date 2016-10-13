package com.gesangwu.spider.biz.common;

import com.gandalf.framework.util.StringUtil;

/**
 * 敢死队也是一线游资
 * @author zhuxb
 *
 */
public enum SecDeptType {
	
	NONE(0,"无"),GSD(2,"敢死队"),YXYZ(1,"一线游资");
	
	private int code;
	private String desc;
	
	private SecDeptType(int code, String desc){
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
		for (SecDeptType type : SecDeptType.values()) {
			if(type.getCode() == code){
				return type.getDesc();
			}
		}
		
		return StringUtil.EMPTY;
	}
}
