package com.gesangwu.spider.engine.task.init;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.service.UpperShadowService;

@Component
public class UpperShadowInit {
	
	@Resource
	private UpperShadowService usService;

	public void execute(){
		
	}
}
