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
	
	String selectLatestDate(String symbol);
	
	List<StockShareHolder> selectByPersonalName(String name);
	
	/**
	 * 个人持股
	 * @param example
	 * @return
	 */
	List<StockShareHolderExt> selectCliqueByExample(StockShareHolderExample example);
	/**
	 * 帮派持股总数
	 * @param example
	 * @return
	 */
	Integer countCliqueByExample(StockShareHolderExample example);
	/**
	 * 根据symbol查最新的股东
	 * @param symbol
	 * @return
	 */
	List<StockShareHolderExt> selectLatestBySymbol(String symbol);
	/**
	 * 根据symbol和日期查股东
	 * @param symbol
	 * @param endDate
	 * @return
	 */
	List<StockShareHolderExt> selectByEndDate(@Param("symbol")String symbol, @Param("endDate")String endDate);
	/**
	 * 根据symbol查所有股东日期
	 * @param symbol
	 * @return
	 */
	List<String> selectEndDate(@Param("symbol")String symbol);
	
	/**
	 * 帮派持股
	 * @param example
	 * @return
	 */
	List<StockShareHolderExt> selectStockByClique(StockShareHolderExample example);
	
}