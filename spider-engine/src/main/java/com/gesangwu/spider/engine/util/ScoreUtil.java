package com.gesangwu.spider.engine.util;

import java.util.TreeMap;

import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.engine.exception.LaunchDayException;
import com.gesangwu.spider.engine.exception.MilestoneException;

public class ScoreUtil {

	/**
	 * 里程碑日计分
	 * @param kl
	 * @return
	 * @throws MilestoneException
	 */
	public static int milestone(KLine kl) throws MilestoneException{
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
	 * 启动日得分
	 * @param kl
	 * @return
	 * @throws LaunchDayException
	 */
	public static int launchDay(KLine kl, Company company) throws LaunchDayException{
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
	private static int ldClosePosition(KLine kl) throws LaunchDayException{
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
	
	private static int ldMaDistri(KLine kl) throws LaunchDayException{
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
	private static int ldPercent(KLine kl) throws LaunchDayException{
		double chg = Math.abs(kl.getPercent());
		if(chg >= 2){
			throw new LaunchDayException();
		}
		return chg < 1 ? 10 : 5;
	}
	
	/**
	 * 注意，其中可能存在均线相同的情况，所以实际可能没有4条数据
	 * @param kl
	 * @return
	 */
	private static TreeMap<Double,Integer> sort(KLine kl){
		TreeMap<Double,Integer> tm = new TreeMap<Double, Integer>();
		tm.put(kl.getMa5(), 5);
		tm.put(kl.getMa10(), 10);
		tm.put(kl.getMa20(), 20);
		tm.put(kl.getMa30(), 30);
		return tm;
	}
}
