package com.gesangwu.spider.engine.task;

import java.util.List;
import java.util.TreeMap;

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
import com.gesangwu.spider.engine.exception.LaunchDayException;
import com.gesangwu.spider.engine.exception.MilestoneException;
import com.gesangwu.spider.engine.util.LittleCompanyHolder;

/**
 * 主力跟踪统计
 * @author zhuxb
 *
 */
@Component
public class BankerTraceStatisTask {
	
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
			Page<KLine> page = new Page<KLine>(1,15);
			kLineService.selectByPagination(example, page);
			List<KLine> kLineList = page.getRecords();
			if(CollectionUtils.isEmpty(kLineList)){
				continue;
			}
			BankerTrace trace = null;
			for(int i = kLineList.size() - 2; i >= 0; i--){
				KLine kLine = kLineList.get(i);
				if(trace == null){
					try {
						int milestoneScore = milestone(kLine);
						int launchScore = launchDate(kLine);
						trace = new BankerTrace();//成立的情况下才新建对象
					} catch (BankerTraceException e) {//不符合条件，继续判断里程碑日
						e.printStackTrace();
						continue;
					}					
				} else {
					//TODO 碎步吸筹判断
				}
				
			}
			
			if(trace != null){
				traceService.insert(trace);
			}
		}
	}
	
	private int milestone(KLine kl) throws MilestoneException{
		int score = 10;
		TreeMap<Double,Integer> tm = sort(kl);
		int maxMA = tm.lastEntry().getValue();
		if(maxMA == 5){//五日线为当日最高均线
			throw new MilestoneException();
		}
		if(kl.getClose() < kl.getOpen()){//收盘价低于开盘价，当天收绿柱
			throw new MilestoneException();
		}
		if(kl.getPercent()<= 2 || kl.getPercent() >= 8){//当天涨幅需在2%至8%之间
			throw new MilestoneException();
		}
		if(kl.getClose() < kl.getMa5()){//收盘价小于5日线
			throw new MilestoneException();
		}
		if(kl.getClose() < kl.getMa10()){//收盘价小于10日线
			int diff = (int)((kl.getMa10()-kl.getClose())*1000/kl.getClose());
			if(diff >= 10){
				throw new MilestoneException();
			}
			score = score - diff;
		}
		return score;
	}
	
	private TreeMap<Double,Integer> sort(KLine kl){
		TreeMap<Double,Integer> tm = new TreeMap<Double, Integer>();
		tm.put(kl.getMa5(), 5);
		tm.put(kl.getMa5(), 10);
		tm.put(kl.getMa5(), 20);
		tm.put(kl.getMa5(), 30);
		return tm;
	}
	
	/**
	 * 启动日得分
	 * @param kl
	 * @return
	 * @throws LaunchDayException
	 */
	private int launchDate(KLine kl) throws LaunchDayException{
		int cpScore = ldClosePosition(kl);
		int mdScore = ldMaDistri(kl);
		int pScore = ldPercent(kl);
		return cpScore + mdScore + pScore;
	}
	
	/**
	 * 启动日收盘位置判断
	 * @param kl
	 * @return
	 * @throws LaunchDayException
	 */
	private int ldClosePosition(KLine kl) throws LaunchDayException{
		int score = 10;
		TreeMap<Double,Integer> tm = sort(kl);
		if(kl.getClose() <= tm.firstKey()){//收盘价小于所有均线
			return score;
		}
		if(kl.getClose() >= kl.getMa10()){//收盘价高于十日线排除
			throw new LaunchDayException();
		}
		if(kl.getClose() > kl.getMa5()){//收盘价高于5日线
			int diff = (int)((kl.getClose()-kl.getMa5())*1000/kl.getClose());
			if(diff >= 5){
				throw new LaunchDayException();
			}
			score = diff * 2;
		}
		return score;
	}
	
	private int ldMaDistri(KLine kl) throws LaunchDayException{
		int score = 10;
		if(kl.getMa5() <= kl.getMa10()){
			return score;
		}
		int diff = (int)((kl.getMa5()-kl.getMa10())*1000/kl.getMa5());
		if(diff >= 5){
			throw new LaunchDayException();
		}
		score = diff * 2;
		return score;
	}
	
	/**
	 * 涨跌幅判断
	 * @param kl
	 * @return
	 * @throws LaunchDayException
	 */
	private int ldPercent(KLine kl) throws LaunchDayException{
		double chg = Math.abs(kl.getPercent());
		if(chg >= 2){
			throw new LaunchDayException();
		}
		return chg < 1 ? 10 : 5;
	}
}
