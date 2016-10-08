package com.gesangwu.spider.engine.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.gesangwu.spider.biz.dao.model.Clique;
import com.gesangwu.spider.biz.dao.model.CliqueDept;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import com.gesangwu.spider.biz.dao.model.LongHuExample;
import com.gesangwu.spider.biz.service.CliqueDeptService;
import com.gesangwu.spider.biz.service.CliqueService;
import com.gesangwu.spider.biz.service.LongHuDetailService;
import com.gesangwu.spider.biz.service.LongHuService;

/**
 * 完善帮派
 * @author zhuxb
 *
 */
@Component
public class PerfectCliqueTask {
	
	@Resource
	private CliqueService cliqueService;
	@Resource
	private CliqueDeptService cliqueDeptService;
	@Resource
	private LongHuService lhService;
	@Resource
	private LongHuDetailService lhdService;
	
	private Set<String> memberSet;//保存现有的帮派成员
	
	public void execute(){
		List<Clique> cliqueList = cliqueService.selectByExample(null);
		for (Clique clique : cliqueList) {
			List<CliqueDept> deptList = cliqueDeptService.selectByCliqueId(clique.getId());
			memberSet = new HashSet<String>();
			for (CliqueDept dept : deptList) {
				memberSet.add(dept.getSecDeptCode());
			}
			List<LongHu> lhList = getLongHuList();
			for (LongHu longHu : lhList) {//每天龙虎榜
				Date date = new Date();
				Map<Long,List<LongHuDetail>> detailMap = new HashMap<Long,List<LongHuDetail>>();
				List<LongHuDetail> detailList = getLongHuDetailList(longHu.getSymbol(), longHu.getTradeDate());
				for (LongHuDetail longHuDetail : detailList) {//每条龙虎详情
					List<CliqueDept> cliqueDeptList = cliqueDeptService.selectByDeptCode(longHuDetail.getSecDeptCode());
					for (CliqueDept cliqueDept : cliqueDeptList) {//每个营业部属于那些帮派
						List<LongHuDetail> cliqueDetailList = detailMap.get(cliqueDept.getCliqueId());
						if(CollectionUtils.isEmpty(cliqueDetailList)){
							cliqueDetailList = new ArrayList<LongHuDetail>();
							detailMap.put(cliqueDept.getCliqueId(), cliqueDetailList);
						}
						cliqueDetailList.add(longHuDetail);
					}
				}
				for(Map.Entry<Long,List<LongHuDetail>> entry : detailMap.entrySet()){//遍历所有数据，统计帮派
					if(entry.getValue().size() > 2){//同一帮派大于两个营业部介入才算
						//XXX 有可能两个帮派同时大于3个营业部介入，先不管这种情况
						List<LongHuDetail> lhdList = entry.getValue();
						longHu.setCliqueId(entry.getKey());
						longHu.setSecDeptRelation(lhdList.size());
						longHu.setGmtUpdate(date);
						lhService.updateByPrimaryKey(longHu);
						for (LongHuDetail longHuDetail : lhdList) {
							
						}
						break;
					}
				}
			}
		}
	}
	
	private List<LongHu> getLongHuList(){
		LongHuExample lhExample = new LongHuExample();
		LongHuExample.Criteria criteria = lhExample.createCriteria();
		criteria.andTradeDateGreaterThanOrEqualTo("2015-01-01");
		return lhService.selectByExample(lhExample);
	}
	
	private List<LongHuDetail> getLongHuDetailList(String symbol, String tradeDate){
		LongHuDetailExample lhdExample = new LongHuDetailExample();
		LongHuDetailExample.Criteria criteria = lhdExample.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateEqualTo(tradeDate);
		return lhdService.selectByExample(lhdExample);
	}

}
