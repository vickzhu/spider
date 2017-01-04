package com.gesangwu.spider.biz.service;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.ShareHolder;
import com.gesangwu.spider.biz.dao.model.ShareHolderExample;

public interface ShareHolderService extends BaseService<ShareHolder, ShareHolderExample> {
	
	public ShareHolder selectByHoldCode(String holderCode);
	
	public ShareHolder selectHolder(int holderType, String holderName);
	
}
