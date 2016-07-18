package com.gesangwu.spider.engine.task;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.BankerTrace;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.BankerTraceService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.engine.util.LittleCompanyHolder;

/**
 * 主力跟踪统计
 * @author zhuxb
 *
 */
@Component
public class BankerTraceStatisTask {
	
	@Resource
	private KLineService kLineService;
	@Resource
	private BankerTraceService traceService;

	public void execute(){
		List<Company> companyList = LittleCompanyHolder.getCompanyList();
		for (Company company : companyList) {
			KLineExample example = new KLineExample();
			KLineExample.Criteria criteria = example.createCriteria();
			criteria.andSymbolEqualTo(company.getSymbol());
			example.setOrderByClause("trans_date desc");
			Page<KLine> page = new Page<KLine>(1,15);
			kLineService.selectByPagination(example, page);
			List<KLine> kLineList = page.getRecords();
			if(CollectionUtils.isEmpty(kLineList)){
				continue;
			}
			BankerTrace trace = null;
			for(int i = kLineList.size()-2; i >= 0; i--){
				KLine kLine = kLineList.get(i);
				double percent = kLine.getPercent();
				if(percent <= 2 || percent >= 8){
					continue;
				}
				//这里继续判断里程碑日的逻辑
				KLine preKLine = kLineList.get(i-1);
				double prePercent = preKLine.getPercent();
				if(Math.abs(prePercent) >= 2){
					continue;
				}
				//这里判断起点逻辑
				
				trace = new BankerTrace();//成立的情况下才新建对象
			}
			
			if(trace != null){
				traceService.insert(trace);
			}
		}
	}
	
	private boolean milestone(){
		return Boolean.TRUE;
	}
	
	private boolean launchDate(){
		return Boolean.TRUE;
	}
}
