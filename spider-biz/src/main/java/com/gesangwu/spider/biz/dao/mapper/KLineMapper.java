package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.KeyValue;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;

public interface KLineMapper extends BaseMapper<KLine, KLineExample> {
    
	public void batchInsert(List<KLine> kLineList);
	
	public String selectLatestDate();
	
	public List<Double> selectLastest30Close(@Param("symbol")String symbol, @Param("lastDate")String lastDate);
	
	public List<KeyValue<String, Double>> selectLastest90Close(@Param("symbol")String symbol, @Param("lastDate")String lastDate);
	
	public List<KLine> selectByShape(@Param("tradeDate")String tradeDate);
	
	public void updateShape(@Param("shape")int shape, @Param("idList")List<Long> idList);

}