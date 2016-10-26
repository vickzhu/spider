package com.gesangwu.spider.biz.dao.mapper;

import org.apache.ibatis.annotations.Param;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.HolderNum;
import com.gesangwu.spider.biz.dao.model.HolderNumExample;

public interface HolderNumMapper extends BaseMapper<HolderNum, HolderNumExample> {
    
	public HolderNum selectLatestBySymbol(@Param("symbol")String symbol);
	
}