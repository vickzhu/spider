package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.FiveRangeStatis;
import com.gesangwu.spider.biz.dao.model.FiveRangeStatisExample;

public interface FiveRangeStatisMapper extends BaseMapper<FiveRangeStatis, FiveRangeStatisExample> {
    
	List<FiveRangeStatis> selectByTradeDate(@Param("tradeDate")String tradeDate,@Param("minAmv")double minAmv, @Param("maxAmv")double maxAmv);
	
}