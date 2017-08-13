package com.gesangwu.spider.engine.kshape.task;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.biz.common.ShapeEnum;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.dao.model.KLineExample;
import com.gesangwu.spider.biz.service.KLineService;
import com.gesangwu.spider.engine.util.SpiderMailSender;

/**
 * 用于计算底部形态的票在早上10点之前的量是否超过前一天一半以上
 * @author zhuxb
 * url:http://vip.stock.finance.sina.com.cn/quotes_service/view/vML_DataList.php?desc=j&symbol=sh600706&num=31
 *
 */
@Component
public class VolumeCalTask extends ShapeTask {
	
	@Resource
	private KLineService klService;
	@Resource
	private SpiderMailSender sender;

	@Scheduled(cron="0 35,40,45,50,55,59 9 * * MON-FRI")
	public void execute(){
		String latestDate = klService.selectLatestDate();
		KLineExample example = new KLineExample();
		KLineExample.Criteria criteria = example.createCriteria();
		criteria.andTradeDateEqualTo(latestDate);
		criteria.andShapeEqualTo(ShapeEnum.DI_BU.getCode());
		List<KLine> klList = klService.selectByExample(example);
		StringBuilder sb = new StringBuilder();
		for (KLine kl : klList) {
			int totalVolume = getTotalVolume(kl.getSymbol());
			if(totalVolume * 2 > kl.getVolume()){
				sb.append(kl.getSymbol());
				sb.append(SymbolConstant.COMMA);
			}
		}
		if(sb.length() > 0){
			sender.send(sb.toString());
		}
	}
	
	private static final String r = ", '([0-9]*)'\\]";
	private static final Pattern p = Pattern.compile(r);

	private static int getTotalVolume(String symbol){
		String url = buildUrl(symbol);
		String result = HttpTool.get(url);
		Matcher m = p.matcher(result);
		int total = 0;
		while(m.find()){
			total += Integer.valueOf(m.group(1));
		}
		return total;
	}
	
	private static String buildUrl(String symbol){
		StringBuilder sb = new StringBuilder();
		sb.append("http://vip.stock.finance.sina.com.cn/quotes_service/view/vML_DataList.php?desc=j&num=36&symbol=");
		sb.append(symbol);
		return sb.toString();
	}
	
	public static void main(String[] args){
		getTotalVolume("sh600706");
	}
	
}
