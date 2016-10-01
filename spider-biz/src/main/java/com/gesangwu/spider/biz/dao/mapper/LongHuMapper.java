package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuExample;

public interface LongHuMapper extends BaseMapper<LongHu, LongHuExample> {
	
	List<LongHu> selectByTradeDate(@Param("tradeDate")String tradeDate);
	
}