package com.gesangwu.spider.engine.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.util.CalculateUtil;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.LianBanStatus;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.dao.model.LianBan;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.biz.service.LianBanService;

/**
 * 处理连板
 * @author bran
 *
 */
@Component
public class LianBanTask extends BaseTask {
	
	private static final Logger logger = LoggerFactory.getLogger(LianBanTask.class);
	
	@Resource
	private LianBanService lbService;
	@Resource
	private CompanyService compnayService;
	@Resource
	private KLineService klService;
	
//	@Scheduled(cron="0 30 15 * * MON-FRI")
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
		Date date = new Date();
		List<LianBan> lbList = new ArrayList<LianBan>();
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
				double ztPrice = preKl.getClose();
				double todayZtPrice = CalculateUtil.mul(ztPrice, 1.1);
				if(kl.getClose() != todayZtPrice) {
					continue;
				}
			}
			LianBan preLianBan = getPreLianBan(company, tradeDate);
			int days = 1;
			int lbDays = 1;
			Long plate = null;
			String reason = null;
			LianBan lb = new LianBan();
			if(preLianBan != null){
				days += preLianBan.getDays();
				lbDays += preLianBan.getLbDays();
				plate = preLianBan.getPlate();
				reason = preLianBan.getReason();
				String preShape = preLianBan.getShape();
				if(StringUtil.isNotBlank(preShape)) {//说明中间断板过,这里继续更新shape
					
					int curlbDays = 1;//当前连扳天数
					String prelbDays = preShape.substring(preShape.lastIndexOf(SymbolConstant.COMMA) + 1);
					if(StringUtil.isNotBlank(prelbDays)) {
						curlbDays += Integer.valueOf(prelbDays);
					}
					
					StringBuilder sb = new StringBuilder();
					sb.append(preShape.substring(0, preShape.lastIndexOf(SymbolConstant.COMMA) + 1));
					sb.append(curlbDays);
					lb.setShape(sb.toString());
				}
			}
			lb.setDays(days);
			lb.setLbDays(lbDays);
			lb.setGmtCreate(date);
			lb.setPercent(kl.getPercent());
			lb.setStatus(LianBanStatus.ZT.getCode());
			lb.setSymbol(kl.getSymbol());
			lb.setStockName(company.getStockName());
			lb.setTradeDate(tradeDate);
			lb.setPlate(plate);
			lb.setReason(reason);
			lbList.add(lb);
			kl.setShape(ShapeEnum.ZHANG_TING.getCode());
			klService.updateByPrimaryKeySelective(kl);
		}
		if(CollectionUtils.isNotEmpty(lbList)){
			lbService.batchInsert(lbList);
		}
	}
	
	private LianBan getPreLianBan(Company company, String tradeDate){
		String symbol = company.getSymbol();
		String stockName = company.getStockName();
		String preDate1 = getPreTradeDate(tradeDate);
		LianBan preLianBan1 = lbService.selectByTradeDate(symbol, preDate1);
		if(preLianBan1 == null || preLianBan1.getStatus() != LianBanStatus.ZT.getCode()){//前一日非涨停，则计算前第二日
			String preDate2 = getPreTradeDate(preDate1);
			LianBan preLianBan2 = lbService.selectByTradeDate(symbol, preDate2);
			if(preLianBan2 != null && preLianBan2.getStatus() == LianBanStatus.ZT.getCode()){//前第二个交易日为涨停
				
				KLine kl = klService.selectByDate(symbol, preDate1);
				
				LianBan lb = new LianBan();
				lb.setGmtCreate(new Date());
				if(kl != null){
					lb.setPercent(kl.getPercent());
				}
				lb.setSymbol(symbol);
				lb.setStockName(stockName);
				lb.setStatus(LianBanStatus.FZT.getCode());
				lb.setTradeDate(preDate1);
				
				StringBuilder sb = new StringBuilder();
				if(StringUtil.isBlank(preLianBan2.getShape())) {
					sb.append(preLianBan2.getDays());
				} else {
					sb.append(preLianBan2.getShape());
				}
				sb.append(SymbolConstant.COMMA);
				lb.setShape(sb.toString());
				
				lb.setDays(preLianBan2.getDays());
				lb.setLbDays(0);
				lb.setPlate(preLianBan2.getPlate());
				lb.setReason(preLianBan2.getReason());
				lbService.insert(lb);
				return lb;
			}
		} else {
			return preLianBan1;
		}
		return null;
	}
	
	private List<KLine> getKLineList(String tradeDate){
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(tradeDate);
		criteria.andSymbolLike("sh60%");
		criteria.andPercentGreaterThanOrEqualTo(9.7d);
		
		KLineExample.Criteria criteria1 = example.createCriteria();
		criteria1.andTradeDateEqualTo(tradeDate);
		criteria1.andSymbolLike("sz00%");
		criteria1.andPercentGreaterThanOrEqualTo(9.7d);
		example.or(criteria1);
		
		KLineExample.Criteria criteria2 = example.createCriteria();
		criteria2.andTradeDateEqualTo(tradeDate);
		criteria2.andSymbolLike("sz30%");
		if(cybStartDate.compareTo(tradeDate) <= 0) {//创业板20cm新规
			criteria2.andPercentGreaterThanOrEqualTo(19.9d);
		} else {//创业板10cm老规
			criteria2.andPercentGreaterThanOrEqualTo(9.7d);
		}
		example.or(criteria2);
		
		return klService.selectByExample(example);
	}
	
	public static void main (String[] args) {
//		String shape1 = "3,";
//		String shape2 = "4,1";
//		String d1 = shape1.substring(0,shape1.lastIndexOf(SymbolConstant.COMMA)+1);
//		String d2 = shape2.substring(0,shape2.lastIndexOf(SymbolConstant.COMMA)+1);
//		System.out.println(d1);
//		System.out.println(d2);
		System.out.println(CalculateUtil.mul(29.655, 1.1));
	}
	
}
