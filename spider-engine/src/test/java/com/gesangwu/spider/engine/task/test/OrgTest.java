package com.gesangwu.spider.engine.task.test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

import com.gandalf.framework.util.CalculateUtil;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.LongHuExample;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;
import com.gesangwu.spider.engine.test.BaseTest;

public class OrgTest extends BaseTest {
	
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhdService;

	@Test
	public void execute(){
		LongHuExample example = new LongHuExample();
		LongHuExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateGreaterThan("2017-05-31");
		List<LongHu> lhList = lhService.selectByExample(example);
		for (LongHu lh : lhList) {
			LongHuDetailExample lhdExample = new LongHuDetailExample();
			LongHuDetailExample.Criteria lhdCriteria = lhdExample.createCriteria();
			lhdCriteria.andLongHuIdEqualTo(lh.getId());
			lhdCriteria.andDateTypeEqualTo(1);
			List<LongHuDetail> lhdList = lhdService.selectByExample(lhdExample);
			if(CollectionUtils.isEmpty(lhdList)){
				continue;
			}
			if(isOrg(lhdList)){
				lh.setOperateClique(1000l);
				lh.setGmtUpdate(new Date());
				lhService.updateByPrimaryKey(lh);
				System.out.println(lh.getSymbol()+":"+lh.getTradeDate());
			}
		}
	}
	
	/**
	 * 判断是否为机构
	 * @param lhdList
	 * @return
	 */
	public boolean isOrg(List<LongHuDetail> lhdList){
		double total = 0;
		double orgTotal = 0;
		for (LongHuDetail lhd : lhdList) {
			total = CalculateUtil.add(total, lhd.getBuyAmt().doubleValue());
			long secDeptCode = Long.valueOf(lhd.getSecDeptCode());
			if(secDeptCode > 20000000 || secDeptCode < 19000000){
				continue;
			}
			if(lhd.getBuyAmt().doubleValue() < 100){//小于100万的不予考虑
				continue;
			}
			orgTotal = CalculateUtil.add(orgTotal, lhd.getBuyAmt().doubleValue());
		}
		return CalculateUtil.mul(orgTotal, 5) > CalculateUtil.mul(total, 2);
	}
}
