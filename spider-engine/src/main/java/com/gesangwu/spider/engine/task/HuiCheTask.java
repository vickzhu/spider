package com.gesangwu.spider.engine.task;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.CalculateUtil;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;

/**
 * 回撤
 * @author Gandalf
 *
 */
@Component
public class HuiCheTask extends BaseTask {
	
	private static final Logger logger = LoggerFactory.getLogger(HuiCheTask.class);

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
		String preTradeDate = getPreTradeDate(tradeDate);
		List<KLine> klList = getKLineList(preTradeDate);
		for (KLine preKl : klList) {
			if(preKl.getSymbol().startsWith("sh688") || (cybStartDate.compareTo(tradeDate) <= 0 && preKl.getSymbol().startsWith("sz30")) || preKl.getSymbol().startsWith("bj")){
				continue;
			}
			KLine kl = klService.selectByDate(preKl.getSymbol(), tradeDate);
			double cj = CalculateUtil.sub(kl.getHigh(), kl.getClose());
			double cjRate = CalculateUtil.div(cj, kl.getHigh(), 4);
//			klService.updateByPrimaryKeySelective(kl);
			if(cjRate >= 0.1) {				
				System.out.println(kl.getSymbol() + " : " + cjRate);
			}
		}
	}
	
	private List<KLine> getKLineList(String tradeDate){
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		criteria.andShapeEqualTo(ShapeEnum.ZHANG_TING.getCode());		
		return klService.selectByExample(example);
	}
	
}
