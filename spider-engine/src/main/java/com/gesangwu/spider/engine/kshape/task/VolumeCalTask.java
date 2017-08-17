package com.gesangwu.spider.engine.kshape.task;

import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.util.CalculateUtil;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.engine.util.AmbushBottomHolder;
import com.gesangwu.spider.engine.util.SpiderMailSender;

/**
 * 用于计算底部形态的票在早上10点之前的量是否超过前一天一半以上
 * @author zhuxb
 * url:http://vip.stock.finance.sina.com.cn/quotes_service/view/vML_DataList.php?desc=j&symbol=sh600706&num=31
 *
 */
@Component
public class VolumeCalTask extends ShapeTask {
	
	private static final Logger logger = LoggerFactory.getLogger(VolumeCalTask.class);
	
	@Resource
	private KLineService klService;
	@Resource
	private SpiderMailSender sender;
	
	private static Set<String> symbolSet = new HashSet<String>();

	@Scheduled(cron="0 0/5 9,10 * * MON-FRI")
	public void execute(){
		Calendar c = Calendar.getInstance();
		int hod = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		if(hod == 9 && min <= 30){//<=9:30
			symbolSet.clear();
			return;
		}
		if(hod == 10 && min > 30){//>10:05
			return;
		}
		logger.info("begin calc volume...");
		long start = System.currentTimeMillis();
		String preDate = klService.selectLatestDate();
		String result = execute(preDate);
		long end = System.currentTimeMillis();
		logger.info("Calc volume used:"+(end - start)/1000);
		if(StringUtil.isNotBlank(result)){
			sender.send(result);
		}
	}
	
	public String execute(String preDate){
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(preDate);
		criteria.andShapeEqualTo(ShapeEnum.DI_BU.getCode());
		List<KLine> klList = klService.selectByExample(example);
		AmbushBottomHolder.set(klList);
		StringBuilder sb = new StringBuilder();
		for (KLine kl : klList) {
			sb.append(kl.getSymbol());
			sb.append(SymbolConstant.COMMA);			
		}
		return calc(sb.toString());
	}
	
	private static final String regex = "var hq_str_([\\w]{8})=\"(.*)\";";
	private static final Pattern r = Pattern.compile(regex);
	
	private static String calc(String symbolArr){
		StringBuilder sb = new StringBuilder();
		String result = HttpTool.get("http://hq.sinajs.cn/?list=" + symbolArr,Charset.forName("GBK"));
		Matcher matcher = r.matcher(result);
		while(matcher.find()){
			String symbol = matcher.group(1);
			String detail = matcher.group(2);
			String[] details = detail.split(SymbolConstant.COMMA);
			if(details.length < 30){
				continue;
			}
			String name = details[0];//名字
			double open = Double.valueOf(details[1]);//今开
			double yc = Double.valueOf(details[2]);//昨收
			double cp = Double.valueOf(details[3]);//现价
			//4最高 5最低 6 买一 7 卖一
			long volume = Long.valueOf(details[8]);//成交量
			//9成交额
			KLine kl = AmbushBottomHolder.get(symbol);
			if(kl == null){
				continue;
			}
			if(cp < open){//阴实体
				continue;
			}
			if(cp < yc){//绿盘
				continue;
			}
			double op = CalculateUtil.div((open - yc), yc, 3);
			if(op > 0.03){//开幅太大
				continue;
			}
			if(kl.getVolume() > volume * 2){
				continue;
			}
			if(!symbolSet.contains(symbol)){
				symbolSet.add(symbol);
				sb.append(name);
				sb.append(SymbolConstant.H_LINE);
				sb.append(symbol);
				sb.append(SymbolConstant.COMMA);
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		String result = calc("sh601336");
		System.out.println(result);
	}
	
}
