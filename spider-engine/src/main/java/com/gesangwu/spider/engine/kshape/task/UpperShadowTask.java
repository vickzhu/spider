package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.CalculateUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.service.UpperShadowService;

/**
 * 实体比上影线长的可以滚蛋了，量不能大于前一天太多，量不能大于前天一天太多，说明尼玛是冲高被套了
 * @author zhuxb
 *
 */
@Component
public class UpperShadowTask extends ShapeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(UpperShadowTask.class);
	
	@Resource
	private UpperShadowService usService;
	@Resource
	private KLineService klService;
	@Resource
	private CompanyService companyService;

	@Scheduled(cron = "0 11 15 * * MON-FRI")
	public void execute(){
		long start = System.currentTimeMillis();
		execute(null);
		long end = System.currentTimeMillis();
		logger.info("Calc upper shadow used:" + (end-start) + "ms!");
	}
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		execute(1, tradeDate, idList);
		if(CollectionUtils.isNotEmpty(idList)){
			klService.updateShape(ShapeEnum.UPPER_SHADOW, idList);
		}
	}
	
	private void execute(int curPage, String tradeDate, List<Long> idList){
		Page<KLine> page = new Page<KLine>(curPage, 500);
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andPercentGreaterThan(-5d);
		criteria.andPercentLessThan(3d);
		criteria.andTradeDateEqualTo(tradeDate);
		klService.selectByPagination(example, page);
		List<KLine> klList = page.getRecords();
		judge(klList, idList);
		int totalPage = page.getTotalPages();
		if(totalPage == curPage){
			return;
		} else {
			curPage++;
			execute(curPage, tradeDate, idList);
		}
	}
	
	private void judge(List<KLine> klList, List<Long> idList){
		for (KLine kl : klList) {			
			boolean isPositive = false;//是否为多头
			if(kl.getMa20() != null){				
				if(kl.getMa5() > kl.getMa10() && kl.getMa10() > kl.getMa20()){//多头
					isPositive = true;
				}
			}
			if(isPositive){
				if(kl.getMa20() != null && kl.getClose() < kl.getMa20()){
					continue;
				}
				if(kl.getMa30() != null){
					if(kl.getClose() < kl.getMa30()){//收盘价小于30日线
						continue;
					}
					if((kl.getClose() - kl.getMa30())/kl.getClose() < 0.05){//收盘价比30日线高度小于5%
						continue;
					}
				}
			}
			List<KLine> tmpList = listByTradeDateDesc(kl.getSymbol(), kl.getTradeDate(), 1);
			if(CollectionUtils.isNotEmpty(tmpList)){
				KLine tmp = tmpList.get(0);
				if(tmp.getPercent() > 5){
					continue;
				}
			}
			double high = kl.getHigh();
//			double second = kl.getOpen() > kl.getClose() ? kl.getOpen() : kl.getClose();
//			if(high - second < Math.abs(kl.getOpen() - kl.getClose())){//上影小于实体
//				continue;
//			}
			double upScale = CalculateUtil.div(high, kl.getClose(), 3);
			double third = kl.getOpen() > kl.getClose() ? kl.getClose() : kl.getOpen();
			double downScale = CalculateUtil.div(third, kl.getLow(), 3);
			double upDiff = CalculateUtil.sub(upScale, 1, 3);
			double downDiff = CalculateUtil.sub(downScale, 1, 3);
			double upDiffMin = isPositive ? 0.05:0.07;//多头5%以上，否则7%以上
			if(upDiff < upDiffMin){
				continue;
			}
			if(downDiff > 0.03){
				continue;
			}			
			idList.add(kl.getId());
		}
	}
	
}
