package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.LianBan;
import com.gesangwu.spider.biz.dao.model.LianBanExample;

public interface LianBanMapper extends BaseMapper<LianBan, LianBanExample> {
	
	public void batchInsert(List<LianBan> lbList);
	
}