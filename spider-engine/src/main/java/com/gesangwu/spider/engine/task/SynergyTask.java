package com.gesangwu.spider.engine.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.Synergy;
import com.gesangwu.spider.biz.dao.model.SynergyDetail;
import com.gesangwu.spider.biz.dao.model.SynergyDetailExample;
import com.gesangwu.spider.biz.dao.model.SynergyExample;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.SynergyDetailService;
import com.gesangwu.spider.biz.service.SynergyService;
import com.gesangwu.spider.engine.task.test.FPGrowthUtil;
import com.gesangwu.spider.engine.task.test.FPTreeNode;

@Component
public class SynergyTask {
	
	private static final Logger logger = LoggerFactory.getLogger(SynergyTask.class);
	
	@Resource
	private LongHuDetailService lhdService;
	@Resource
	private SynergyService sService;
	@Resource
	private SynergyDetailService sdService;
	
	@Scheduled(cron = "0 0 12 * * MON-FRI")
	public void execute(){
		
	}
	
	@Test
	public void test(String startDate, String endDate){
		long start = System.currentTimeMillis();
		LinkedList<LinkedList<String>> records = new LinkedList<LinkedList<String>>();
		Map<String, Map<String, LinkedList<String>>> maps = new HashMap<String, Map<String, LinkedList<String>>>();
		LongHuDetailExample lhdExample = new LongHuDetailExample();
		LongHuDetailExample.Criteria lhdCriteria = lhdExample.createCriteria();
		lhdCriteria.andTradeDateGreaterThanOrEqualTo("2017-03-05");
		List<LongHuDetail> detailList = lhdService.selectByExample(lhdExample);

		for (LongHuDetail longHuDetail : detailList) {
			if(longHuDetail.getDateType() > 1){
				continue;
			}
			if(longHuDetail.getSecDeptCode().compareTo("20000000") < 0){
				continue;
			}
			Map<String, LinkedList<String>> datesMap = maps.get(longHuDetail.getSymbol());
			if(datesMap == null){
				datesMap = new HashMap<String, LinkedList<String>>();
				maps.put(longHuDetail.getSymbol(), datesMap);
			}
			LinkedList<String> list = datesMap.get(longHuDetail.getTradeDate());
			if(list == null){
				list = new LinkedList<String>();
				datesMap.put(longHuDetail.getTradeDate(), list);
			}
			list.add(longHuDetail.getSecDeptCode());
		}
		for(Map<String, LinkedList<String>> ml : maps.values()){
			for(LinkedList<String> list : ml.values()){
				records.add(list);
			}
		}
		FPGrowthUtil fpg=new FPGrowthUtil();
		LinkedList<FPTreeNode> orderheader=fpg.buildHeaderLink(records);
		fpg.orderF1(orderheader);
		Map<String, Integer> resultMap = fpg.fpgrowth(records,null);
		emptySynergy();
		emptySynergyDetial();
		int group = 1;
		List<Synergy> sList = new ArrayList<Synergy>();
		List<SynergyDetail> sdList = new ArrayList<SynergyDetail>();
		Date now = new Date();
		for (Map.Entry<String, Integer> entry: resultMap.entrySet()) {
			String[] depts = entry.getKey().split(SymbolConstant.COMMA);
			Synergy s = new Synergy();
			s.setsG(group);
			s.setTotal(entry.getValue());
			s.setDeptCount(depts.length);
			s.setGmtCreate(now);
			sList.add(s);
			for (String dept : depts) {
				SynergyDetail sd = new SynergyDetail();
				sd.setDept(dept);
				sd.setsG(group);
				sdList.add(sd);
			}
			
			group++;
		}
		sService.insertSynergyBatch(sList);
		sdService.insertSynergyDetailBatch(sdList);
		logger.info("SynergyTask cost " + (System.currentTimeMillis() - start) +"ms!");
	}
	
	private void emptySynergy(){
		SynergyExample example = new SynergyExample();
		SynergyExample.Criteria criteria = example.createCriteria();
		criteria.andIdGreaterThan(0l);
		sService.deleteByExample(example);
	}
	
	private void emptySynergyDetial(){
		SynergyDetailExample example = new SynergyDetailExample();
		SynergyDetailExample.Criteria criteria = example.createCriteria();
		criteria.andIdGreaterThan(0l);
		sdService.deleteByExample(example);
	}
}
