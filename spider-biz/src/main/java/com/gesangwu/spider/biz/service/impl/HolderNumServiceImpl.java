package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.mapper.HolderNumMapper;
import com.gesangwu.spider.biz.dao.model.HolderNum;
import com.gesangwu.spider.biz.dao.model.HolderNumExample;
import com.gesangwu.spider.biz.service.HolderNumService;

@Service
public class HolderNumServiceImpl extends BaseServiceImpl<HolderNum, HolderNumExample> implements
		HolderNumService {
	
	@Resource
	private HolderNumMapper mapper;

	@Override
	protected BaseMapper<HolderNum, HolderNumExample> getMapper() {
		return mapper;
	}

	@Override
	public HolderNum selectLatestBySymbol(String symbol) {
		return mapper.selectLatestBySymbol(symbol);
	}

	@Override
	public void insertBatch(List<HolderNum> hnList) {
		mapper.insertBatch(hnList);		
	}

	@Override
	public void selectByPagination(HolderNumExample example,
			Page<HolderNum> page) {
		example.setOffset(page.getOffset());
        example.setRows(page.getPageSize());
        int totalCounts = mapper.countByExample(example);
        page.setTotalCounts(totalCounts);
        List<HolderNum> hnList = mapper.selectByExample(example);
        page.setRecords(hnList);
	}

}
