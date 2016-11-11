package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.StockShareHolder;
import com.gesangwu.spider.biz.dao.model.StockShareHolderExample;
import com.gesangwu.spider.biz.dao.model.ext.StockShareHolderExt;

public interface StockShareHolderMapper extends BaseMapper<StockShareHolder, StockShareHolderExample> {

	void insertBatch(List<StockShareHolder> sshList);
	
	Double calcFloatRate(String symbol);
	
	public String selectLatestDate(String symbol);
	
	public List<StockShareHolderExt> selectByPersonalName(String name);
		
	public List<StockShareHolderExt> selectExtByShareHolder(@Param("holderId")Long holderId);
}