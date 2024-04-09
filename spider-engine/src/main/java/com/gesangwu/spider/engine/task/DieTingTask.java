package com.gesangwu.spider.engine.task;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.CalculateUtil;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;

@Component
public class DieTingTask extends BaseTask {
	
	private static final Logger logger = LoggerFactory.getLogger(DieTingTask.class);

	@Resource
	private KLineService klService;
	@Resource
	private CompanyService compnayService;

	public void execute(){
		Date now = new Date();
		if(!isTradeDate(sdf.format(now))){
			logger.error("非交易日！！！");
			return;
		}
		execute(null);
	}
	
	public void execute(String tradeDate){
		try {			
			tradeDate = getTradeDate(tradeDate);
		} catch (Exception e) {
			logger.info("交易日异常：" + tradeDate, e.getMessage());
			return;
		}
		List<KLine> klList = getKLineList(tradeDate);
		String preTradeDate = getPreTradeDate(tradeDate);
		for (KLine kl : klList) {
			if(kl.getSymbol().startsWith("sh688") || (cybStartDate.compareTo(tradeDate) <= 0 && kl.getSymbol().startsWith("sz30")) || kl.getSymbol().startsWith("bj")){
				continue;
			}
			Company company = compnayService.selectBySymbol(kl.getSymbol());
			if(company == null){
				continue;
			}
			KLine preKl = klService.selectByDate(kl.getSymbol(), preTradeDate);
			if(preKl != null) {
				double prePrice = preKl.getClose();
				double todayDtPrice = CalculateUtil.mul(prePrice, 0.9);
				if(kl.getClose() != todayDtPrice) {
					continue;
				}
			}
			kl.setShape(ShapeEnum.DIE_TING.getCode());
			klService.updateByPrimaryKeySelective(kl);
		}
	}
	
	private List<KLine> getKLineList(String tradeDate){
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		criteria.andSymbolLike("sh60%");
		criteria.andPercentLessThanOrEqualTo(-9.7d);
		
		KLineExample.Criteria criteria1 = example.createCriteria();
		criteria1.andTradeDateEqualTo(tradeDate);
		criteria1.andSymbolLike("sz00%");
		criteria1.andPercentLessThanOrEqualTo(-9.7d);
		example.or(criteria1);
		
		KLineExample.Criteria criteria2 = example.createCriteria();
		criteria2.andTradeDateEqualTo(tradeDate);
		criteria2.andSymbolLike("sz30%");
		if(cybStartDate.compareTo(tradeDate) <= 0) {//创业板20cm新规
			criteria2.andPercentLessThanOrEqualTo(-19.9d);
		} else {//创业板10cm老规
			criteria2.andPercentLessThanOrEqualTo(-9.7d);
		}
		example.or(criteria2);
		
		return klService.selectByExample(example);
	}
	
}
