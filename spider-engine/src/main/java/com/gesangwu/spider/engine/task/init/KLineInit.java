package com.gesangwu.spider.engine.task.init;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.CompanyExample;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;

/**
 * 由于新浪数据部分缺失，所以需要雪球的数据来做修复
 * url:https://xueqiu.com/stock/forchartk/stocklist.json
 * ?symbol=SH600526&period=1d
 * ay&type=before&begin=1437148330951&end=1468684330951&_=1468684330951
 * 
 * @author zhuxb
 *
 */
@Component
public class KLineInit {

	private static final String r1 = "\"item\"\\:\\[\\[(.*)\\]\\]\\}";
	private static Pattern p1 = Pattern.compile(r1);
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

	@Resource
	private CompanyService companyService;
	@Resource
	private KLineService kLineService;

	public void execute() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		long start = c.getTimeInMillis();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		long end = c.getTimeInMillis();
		execute(start, end);
	}

	public void execute(long start, long end) {
		String cookieUrl = "https://xueqiu.com/account/lostpasswd";
		HttpTool.get(cookieUrl);// 这个链接只是为了获得cookie信息，因为后面的请求需要用到cookie
		//1423
		CompanyExample example = new CompanyExample();
		CompanyExample.Criteria criteria = example.createCriteria();
		criteria.andIdGreaterThan(1242l);
		List<Company> companyList = companyService.selectByExample(example);
		Date now = new Date();
		for (Company company : companyList) {
			String symbol = company.getSymbol();
			System.out.println(symbol);
			String url = buildUrl(symbol, start, end);
			String result = HttpTool.get(url);
			Matcher m = p1.matcher(result);
			if (m.find()) {
				String detailList = m.group(1);
				String[] details = detailList.split("\\],\\[");
				List<KLine> kLineList = new ArrayList<KLine>();
				for (String detail : details) {
					String[] columns = detail.split(SymbolConstant.COMMA);
					// "column":["timestamp","volume","open","high","low","close","chg","percent","turnoverrate","amount","volume_post","amount_post"]
					KLine kLine = new KLine();

					String volume = columns[1];
					String amt = columns[9];// 成交额
					String open = columns[2];
					String close = columns[5];
					String high = columns[3];
					String low = columns[4];
					String chgAmt = columns[6];
					String chgPct = columns[7];
					String turnrate = columns[8];

					long vol = Long.valueOf(volume);
					double openPrice = Double.valueOf(open);
					if (vol == 0 && openPrice == 0) {
						continue;
					}
					
					double amount = 0;
					if(StringUtils.isNotBlank(amt) && !"null".equals(amt)){
						amount = Double.valueOf(amt);
					}
					double dClose = Double.valueOf(close);
					double percent = Double.valueOf(chgPct);
					kLine.setSymbol(symbol);
					kLine.setVolume(vol);
					kLine.setAmount(amount);
					kLine.setOpen(openPrice);
					kLine.setClose(dClose);
					kLine.setHigh(Double.valueOf(high));
					kLine.setLow(Double.valueOf(low));
					kLine.setChangeAmount(Double.valueOf(chgAmt));
					kLine.setPercent(percent);
					kLine.setTurnrate(Double.valueOf(turnrate));
					Date tradeDate = new Date(Long.valueOf(columns[0]));
					kLine.setTradeDate(sdf2.format(tradeDate));
					kLine.setGmtCreate(now);
					kLineList.add(kLine);
				}
				if (CollectionUtils.isNotEmpty(kLineList)) {
					kLineService.batchInsert(kLineList);
				}
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("End.....");
		}
	}

	private String buildUrl(String symbol, long start, long end) {
		StringBuffer sb = new StringBuffer();
		sb.append("https://stock.xueqiu.com/v5/stock/chart/kline.json?symbol=");
		sb.append(symbol.toUpperCase());
		sb.append("&period=day&type=before&begin=");
		sb.append(start);
		sb.append("&indicator=kline");
		sb.append("&count=");
		sb.append(-150);//往前多少天的数据
		return sb.toString();
	}
}
