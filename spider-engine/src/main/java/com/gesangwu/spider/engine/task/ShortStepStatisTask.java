package com.gesangwu.spider.engine.task;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.BankerTrace;
import com.gesangwu.spider.biz.dao.model.BankerTraceExample;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.BankerTraceService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.engine.common.TraceStepEnum;
import com.gesangwu.spider.engine.exception.SuiBuException;
import com.gesangwu.spider.engine.util.ScoreUtil;

/**
 * 碎步统计，每天下午3:30执行碎步统计
 * @author zhuxb
 *
 */
@Component
public class ShortStepStatisTask {
	
	@Resource
	private BankerTraceService traceService;
	@Resource
	private KLineService kLineService;

//	@Scheduled(cron = "0 45 15 * * MON-FRI")
	public void execute(){
		BankerTraceExample example = new BankerTraceExample();
		BankerTraceExample.Criteria criteria = example.createCriteria();
		criteria.andStepLessThan(TraceStepEnum.SURE.getCode());
		List<BankerTrace> traceList = traceService.selectByExample(example);
		for (BankerTrace bankerTrace : traceList) {
			KLineExample klExample = new KLineExample();
			KLineExample.Criteria klC = klExample.createCriteria();
			klC.andSymbolEqualTo(bankerTrace.getSymbol());
			klC.andTradeDateGreaterThanOrEqualTo(bankerTrace.getLaunchDate());
			Page<KLine> page = new Page<KLine>(1,8);
			kLineService.selectByPagination(klExample, page);
			List<KLine> kLineList = page.getRecords();
			if(CollectionUtils.isEmpty(kLineList)){
				continue;
			}
			KLine launchKLine = kLineList.get(0);
			KLine msKLine = kLineList.get(1);
			int size = kLineList.size();
			int highVol = 0;
			int suibuScore = 0;
			double percent = msKLine.getPercent();
			long launchVol = launchKLine.getVolume();
			try {
				for(int i = 2; i < size; i++){
					KLine kLine = kLineList.get(i);
					if(kLine.getVolume() > launchKLine.getVolume() * 7){//大于7倍直接结束
						throw new SuiBuException();
					} else if(kLine.getVolume() > launchKLine.getVolume() * 5){//成交量大于启动日5倍的
						highVol++;
					}
					if(highVol > 2){//碎步出现两次超大交易量则直接不满足
						throw new SuiBuException();
					}
					long vol_2 = kLine.getVolume() + kLineList.get(i-1).getVolume();
					if(vol_2 >= launchVol * 4 * 2){//相邻两天的平均成交量大于启动日的4倍
						throw new SuiBuException();
					}
					percent += kLine.getPercent();
					if(percent <= 0 || percent >= 21){//碎步累计涨跌幅过大或过小
						throw new SuiBuException();
					}
					int sbClosePosiScore = ScoreUtil.sbClosePosition(kLine);
					int sbPercentScore = ScoreUtil.sbPercent(kLine);
					double syDiff = 0;
					if(kLine.getClose() > kLine.getOpen()){//红柱
						syDiff = kLine.getHigh() - kLine.getClose();
					} else {//绿柱
						 syDiff = kLine.getHigh() - kLine.getOpen();
					}
					int syPercent = (int)(syDiff * 1000 / kLine.getClose());
					if(syPercent >= 60 ) {//上影线长度超过了6‰
						throw new SuiBuException();
					}
					int score = sbClosePosiScore + sbPercentScore;
					suibuScore += score;
				}
				bankerTrace.setScores(bankerTrace.getMsScore() + suibuScore);
				if(size == 8){
					if(percent <= 5){//碎步累计涨跌幅小于预计5%
						throw new SuiBuException();
					}
					bankerTrace.setStep(TraceStepEnum.SURE.getCode());
				} else {
					bankerTrace.setStep(TraceStepEnum.SUIBU.getCode());
				}
			} catch (SuiBuException e) {//不符合条件直接结束
				bankerTrace.setStep(TraceStepEnum.CANCEL.getCode());
			}
			bankerTrace.setGmtUpdate(new Date());
			traceService.updateByPrimaryKey(bankerTrace);
		}
	}	
}
