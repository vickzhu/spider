package com.gesangwu.spider.engine.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.service.SecDeptService;
import com.gesangwu.spider.engine.common.LongHuTaskChannelEnum;
import com.gesangwu.spider.engine.util.TradeTimeUtil;

/**
 * 1、从东方财富获得龙虎列表，但东方财富无龙虎类型
 * 2、从网易获取所有龙虎类型
 * 3、根据从东财获得的列表及从网易获取的龙虎类型，抓取龙虎详情
 * <ul>
 * 	<li>东方财富</li>
 *  <li>http://data.eastmoney.com/stock/tradedetail.html</li>
 * </ul>
 * <ul>
 * 	<li>网易</li>
 * 	<li>http://quotes.money.163.com/old/#query=MRLHB&DataType=lhb&sort=TDATE&order=desc&count=150&page=0&$3xa05</li>
 * </ul>
 * @author zhuxb
 *
 */
@Component
public class LongHuTask extends BaseTask {
	
	private static final Logger logger = LoggerFactory.getLogger(LongHuTask.class);

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Resource
	private LongHuSinaTask lhSinaTask;
	@Resource
	private LongHu163Task lh163Task;
	@Resource
	private SecDeptService sdService;
	
	@Scheduled(cron = "0 0/3 16-18 * * MON-FRI")
	public void execute(){
		Date now = new Date();
		if(!isTradeDate(sdf.format(now))){
			logger.info("非交易日！！！");
			return;
		}
		if(!TradeTimeUtil.checkLongHuTime()){
			return;
		}
		execute(null);
	}
	
	public void execute(String tradeDate){
		execute(tradeDate, null);
	}
	
	public void execute(String tradeDate, LongHuTaskChannelEnum channel){
		if(StringUtil.isBlank(tradeDate)){
			Date now = new Date();
			tradeDate = sdf.format(now);
		}
		LongHuTaskTemplate executor = getExecutor(channel);
		executor.execute(tradeDate);
//		updateActiveDept(tradeDate);
	}
	
	/**
	 * 更新活跃营业部
	 * @param tradeDate
	 */
//	private void updateActiveDept(String tradeDate){
//		sdService.clearActiveDept();
//		sdService.updateActiveDept(tradeDate);
//	}
	
	private LongHuTaskTemplate getExecutor(LongHuTaskChannelEnum channel){
		if(LongHuTaskChannelEnum.WANGYI.equals(channel)){
			return lh163Task;
		} else if(LongHuTaskChannelEnum.SINA.equals(channel)){
			return lhSinaTask;
		} else {
			return lh163Task;
		}
	}
	
	public static void main(String[] args){
		LongHuTask task = new LongHuTask();
		task.execute("2016-10-11");
	}
}
