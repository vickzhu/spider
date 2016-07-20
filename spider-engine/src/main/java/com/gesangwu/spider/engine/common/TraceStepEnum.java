package com.gesangwu.spider.engine.common;

public enum TraceStepEnum {
	
	MILESTONE(1,"里程碑"),SUIBU(2,"碎步吸筹"),SURE(3,"确认"),CANCEL(4,"取消");
	
	private int code;
	private String desc;
	
	private TraceStepEnum(int code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public TraceStepEnum getStep(int code){
		for (TraceStepEnum step : TraceStepEnum.values()) {
			if(step.getCode() == code){
				return step;
			}
		}
		return null;
	}
		
}
