package com.gesangwu.spider.engine.task;

import java.util.Date;
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
import com.gesangwu.spider.engine.exception.BankerTraceException;
import com.gesangwu.spider.engine.exception.MilestoneException;
import com.gesangwu.spider.engine.util.LittleCompanyHolder;
import com.gesangwu.spider.engine.util.ScoreUtil;

/**
 * 寻找里程碑日
 * @author zhuxb
 *
 */
@Component
public class FindMilestoneTask {
	
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
			Page<KLine> page = new Page<KLine>(1,2);
			kLineService.selectByPagination(example, page);
			List<KLine> kLineList = page.getRecords();
			if(CollectionUtils.isEmpty(kLineList) || kLineList.size() != 2){
				continue;
			}
			KLine kLine = kLineList.get(0);
			try {
				int milestoneScore = ScoreUtil.milestone(kLine);
				KLine preKLine = kLineList.get(1);
				if(kLine.getVolume() <= preKLine.getVolume()){//里程碑日成交量需大于启动日
					throw new MilestoneException();
				}
				int launchScore = ScoreUtil.launchDay(preKLine, company);
				BankerTrace trace = new BankerTrace();
				trace.setGmtCreate(new Date());
				trace.setMsDate(kLine.getTransDate());
				trace.setScores(milestoneScore + launchScore);
				trace.setStep(1);
				trace.setSymbol(kLine.getSymbol());
				traceService.insert(trace);
			} catch (BankerTraceException e) {
				continue;
			}
		}
	}
}
