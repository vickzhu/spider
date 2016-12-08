package com.gesangwu.spider.engine.task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;

/**
 * 1"symbol",
 * 2"code",
 * 3"name",
 * 4"trade",当前价格
 * 5"pricechange",涨跌额
 * 6"changepercent",涨跌幅
 * 7"buy",买1
 * 8"sell",卖1
 * 9"settlement",昨收
 * 10"open",今开
 * 11"high",最高
 * 12"low",最低
 * 13"volume",成交量
 * 14"amount",成交额
 * 15"ticktime",成交时间
 * 16"per",
 * 17"per_d",
 * 18"nta",
 * 19"pb",市净率
 * 20"mktcap",总市值
 * 21"nmc",流通市值
 * 22"turnoverratio",换手率
 * 23"favor",
 * 24"guba"
 * 公司信息更新
 * @author zhuxb
 *
 */
@Component
public class KLineSinaTask {
	
	private static final Logger logger = LoggerFactory.getLogger(KLineSinaTask.class);
	
	private static final String r1 = "\"day\"\\:\"([0-9\\-]*)\",\"count\"\\:([0-9]*).*\"items\"\\:\\[\\[(.*)\\]\\]\\}\\]\\)";
	private static final String r2 = "\"items\"\\:\\[\\[(.*)\\]\\]\\}\\]\\)";
	private static final Pattern p1 = Pattern.compile(r1);
	private static final Pattern p2 = Pattern.compile(r2);
	private static int cpp = 80;
	
	@Resource
	private CompanyService companyService;
	@Resource
	private KLineService kLineService;

	@Scheduled(cron="0 05 15 * * ?")
	public void execute(){
		long start = System.currentTimeMillis();
		
		String result = HttpTool.get(buildUrl(1));
		Matcher matcher = p1.matcher(result);
		if(!matcher.find()){
			return;
		}
		String totalCounts = matcher.group(2);
		int pages = (Integer.valueOf(totalCounts)+cpp-1)/cpp;
		String detailList = matcher.group(3);
		save(detailList);
		for(int curPage = 2; curPage <= pages; curPage++){
			result = HttpTool.get(buildUrl(curPage));
			matcher = p2.matcher(result);
			if(!matcher.find()){
				continue;
			}
			detailList = matcher.group(1);
			save(detailList);
		}	
		long end = System.currentTimeMillis();
		logger.info("Fetch KLine from sina used:" + (end-start)+"ms!");
	}
	
	private String buildUrl(int curPage){
		StringBuilder sb = new StringBuilder();
		sb.append("http://money.finance.sina.com.cn/d/api/openapi_proxy.php/?__s=[[%22hq%22,%22hs_a%22,%22%22,0,");
		sb.append(curPage);
		sb.append(SymbolConstant.COMMA);
		sb.append(cpp);
		sb.append("]]&callback=FDC_DC.theTableData");
		return sb.toString();
	}
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * save or update
	 * @param detailList
	 */
	private void save(String detailList){
		detailList = detailList.replaceAll("\"", "");
		String[] details = detailList.split("\\],\\[");
		Date now = new Date();
		String tradeDate = sdf.format(now);
		List<KLine> kLineList = new ArrayList<KLine>();
		for(String detail : details) {
			KLine kLine = new KLine();
			String[] columns = detail.split(SymbolConstant.COMMA);
			String symbol = columns[0];
			String yesterdayClose = columns[8];//昨收
			String volume = columns[12];
			String amt = columns[13];//成交额
			String open = columns[9];
			String close = columns[3];
			String high = columns[10];
			String low = columns[11];
			String chgAmt = columns[4];
			String chgPct = columns[5];
			String turnrate = columns[21];
			
			kLine.setSymbol(symbol);
			kLine.setYesterdayClose(Double.valueOf(yesterdayClose));
			kLine.setVolume(Long.valueOf(volume));
			kLine.setAmount(Double.valueOf(amt));
			kLine.setOpen(Double.valueOf(open));
			kLine.setClose(Double.valueOf(close));
			kLine.setHigh(Double.valueOf(high));
			kLine.setLow(Double.valueOf(low));
			kLine.setChangeAmount(Double.valueOf(chgAmt));
			kLine.setPercent(Double.valueOf(chgPct));
			kLine.setTurnrate(Double.valueOf(turnrate));
			kLine.setTradeDate(tradeDate);
			kLine.setGmtCreate(now);
			
			kLineList.add(kLine);
		}
		kLineService.batchInsert(kLineList);
	}
}
