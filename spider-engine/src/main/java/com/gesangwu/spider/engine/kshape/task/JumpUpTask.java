package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.CalculateUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;

@Component
public class JumpUpTask extends ShapeTask {

	@Scheduled(cron="0 13 15 * * MON-FRI")
	public void execute(){
		execute(null);
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		calc(1, tradeDate, idList);
		if(CollectionUtils.isNotEmpty(idList)){
			klService.updateShape(ShapeEnum.JUMP_UP, idList);
		}
	}	
	
	public void calc(int curPage, String tradeDate, List<Long> idList){
		Page<KLine> page = new Page<KLine>(curPage, 500);
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		klService.selectByPagination(example, page);
		List<KLine> klList = page.getRecords();
		if(CollectionUtils.isEmpty(klList)){
			return;
		}
		for (KLine kl : klList) {
			double open = kl.getOpen();
			double yest = kl.getYesterdayClose();
			if(open <= yest){
				continue;
			}
			double diff = CalculateUtil.sub(open, yest, 3);
			double scale = CalculateUtil.div(diff, yest, 3);
			if(scale < 0.03 || scale > 0.08){
				continue;
			}
			diff = CalculateUtil.sub( kl.getHigh(), yest, 3);
			scale =  CalculateUtil.div(diff, yest, 3);
			if(scale < 0.09){
				continue;
			}
			idList.add(kl.getId());
		}
		int totalPage = page.getTotalPages();
		if(totalPage == curPage){
			return;
		} else {
			curPage++;
			calc(curPage, tradeDate, idList);
		}
	}

}
