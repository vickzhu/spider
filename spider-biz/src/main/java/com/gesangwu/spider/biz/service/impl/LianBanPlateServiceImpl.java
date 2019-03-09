package com.gesangwu.spider.biz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.LianBanPlateMapper;
import com.gesangwu.spider.biz.dao.model.LianBanPlate;
import com.gesangwu.spider.biz.dao.model.LianBanPlateExample;
import com.gesangwu.spider.biz.service.LianBanPlateService;

@Service
public class LianBanPlateServiceImpl extends BaseServiceImpl<LianBanPlate, LianBanPlateExample> implements
		LianBanPlateService {
	
	@Resource
	private LianBanPlateMapper mapper;

	@Override
	protected BaseMapper<LianBanPlate, LianBanPlateExample> getMapper() {
		return mapper;
	}

	@Override
	public List<LianBanPlate> selectByTradeDate(String tradeDate) {
		LianBanPlateExample example = new LianBanPlateExample();
		LianBanPlateExample.Criteria criteria1 = example.createCriteria();
		criteria1.andTradeDateEqualTo(tradeDate);
		LianBanPlateExample.Criteria criteria2 = example.createCriteria();
		criteria2.andTradeDateIsNull();
		example.or(criteria2);
		return mapper.selectByExample(example);
	}

	@Override
	public Map<Long, String> selectByTradeDateForMap(String tradeDate) {
		Map<Long, String> lbpMap = new HashMap<Long, String>();
		List<LianBanPlate> lbpList = selectByTradeDate(tradeDate);
		for (LianBanPlate lbp : lbpList) {
			lbpMap.put(lbp.getId(), lbp.getPlate());
		}
		return lbpMap;
	}

	@Override
	public int insert(LianBanPlate record) {
		LianBanPlate plate = selectByPlate(record.getPlate());
		if(plate != null){
			return 0;
		} else {
			return super.insert(record);
		}
	}

	@Override
	public int insertSelective(LianBanPlate record) {
		LianBanPlate plate = selectByPlate(record.getPlate());
		if(plate != null){
			return 0;
		} else {
			return super.insertSelective(record);
		}
	}

	@Override
	public LianBanPlate selectByPlate(String plate) {
		LianBanPlateExample example = new LianBanPlateExample();
		LianBanPlateExample.Criteria criteria = example.createCriteria();
		criteria.andPlateEqualTo(plate);
		List<LianBanPlate> pList = mapper.selectByExample(example);
		return CollectionUtils.isNotEmpty(pList) ? pList.get(0) : null;
	}
	
	
}
