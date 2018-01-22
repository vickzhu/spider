package com.gesangwu.spider.engine.kshape.task;

import java.util.ArrayList;
import java.util.List;

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

/**
 * 揉搓线
 * <ol>
 * <li>多头形态，连续两天相对于前一天缩量明显</li>
 * <li>前一天必须是阳线，成交额大于1亿</li>
 * <li>本轮上涨必须有明显的涨幅，如果本轮涨幅只有一天，则涨幅须大于5%。如果本轮涨幅有两天，且第二天为小于2%的缩量上涨，则两条的涨幅必须累计10%以上</li>
 * <li>第一日阴线，第二日真阴线</li>
 * <li>第二日跳空低开</li>
 * <li>收盘价低于五日线</li>
 * </ol>
 * @author zhuxb
 *
 */
@Component
public class RouCuoTask extends ShapeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(RouCuoTask.class);

	@Scheduled(cron="0 12 15 * * MON-FRI")
	public void execute(){
		logger.info("Rou Cuo task begin...");
		long start = System.currentTimeMillis();
		execute(null);
		long end = System.currentTimeMillis();
		logger.info("Rou Cuo task end, used:" + (end-start) + "ms");
	}
	
	private static int pageSize = 500;
	
	public void execute(String tradeDate){
		tradeDate = getTradeDate(tradeDate);
		List<Long> idList = new ArrayList<Long>();
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		criteria.andPercentLessThan(-2d);
		criteria.andPercentGreaterThan(-8d);
		criteria.andMa20IsNotNull();
		int counts = klService.countByExample(example);
		int pages = (counts + pageSize -1) / pageSize;
		for (int i = 1; i <= pages; i++) {			
			execute(i, tradeDate, idList);
		}
		if(CollectionUtils.isNotEmpty(idList)){
			klService.updateShape(ShapeEnum.ROU_CUO, idList);
		}
	}
	
	public void execute(int curPage, String tradeDate, List<Long> idList){
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		criteria.andPercentLessThan(-2d);
		criteria.andPercentGreaterThan(-8d);
		criteria.andMa20IsNotNull();
		Page<KLine> page = new Page<KLine>(curPage, 500);
		klService.selectByPagination(example, page);
		List<KLine> klList = page.getRecords();
		judge(klList, idList);
		int totalPage = page.getTotalPages();
		if(totalPage == curPage){
			return;
		} else {
			curPage++;
		}
	}
	
	public void judge(List<KLine> klList, List<Long> idList){
		for (KLine kl : klList) {
			if(kl.getOpen() < kl.getClose()){//阳线
				continue;
			}
			if(kl.getOpen() >= kl.getYesterdayClose()){//今开大于昨收
				continue;
			}
			List<KLine> preKlList = listByTradeDateDesc(kl.getSymbol(), kl.getTradeDate(), 3);
			KLine pre1 = preKlList.get(0);
			if(pre1.getPercent() > 0){//昨涨
				continue;
			}
			if(pre1.getClose() < pre1.getMa5()){//昨收于五日之下
				continue;
			}
			if(pre1.getOpen() < pre1.getClose()){//昨天阳线
				continue;
			}
			double totalDown = CalculateUtil.add(kl.getPercent(), pre1.getPercent(), 3);
			if(totalDown > -5){//两天跌幅太小
				continue;
			}
			double diff = CalculateUtil.div(pre1.getClose(), kl.getOpen(), 3);
			double scale = CalculateUtil.sub(diff, 1, 3);
			if(scale < 0.01){//低开太少
				continue;
			}
			KLine pre2 = preKlList.get(1);
			if(pre2.getMa20() == null){
				continue;
			}
			if(pre2.getPercent() < 0){//不能跌
				continue;
			}
			if(pre2.getClose() < pre2.getMa5()){//收于五日之上
				continue;
			}
			if(pre2.getOpen() > pre2.getClose()){//收阴
				continue;
			}
			if(kl.getVolume() > pre2.getVolume() || pre1.getVolume() > pre2.getVolume()){
				continue;
			}
			double low = pre2.getOpen();
			KLine pre3 = preKlList.get(2);
			if(pre2.getPercent() < 5){//涨幅过小，判断两天的涨幅
				if(pre3.getPercent() < 5){
					continue;
				}
				if(pre3.getPercent() < pre2.getPercent()){//第一天涨幅必须大于第二天涨幅
					continue;
				}
				if(pre3.getOpen() > pre3.getClose()){//收绿
					continue;
				}
				if(pre2.getVolume() < pre3.getVolume()){//上涨第二天的量要大于上涨第一天的量
					continue;
				}
				low = pre3.getOpen();
			} else {//大于5%的情况
				if(pre3.getPercent() > 3){//这样两天的涨幅有点大
					continue;
				}
			}
			if(kl.getClose() < low){//收盘价比前面大阳线的开盘价要低，说明是下行趋势
				continue;
			}
			idList.add(kl.getId());
		}
	}
	
}
