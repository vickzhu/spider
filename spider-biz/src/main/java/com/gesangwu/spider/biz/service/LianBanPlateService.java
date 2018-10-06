package com.gesangwu.spider.biz.service;

import java.util.List;

import com.gandalf.framework.mybatis.BaseService;
import com.gesangwu.spider.biz.dao.model.LianBanPlate;
import com.gesangwu.spider.biz.dao.model.LianBanPlateExample;

public interface LianBanPlateService extends BaseService<LianBanPlate, LianBanPlateExample> {

	public List<LianBanPlate> selectByTradeDate(String tradeDate);
	
}
