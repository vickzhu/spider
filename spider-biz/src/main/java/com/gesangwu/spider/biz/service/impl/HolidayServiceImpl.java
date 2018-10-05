package com.gesangwu.spider.biz.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.HolidayMapper;
import com.gesangwu.spider.biz.dao.model.Holiday;
import com.gesangwu.spider.biz.dao.model.HolidayExample;
import com.gesangwu.spider.biz.service.HolidayService;

@Service
public class HolidayServiceImpl extends BaseServiceImpl<Holiday, HolidayExample> implements
		HolidayService {
	
	@Resource
	private HolidayMapper mapper;

	@Override
	protected BaseMapper<Holiday, HolidayExample> getMapper() {
		return mapper;
	}

	@Override
	public List<String> selectByYear(String year) {
		HolidayExample example = new HolidayExample();
		HolidayExample.Criteria criteria = example.createCriteria();
		criteria.andYearEqualTo(year);
		List<Holiday> hList = mapper.selectByExample(example);
		List<String> dateList = new ArrayList<String>();
		if(CollectionUtils.isEmpty(hList)){
			return dateList;				
		}
		String dates = hList.get(0).getDates();
		String[] arrs = dates.split("\\|");
		for (String d : arrs) {
			String[] md = d.split(",");
			String date = assembleDate(year, md[0], md[1]);
			dateList.add(date);
		}
		return dateList;
	}
	
	private static final String ZERO = "0";
	
	private String assembleDate(String year, String month, String day){
		StringBuilder sb = new StringBuilder();
		sb.append(year);
		sb.append(SymbolConstant.H_LINE);
		if(month.length() == 1){
			sb.append(ZERO);
		}
		sb.append(month);
		sb.append(SymbolConstant.H_LINE);
		if(day.length() == 1){
			sb.append(ZERO);
		}
		sb.append(day);
		return sb.toString();
	}

	
}
