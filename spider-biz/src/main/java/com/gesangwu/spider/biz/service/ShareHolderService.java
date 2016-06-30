package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.ShareHolder;
import com.gesangwu.spider.biz.dao.model.ShareHolderExample;

public interface ShareHolderService extends BaseService<ShareHolder, ShareHolderExample> {

	public void insertShareHolderBatch(List<ShareHolder> shareHolderList);
	
	public Double calcFloatRate(String symbol);
}
