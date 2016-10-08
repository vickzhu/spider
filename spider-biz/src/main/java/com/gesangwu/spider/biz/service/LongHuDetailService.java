package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.common.LongHuDetailPair;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExt;
import com.gesangwu.spider.biz.dao.model.ext.LongHuDetailDept;

public interface LongHuDetailService extends BaseService<LongHuDetail, LongHuDetailExample> {
	
	public void batchInsert(List<LongHuDetail> detailList);
	
	public LongHuDetailPair selectDetailPairs(String symbol, String tradeDate, int dateType);
	
	public void selectByPagination(LongHuDetailExample example, Page<LongHuDetail> page);
	
	public void selectDetailExtByExample(LongHuDetailExample example, Page<LongHuDetailExt> page);
	
	public List<LongHuDetailDept> selectDetailDeptByExample(LongHuDetailExample example);
	
	/**
	 * 查询指定时间内的详情
	 * @param deptCode	营业部
	 * @param longHuCliqueId	龙虎榜的帮派类型
	 * @param startDate	起始时间
	 * @return
	 */
	public List<LongHuDetail> selectDetail(String deptCode, long longHuCliqueId, String startDate);
}
