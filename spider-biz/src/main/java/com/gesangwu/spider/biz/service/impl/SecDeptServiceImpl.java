package com.gesangwu.spider.biz.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.dao.mapper.SecDeptMapper;
import com.gesangwu.spider.biz.dao.model.SecDept;
import com.gesangwu.spider.biz.dao.model.SecDeptExample;
import com.gesangwu.spider.biz.service.SecDeptService;

@Service
public class SecDeptServiceImpl extends BaseServiceImpl<SecDept, SecDeptExample> implements
		SecDeptService {
	
	@Resource
	private SecDeptMapper mapper;

	@Override
	protected BaseMapper<SecDept, SecDeptExample> getMapper() {
		return mapper;
	}

	@Override
	public void batchInsert(List<SecDept> secDeptList) {
		mapper.insertBatch(secDeptList);		
	}

	@Override
	public SecDept selectByCode(String code) {
		SecDeptExample example = new SecDeptExample();
		SecDeptExample.Criteria criteria = example.createCriteria();
		criteria.andCodeEqualTo(code);
		List<SecDept> deptList = mapper.selectByExample(example);
		return CollectionUtils.isEmpty(deptList) ? null : deptList.get(0);
	}

	@Override
	public void clearActiveDept() {
		mapper.clearActiveDept();		
	}

	@Override
	public void updateActiveDept(String tradeDate) {
		String startDate = getStartDate(tradeDate);
		if(StringUtil.isBlank(startDate)){
			return;
		}
		mapper.updateActiveDept(startDate);
	}
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 往前推3个月
	 * @param tradeDate
	 * @return
	 */
	private static String getStartDate(String tradeDate){
		try {
			Date date = sdf.parse(tradeDate);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MONTH, -3);
			return sdf.format(c.getTime());
		} catch (Exception e) {
			return null;
		}
	}

}
