package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;

public interface KLineMapper extends BaseMapper<KLine, KLineExample> {
    
	public void batchInsert(List<KLine> kLineList);
	
}