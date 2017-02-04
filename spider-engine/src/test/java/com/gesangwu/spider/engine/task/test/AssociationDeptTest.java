package com.gesangwu.spider.engine.task.test;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.LongHuExample;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.engine.test.BaseTest;

/**
 * 营业部关联测试
 * @author zhuxb
 *
 */
public class AssociationDeptTest extends BaseTest {

	
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhdService;
	
	@Test
	public void test(){
		LongHuExample example = new LongHuExample();
		LongHuExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateGreaterThanOrEqualTo("2017-02-01");
		
		List<LongHu> longHuList = lhService.selectByExample(example);
		
		FPGrowthUtil fpg=new FPGrowthUtil();
		LinkedList<LinkedList<String>> records=new LinkedList<LinkedList<String>>();
		
		for (LongHu longHu : longHuList) {
			LongHuDetailExample lhdExample = new LongHuDetailExample();
			LongHuDetailExample.Criteria lhdCriteria = lhdExample.createCriteria();
			lhdCriteria.andLongHuIdEqualTo(longHu.getId());
			List<LongHuDetail> detailList = lhdService.selectByExample(lhdExample);
			LinkedList<String> ll = new LinkedList<String>();
			for (LongHuDetail detail : detailList) {
				ll.add(detail.getSecDeptCode());
			}
			records.add(ll);
		}
		LinkedList<FPTreeNode> orderheader=fpg.buildHeaderLink(records);
		fpg.orderF1(orderheader);
		fpg.fpgrowth(records,null);
	}
}
