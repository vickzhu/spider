package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.common.LongHuDetailPair;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExt;

public interface LongHuDetailService extends BaseService<LongHuDetail, LongHuDetailExample> {
	
	public void batchInsert(List<LongHuDetail> detailList);
	
	public LongHuDetailPair selectDetailPairs(String symbol, String tradeDate, int isSr);
	
	public void selectByPagination(LongHuDetailExample example, Page<LongHuDetail> page);
	
	public void selectDetailExtByExample(LongHuDetailExample example, Page<LongHuDetailExt> page);
}
