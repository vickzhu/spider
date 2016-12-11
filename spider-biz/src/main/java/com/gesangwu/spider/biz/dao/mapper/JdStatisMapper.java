package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.JdStatis;
import com.gesangwu.spider.biz.dao.model.JdStatisExample;

public interface JdStatisMapper extends BaseMapper<JdStatis, JdStatisExample> {
    
	List<JdStatis> selectByTradeDate(@Param("tradeDate")String tradeDate, @Param("minAmv")double minAmv, @Param("maxAmv")double maxAmv);
	
}