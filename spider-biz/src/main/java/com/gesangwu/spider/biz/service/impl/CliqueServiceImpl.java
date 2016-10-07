package com.gesangwu.spider.biz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gandalf.framework.mybatis.BaseServiceImpl;
import com.gesangwu.spider.biz.dao.mapper.CliqueMapper;
import com.gesangwu.spider.biz.dao.model.Clique;
import com.gesangwu.spider.biz.dao.model.CliqueExample;
import com.gesangwu.spider.biz.service.CliqueService;

@Service
public class CliqueServiceImpl extends BaseServiceImpl<Clique, CliqueExample> implements
		CliqueService {
	
	private static Map<Long, Clique> cliqueMap = new HashMap<Long, Clique>();

	@Resource
	private CliqueMapper mapper;
	
	@Override
	protected BaseMapper<Clique, CliqueExample> getMapper() {
		return mapper;
	}
	
	@PostConstruct
	public void init(){
		List<Clique> cliqueList = mapper.selectByExample(null);
		for (Clique clique : cliqueList) {
			cliqueMap.put(clique.getId(), clique);
		}
	}

	public Clique selectByPrimaryKey(Long primaryKey){
		Clique clique = cliqueMap.get(primaryKey);
		if(clique == null){
			clique = mapper.selectByPrimaryKey(primaryKey);
			if(clique != null){
				cliqueMap.put(clique.getId(), clique);
			}
		}
		return clique;
	}
	
}
