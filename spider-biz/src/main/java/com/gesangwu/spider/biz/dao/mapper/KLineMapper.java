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
	
	public List<KeyValue<String, Double>> selectLastestClose(KLineExample example);
	
	/**
	 * 适合判断形态的K线，包括多头和五日线低于十日线的多头
	 * @param tradeDate
	 * @return
	 */
	public List<KLine> selectForShape(@Param("tradeDate")String tradeDate);
	
	/**
	 * 多头
	 * @param tradeDate
	 * @return
	 */
	public List<KLine> selectByPositive(@Param("tradeDate")String tradeDate);
	
	public void updateShape(@Param("shape")int shape, @Param("idList")List<Long> idList);

}