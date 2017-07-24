package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.mybatis.KeyValue;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;

public interface KLineService extends BaseService<KLine, KLineExample> {
	
	public void batchInsert(List<KLine> kLineList);
	
	public void selectByPagination(KLineExample example, Page<KLine> page);
	
	public String selectLatestDate();
	
	public List<Double> selectLastest30Close(String symbol, String lastDate);
	
	public List<KeyValue<String, Double>> selectLastest90Close(String symbol, String lastDate);
	
	public List<KLine> selectByShape(String tradeDate);
	
}
