package com.gesangwu.spider.biz.dao.mapper;

import java.util.List;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExt;
import com.gesangwu.spider.biz.dao.model.ext.LongHuDetailDept;

public interface LongHuDetailMapper extends BaseMapper<LongHuDetail, LongHuDetailExample> {
    
	void insertBatch(List<LongHuDetail> detailList);
	
	List<LongHuDetailExt> selectDetailExtByExample(LongHuDetailExample example);
	
	List<LongHuDetailDept> selectDetailDeptByExample(LongHuDetailExample example);
}