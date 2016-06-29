package com.gesangwu.spider.biz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.ShareHolderMapper;
import com.gesangwu.spider.biz.dao.model.ShareHolder;
import com.gesangwu.spider.biz.dao.model.ShareHolderExample;
import com.gesangwu.spider.biz.service.ShareHolderService;

@Service
public class ShareHolderServiceImpl extends BaseServiceImpl<ShareHolder, ShareHolderExample> implements
		ShareHolderService {

	@Resource
	private ShareHolderMapper mapper;

	@Override
	protected BaseMapper<ShareHolder, ShareHolderExample> getMapper() {
		return mapper;
	}

	@Override
	public void insertShareHolderBatch(List<ShareHolder> shareHolderList) {
		if(!CollectionUtils.isEmpty(shareHolderList)){			
			mapper.insertShareHolderBatch(shareHolderList);		
		}
	}

}
