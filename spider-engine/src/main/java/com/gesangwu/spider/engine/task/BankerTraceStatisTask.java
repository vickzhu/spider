package com.gesangwu.spider.engine.task;

import java.util.Date;
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
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.engine.exception.BankerTraceException;
import com.gesangwu.spider.engine.exception.LaunchDayException;
import com.gesangwu.spider.engine.exception.MilestoneException;
import com.gesangwu.spider.engine.exception.SuiBuException;
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
	@Resource
	private CompanyService companyService;
	
	private static final int suiBuDays = 6;

	public void execute(){
		List<Company> companyList = LittleCompanyHolder.getCompanyList();
		companyList.clear();
		Company c = companyService.selectBySymbol("sz000916");
		companyList.add(c);
		for (Company company : companyList) {
			KLineExample example = new KLineExample();
			KLineExample.Criteria criteria = example.createCriteria();
			criteria.andSymbolEqualTo(company.getSymbol());
			example.setOrderByClause("trans_date desc");
			Page<KLine> page = new Page<KLine>(1,4);
			kLineService.selectByPagination(example, page);
			List<KLine> kLineList = page.getRecords();
			if(CollectionUtils.isEmpty(kLineList)){
				continue;
			}
			BankerTrace trace = null;
			int suibu = 0;//碎步计数器
			int highVol = 0;//成交量大于启动日4倍则加1
			long ldVol = 0;//启动日成交量
			for(int i = kLineList.size() - 2; i >= 0; i--){
				KLine kLine = kLineList.get(i);
				try {
					int milestoneScore = milestone(kLine);
					KLine preKLine = kLineList.get(i+1);
					if(kLine.getVolume() <= preKLine.getVolume()){//里程碑日成交量需大于启动日
						throw new MilestoneException();
					}
					int launchScore = launchDate(preKLine, company);
					trace = new BankerTrace();//成立的情况下才新建对象
					trace.setGmtCreate(new Date());
					trace.setMsDate(kLine.getTransDate());
					trace.setScores(milestoneScore + launchScore);
					trace.setStep(1);
					trace.setSymbol(kLine.getSymbol());
					//因为有可能启动日有覆盖的情况，所以需要重置下列参数
					suibu = 0;//碎步计数器
					highVol = 0;//成交量大于启动日4倍则加1
					ldVol = preKLine.getVolume();//启动日成交量，这个值只有生成BankerTrace对象是才赋值
				} catch (BankerTraceException e) {//不符合条件，继续判断里程碑日
					//这不做任何处理？
				}					
				if(trace != null){
					suibu++;
					if(suibu > suiBuDays){//只判断6天内的碎步吸筹
						break;
					}
					try {
						if(kLine.getVolume() > ldVol * 5){//成交量大于启动日5倍的
							highVol++;
						}
						if(highVol > 2){//碎步出现两次超大交易量则直接不满足
							throw new SuiBuException();
						}
						int sbClosePosiScore = sbClosePosition(kLine);
						int sbPercentScore = sbPercent(kLine);
						int suibuScore = sbClosePosiScore + sbPercentScore;
						trace.setScores(trace.getScores()+suibuScore);
						trace.setStep(suibu == suiBuDays?3:2);
						trace.setGmtUpdate(new Date());
					} catch (SuiBuException e) {//不满足碎步条件，继续判断里程碑
						ldVol = 0;
						highVol = 0;
						suibu = 0;
						trace = null;
						continue;
					}
				}
			}
			if(trace != null){
				traceService.insert(trace);
			}
		}
	}
	/**
	 * 碎步收盘位置
	 * @return
	 */
	private int sbClosePosition(KLine kLine) throws SuiBuException{
		if(kLine.getClose() < kLine.getMa5() && kLine.getClose() < kLine.getMa10()){//收盘位置不符合碎步条件
			throw new SuiBuException();
		}
		return kLine.getClose() > kLine.getMa5()?5:3;
	}
	
	/**
	 * 碎步涨跌幅得分
	 * @param kLine
	 * @return
	 * @throws SuiBuException
	 */
	private int sbPercent(KLine kLine) throws SuiBuException{
		if(kLine.getPercent() <= -3 || kLine.getPercent() >= 7){//涨跌幅不符合碎步条件，重新查找里程碑
			throw new SuiBuException();
		}
		if(kLine.getPercent() >= 0){//涨的情况
			if(kLine.getPercent() < 1){
				return 5;
			} else if(kLine.getPercent() < 4){
				return 3;
			} else {
				return 1;
			}
		} else {//跌的情况
			if(kLine.getPercent() > -1){
				return 5;
			} else if (kLine.getPercent() > -2){
				return 3;
			} else {
				return 1;
			}
		}
	}
	
	private int milestone(KLine kl) throws MilestoneException{
		int score = 10;
		TreeMap<Double,Integer> tm = sort(kl);
		double maxMA = tm.lastKey();
		if(maxMA == kl.getMa5() || maxMA == kl.getMa10()){//五日线或十日线为当日最高均线
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
	
	/**
	 * 注意，其中可能存在均线相同的情况，所以实际可能没有4条数据
	 * @param kl
	 * @return
	 */
	private TreeMap<Double,Integer> sort(KLine kl){
		TreeMap<Double,Integer> tm = new TreeMap<Double, Integer>();
		tm.put(kl.getMa5(), 5);
		tm.put(kl.getMa10(), 10);
		tm.put(kl.getMa20(), 20);
		tm.put(kl.getMa30(), 30);
		return tm;
	}
	
	/**
	 * 启动日得分
	 * @param kl
	 * @return
	 * @throws LaunchDayException
	 */
	private int launchDate(KLine kl, Company company) throws LaunchDayException{
		if(kl.getVolume() * kl.getClose() * 25 > company.getActiveMarketValue() * 10000){//成交量不能超过可流通市值的1/30
			throw new LaunchDayException();
		}
		int stLength = (int)Math.abs((kl.getClose() - kl.getOpen())*100/kl.getClose());
		if(stLength >= 2){//实体长度过长
			throw new LaunchDayException();
		}
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
