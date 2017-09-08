package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.KeyValue;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExt;
import com.gesangwu.spider.biz.dao.model.ext.LongHuDetailDept;

public interface LongHuDetailMapper extends BaseMapper<LongHuDetail, LongHuDetailExample> {
    
	void insertBatch(List<LongHuDetail> detailList);
	
	List<LongHuDetailExt> selectDetailExtByExample(LongHuDetailExample example);
	
	List<LongHuDetailDept> selectDetailDeptByExample(LongHuDetailExample example);
	
	List<LongHuDetail> selectDetail(@Param("deptCode")String deptCode, 
			@Param("cliqueId")Long longHuCliqueId, @Param("startDate")String startDate, @Param("endDate")String endDate);
	
	int count4Clique(@Param("deptCode")String deptCode, @Param("symbol")String symbol,
			@Param("cliqueId")Long cliqueId, @Param("startDate")String startDate, @Param("endDate")String endDate);

	void clearClique(@Param("longHuId")Long longHuId);
	
	/**
	 * 查找关联的股票
	 * @param startDate
	 * @param endDate
	 * @param synergyGroup
	 * @return
	 */
	List<KeyValue<String, String>> selectRelationStock(@Param("startDate")String startDate, @Param("endDate")String endDate,  @Param("synergyGroup")int synergyGroup);
	
	/**
	 * 根据活跃营业部查询
	 * @param tradeDate
	 * @return
	 */
	List<LongHuDetail> selectByActiveDept(@Param("tradeDate")String tradeDate);
	
}