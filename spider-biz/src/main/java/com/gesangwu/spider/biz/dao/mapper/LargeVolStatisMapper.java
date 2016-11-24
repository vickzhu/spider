package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.LargeVolStatis;
import com.gesangwu.spider.biz.dao.model.LargeVolStatisExample;

public interface LargeVolStatisMapper extends BaseMapper<LargeVolStatis, LargeVolStatisExample> {
	
	List<LargeVolStatis> selectByTradeDate(@Param("tradeDate")String tradeDate, @Param("minAmv")double minAmv, @Param("maxAmv")double maxAmv);
	
}