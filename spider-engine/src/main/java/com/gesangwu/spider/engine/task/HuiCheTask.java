package com.gesangwu.spider.engine.task;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.CalculateUtil;
import com.gesangwu.spider.biz.common.LianBanStatus;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.HuiCheDetail;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineStatis;
import com.gesangwu.spider.biz.dao.model.LianBan;
import com.gesangwu.spider.biz.dao.model.LianBanExample;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.HuiCheDetailService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.service.KLineStatisService;
import com.gesangwu.spider.biz.service.LianBanService;

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
	@Resource
	private HuiCheDetailService hcService;
	@Resource
	private KLineStatisService ksService;
	@Resource
	private LianBanService lbService;

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
		List<LianBan> klList = getKLineList(preTradeDate);
		int hcCount = 0;
		for (LianBan preKl : klList) {
			if(preKl.getSymbol().startsWith("sh688") || (cybStartDate.compareTo(tradeDate) <= 0 && preKl.getSymbol().startsWith("sz30")) || preKl.getSymbol().startsWith("bj")){
				continue;
			}
			KLine kl = klService.selectByDate(preKl.getSymbol(), tradeDate);
			if(kl == null) {
				continue;
			}
			double cj = CalculateUtil.sub(kl.getHigh(), kl.getClose());
			double cjRate = CalculateUtil.div(cj, kl.getHigh(), 4);
//			klService.updateByPrimaryKeySelective(kl);
			if(cjRate < 0.1) {
				continue;
			}
			hcCount++;
			Company company = compnayService.selectBySymbol(kl.getSymbol());
			HuiCheDetail detail = new HuiCheDetail();
			detail.setSymbol(kl.getSymbol());
			if(company != null) {				
				detail.setName(company.getStockName());
			}
			detail.setPercent(kl.getPercent());
			detail.setRate(CalculateUtil.mul(cjRate, 100d));
			detail.setTradeDate(tradeDate);
			hcService.insertSelective(detail);
		}
		KLineStatis ks = ksService.aou(tradeDate);
		ks.setHc(hcCount);
		ksService.updateByPrimaryKeySelective(ks);
	}
	
	private List<LianBan> getKLineList(String tradeDate){
		LianBanExample example = new LianBanExample();
//		KLineExample example = new KLineExample();
		LianBanExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		criteria.andStatusEqualTo(LianBanStatus.ZT.getCode());	
		return lbService.selectByExample(example);
	}
	
}
