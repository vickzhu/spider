package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.mapper.KLineMapper;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.KLineService;

@Service
public class KLineServiceImpl extends BaseServiceImpl<KLine, KLineExample> implements
		KLineService {
	
	@Resource
	private KLineMapper mapper;

	@Override
	protected BaseMapper<KLine, KLineExample> getMapper() {
		return mapper;
	}

	@Override
	public void batchInsert(List<KLine> kLineList) {
		mapper.batchInsert(kLineList);
	}

	@Override
	public void selectByPagination(KLineExample example, Page<KLine> page) {
		example.setOffset(page.getOffset());
        example.setRows(page.getPageSize());
        int totalCounts = mapper.countByExample(example);
        page.setTotalCounts(totalCounts);
        List<KLine> list = mapper.selectByExample(example);
        page.setRecords(list);
	}

	@Override
	public String selectLatestDate() {
		return mapper.selectLatestDate();
	}

	@Override
	public List<Double> selectLastest30Close(String symbol, String lastDate) {
		return mapper.selectLastest30Close(symbol, lastDate);
	}

}
