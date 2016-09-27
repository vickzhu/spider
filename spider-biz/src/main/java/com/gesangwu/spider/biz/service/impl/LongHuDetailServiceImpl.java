package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.LongHuDetailMapper;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.service.LongHuDetailService;

@Service
public class LongHuDetailServiceImpl extends BaseServiceImpl<LongHuDetail, LongHuDetailExample> implements
		LongHuDetailService {
	
	@Resource
	private LongHuDetailMapper mapper;

	@Override
	protected BaseMapper<LongHuDetail, LongHuDetailExample> getMapper() {
		return mapper;
	}

	@Override
	public void batchInsert(List<LongHuDetail> detailList) {
		mapper.insertBatch(detailList);
	}

}
