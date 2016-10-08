package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.mapper.CliqueDeptMapper;
import com.gesangwu.spider.biz.dao.model.CliqueDept;
import com.gesangwu.spider.biz.dao.model.CliqueDeptExample;
import com.gesangwu.spider.biz.service.CliqueDeptService;

@Service
public class CliqueDeptServiceImpl extends BaseServiceImpl<CliqueDept, CliqueDeptExample> implements
		CliqueDeptService {
	
	@Resource
	private CliqueDeptMapper mapper;

	@Override
	protected BaseMapper<CliqueDept, CliqueDeptExample> getMapper() {
		return mapper;
	}

	@Override
	public void selectByCliqueId(long cliqueId, Page<CliqueDept> page) {
		CliqueDeptExample example = new CliqueDeptExample();
		example.setOffset(page.getOffset());
        example.setRows(page.getPageSize());
        example.setOrderByClause("id desc");
        CliqueDeptExample.Criteria criteria = example.createCriteria();
        criteria.andCliqueIdEqualTo(cliqueId);
        int totalCounts = mapper.countByExample(example);
        page.setTotalCounts(totalCounts);
        page.setRecords(mapper.selectByExample(example));		
	}

	@Override
	public List<CliqueDept> selectByDeptCode(String deptCode) {
		CliqueDeptExample example = new CliqueDeptExample();
		CliqueDeptExample.Criteria criteria = example.createCriteria();
		criteria.andSecDeptCodeEqualTo(deptCode);
		return mapper.selectByExample(example);
	}

	@Override
	public List<CliqueDept> selectByCliqueId(long cliqueId) {
		CliqueDeptExample example = new CliqueDeptExample();
		CliqueDeptExample.Criteria criteria = example.createCriteria();
		criteria.andCliqueIdEqualTo(cliqueId);
		return mapper.selectByExample(example);
	}
}
