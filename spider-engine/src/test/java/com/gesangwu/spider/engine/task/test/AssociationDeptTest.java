package com.gesangwu.spider.engine.task.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.gandalf.framework.constant.SymbolConstant;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.Synergy;
import com.gesangwu.spider.biz.dao.model.SynergyDetail;
import com.gesangwu.spider.biz.dao.model.SynergyDetailExample;
import com.gesangwu.spider.biz.dao.model.SynergyExample;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.biz.service.SynergyDetailService;
import com.gesangwu.spider.biz.service.SynergyService;
import com.gesangwu.spider.engine.test.BaseTest;

/**
 * 营业部关联测试
 * @author zhuxb
 * 
 * 和80154611有过共同操作的营业部
 *SELECT dept,COUNT(*) c FROM test WHERE dept!='80154611' AND g IN (SELECT g FROM test WHERE dept='80154611' GROUP BY g) GROUP BY dept ORDER BY c DESC;
 *
 *关联度最高的营业部
 *SELECT g,COUNT(*) AS c FROM test WHERE dept='80154611' OR dept='80522975' GROUP BY g HAVING COUNT(*) > 1  ORDER BY c DESC;
 */
public class AssociationDeptTest extends BaseTest {

	
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhdService;
	
	@Resource
	private SynergyService sService;
	@Resource
	private SynergyDetailService sdService;
	
	@Test
	public void test(){
		long start = System.currentTimeMillis();
		
		LinkedList<LinkedList<String>> records = new LinkedList<LinkedList<String>>();
		Map<String, Map<String, LinkedList<String>>> maps = new HashMap<String, Map<String, LinkedList<String>>>();
		LongHuDetailExample lhdExample = new LongHuDetailExample();
		LongHuDetailExample.Criteria lhdCriteria = lhdExample.createCriteria();
		lhdCriteria.andTradeDateGreaterThanOrEqualTo("2017-03-05");
		List<LongHuDetail> detailList = lhdService.selectByExample(lhdExample);
		System.out.println("读取数据库花费" + (System.currentTimeMillis() - start) +"ms!");
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
		System.out.println("拼装Map花费" + (System.currentTimeMillis() - start) +"ms!");
		for(Map<String, LinkedList<String>> ml : maps.values()){
			for(LinkedList<String> list : ml.values()){
				records.add(list);
			}
		}
		System.out.println("组装数据源花费" + (System.currentTimeMillis() - start) +"ms!");
		FPGrowthUtil fpg=new FPGrowthUtil();
		LinkedList<FPTreeNode> orderheader=fpg.buildHeaderLink(records);
		System.out.println("build header花费" + (System.currentTimeMillis() - start) +"ms!");
		fpg.orderF1(orderheader);
		System.out.println("order header花费" + (System.currentTimeMillis() - start) +"ms!");
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
		System.out.println("finsh花费" + (System.currentTimeMillis() - start) +"ms!");
		System.out.println("大小："+resultMap.size());
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
