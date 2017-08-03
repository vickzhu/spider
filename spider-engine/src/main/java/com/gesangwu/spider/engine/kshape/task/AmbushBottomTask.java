package com.gesangwu.spider.engine.kshape.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.KLineService;

/**
 * 底部埋伏
 * @author zhuxb
 *
 */
@Component
public class AmbushBottomTask {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	private KLineService klService;

	public void execute(){
		execute(null);
	}
	
	public void execute(String tradeDate){
		if(StringUtil.isBlank(tradeDate)){
			Date now = new Date();
			tradeDate = sdf.format(now);
		}
		Page<KLine> page = new Page<KLine>(1, 100);
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andPercentGreaterThan(0d);
		criteria.andTradeDateEqualTo(tradeDate);
		klService.selectByPagination(example, page);
		int totalPages = page.getTotalPages();
		List<KLine> klList = page.getRecords();
		execute(tradeDate, klList);
		
		for(int i = 2; i <= totalPages; i++){
			page = new Page<KLine>(i, 100);
			klService.selectByPagination(example, page);
			klList = page.getRecords();
			execute(tradeDate, klList);
		}
	}
	
	public void execute(String tradeDate, List<KLine> klList){
		for (KLine kl : klList) {
			execute(tradeDate, kl.getSymbol());
		}
	}
	
	public void execute(String tradeDate, String symbol){
		KLineExample example = new KLineExample();
		example.setOrderByClause("trade_date desc");
		example.setOffset(0);
		example.setRows(20);
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andSymbolEqualTo(symbol);
		criteria.andTradeDateLessThanOrEqualTo(tradeDate);
		List<KLine> klList = klService.selectByExample(example);
		boolean isMs = false;
		boolean isBreak = false;
		for (int i = klList.size()-1; i >=0; i--) {
			KLine kl = klList.get(i);
			if(kl.getMa5() == null || kl.getMa10() == null || kl.getMa20() == null){
				continue;
			}
			if(kl.getPercent() < -5 && kl.getMa5() < kl.getMa10() && kl.getMa5()< kl.getMa20()){
				isMs = true;
				continue;
			}
			if(!isMs){//有里程碑才往下继续走
				continue;
			}
			if(Math.abs(kl.getPercent()) > 3){//有里程碑，并且单日涨跌幅大于3，直接不玩了
				isMs = false;
				isBreak = false;
				continue;
			}
			if(kl.getMa5() > kl.getMa10()){
				isBreak = true;
			} else {
				isBreak = false;
			}
		}
		if(isMs && isBreak){
			System.out.println(symbol + ":" + tradeDate);
		}
	}
	
	
}
