package com.gesangwu.spider.engine.util;

import java.util.TreeMap;

import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.engine.exception.LaunchDayException;
import com.gesangwu.spider.engine.exception.MilestoneException;
import com.gesangwu.spider.engine.exception.SuiBuException;

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
		TreeMap<Double,Integer> tm = sort(kl);
		if(tm.lastKey() == kl.getMa5()){//五日线为当日最高均线直接排除
			throw new LaunchDayException();
		}
		if(kl.getVolume() * kl.getClose() * 20 > company.getActiveMarketValue() * 10000){//成交量不能超过可流通市值的1/25
			throw new LaunchDayException();
		}
		int stLength = (int)Math.abs((kl.getClose() - kl.getOpen()) * 1000/kl.getClose());
		if(stLength > 15){//实体长度过长
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
			int diff = (int)((kl.getClose()-kl.getMa5()) * 1000 / kl.getClose());
			if(diff >= 5){
				throw new LaunchDayException();
			}
			score = score - diff * 2;
		}
		return score;
	}
	
	/**
	 * 启动日均线分布
	 * @param kl
	 * @return
	 * @throws LaunchDayException
	 */
	private static int ldMaDistri(KLine kl) throws LaunchDayException{
		int score = 10;
		if(kl.getMa5() <= kl.getMa10()){
			return score;
		}
		int diff = (int)((kl.getMa5()-kl.getMa10()) * 1000/kl.getMa5());
		if(diff > 10){//XXX
			throw new LaunchDayException();
		}
		score = score - diff;
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
	
	/**
	 * 碎步收盘位置
	 * @return
	 */
	public static int sbClosePosition(KLine kLine) throws SuiBuException{
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
	public static int sbPercent(KLine kLine) throws SuiBuException{
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
}
