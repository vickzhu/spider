package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.mybatis.KeyValue;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;

public interface KLineService extends BaseService<KLine, KLineExample> {
	
	public void batchInsert(List<KLine> kLineList);
	
	public void selectByPagination(KLineExample example, Page<KLine> page);
	
	public String selectLatestDate();
	
	/**
	 * 适合判断形态的K线，包括多头和五日线低于十日线的多头
	 * @param tradeDate
	 * @return
	 */
	public List<KLine> selectForShape(String tradeDate);
	
	/**
	 * 多头
	 * @param tradeDate
	 * @return
	 */
	public List<KLine> selectByPositive(String tradeDate);
	
	/**
	 * 更改形态
	 * @param shape
	 * @param idList
	 */
	public void updateShape(ShapeEnum shape, List<Long> idList);
	
	/**
	 * 获取截止时间前的指定条数记录
	 * @param symbol
	 * @param lastDate	截止时间
	 * @param rows	记录数
	 * @return	KeyValue key:trade_date,value:close
	 */
	public List<KeyValue<String, Double>> selectLastestClose(KLineExample example);
	
}
