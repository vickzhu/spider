package com.gesangwu.spider.biz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.cache.LongHuTypeCache;
import com.gesangwu.spider.biz.dao.mapper.LongHuTypeMapper;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.dao.model.LongHuTypeExample;
import com.gesangwu.spider.biz.service.LongHuTypeService;

@Service
public class LongHuTypeServiceImpl extends BaseServiceImpl<LongHuType, LongHuTypeExample> implements LongHuTypeService {
	
	@Resource
	private LongHuTypeMapper mapper;

	@Override
	protected BaseMapper<LongHuType, LongHuTypeExample> getMapper() {
		return mapper;
	}
	
	@PostConstruct
	public void init(){
		List<LongHuType> typeList = mapper.selectByExample(null);
		Map<String, LongHuType> typeMap = new HashMap<String, LongHuType>();
		for (LongHuType longHuType : typeList) {
			typeMap.put(longHuType.getLhType(), longHuType);
		}
		LongHuTypeCache.setTypeMap(typeMap);
	}

	@Override
	public LongHuType selectByType(String type) {
		LongHuType lhType = LongHuTypeCache.get(type);
		if(lhType == null){
			LongHuTypeExample example = new LongHuTypeExample();
			LongHuTypeExample.Criteria criteria = example.createCriteria();
			criteria.andLhTypeEqualTo(type);
			List<LongHuType> typeList = mapper.selectByExample(example);
			if(!CollectionUtils.isEmpty(typeList)){
				lhType = typeList.get(0);
				LongHuTypeCache.add(lhType);
			}
		}
		return lhType;
	}

}
