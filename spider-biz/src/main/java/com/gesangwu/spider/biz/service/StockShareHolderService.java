package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.StockShareHolder;
import com.gesangwu.spider.biz.dao.model.StockShareHolderExample;
import com.gesangwu.spider.biz.dao.model.ext.StockShareHolderExt;

public interface StockShareHolderService extends BaseService<StockShareHolder, StockShareHolderExample> {

	public void insertBatch(List<StockShareHolder> sshList);
	
	public Double calcFloatRate(String symbol);
	
	public String selectLatestDate(String symbol);
	
	public List<StockShareHolderExt> selectByPersonalName(String name);
	
	public List<StockShareHolderExt> selectExtByShareHolder(Long holderId);
	
}
