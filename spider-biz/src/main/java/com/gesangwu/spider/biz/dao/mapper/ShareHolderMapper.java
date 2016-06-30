package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.ShareHolder;
import com.gesangwu.spider.biz.dao.model.ShareHolderExample;

public interface ShareHolderMapper extends BaseMapper<ShareHolder, ShareHolderExample> {
	
	void insertShareHolderBatch(List<ShareHolder> shareHolderList);
	
	Double calcFloatRate(@Param("symbol")String symbol);
}