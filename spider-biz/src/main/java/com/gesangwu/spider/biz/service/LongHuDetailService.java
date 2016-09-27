package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;

public interface LongHuDetailService extends BaseService<LongHuDetail, LongHuDetailExample> {
	
	public void batchInsert(List<LongHuDetail> detailList);
	
}
