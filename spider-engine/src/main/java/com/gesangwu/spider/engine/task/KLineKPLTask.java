package com.gesangwu.spider.engine.task;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.KLine;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.KLineService;

@Component
public class KLineKPLTask extends BaseTask{
	
	private static final Logger logger = LoggerFactory.getLogger(KLineKPLTask.class);

	@Resource
	private CompanyService companyService;
	@Resource
	private KLineService kLineService;
	
	//{"Code":"603212","Name":"N\u8d5b\u4f0d","Rate":43.98,"Price":15.06,"CJE":4103609,"Ratio":0.68,"Speed":0,"SJLTP":602550600,"Tude":"","Buy":4103607,"Sell":0,"ZLJE":4103607,"QJZF":43.98,"Tag":""}
	private static final String r1 = "\\{\"volume\"\\:([0-9]*),\"open\"\\:([0-9\\.]*),\"high\"\\:([0-9\\.]*),\"close\"\\:([0-9\\.]*),\"low\"\\:([0-9\\.]*),\"chg\"\\:(\\-?[0-9\\.]*),\"percent\"\\:(\\-?[0-9\\.]*),\"turnrate\"\\:([0-9\\.]*),\"ma5\"\\:([0-9\\.]*),\"ma10\"\\:([0-9\\.]*),\"ma20\"\\:([0-9\\.]*),\"ma30\"\\:([0-9\\.]*),\"dif\"\\:\\-?[0-9\\.]*,\"dea\"\\:\\-?[0-9\\.]*,\"macd\"\\:\\-?[0-9\\.]*,\"lot_volume\"\\:[0-9]*,\"timestamp\"\\:[0-9]*,\"time\"\\:\"([^\"]*)\"\\}";
	private static final Pattern p1 = Pattern.compile(r1);
	
	/**
	 * 
	 * @param tradeDate	交易日起
	 * @param userId 开盘啦的用户ID
	 * @param token	开盘啦的登录token
	 * 
	 */
	public void execute(String tradeDate, String userId, String token){
		try {			
			tradeDate = getTradeDate(tradeDate);
		} catch (Exception e) {
			logger.info("交易日异常：" + tradeDate, e.getMessage());
			return;
		}
		while (true){
			String result = fetchResult(tradeDate, userId, token);
			
			ObjectMapper mapper=new ObjectMapper();
			try {
				Map map = mapper.readValue(result, Map.class);
				List list = (List)map.get("list");
				for (Object obj : list) {
					Map<String, String> detailMap = (HashMap)obj;
					
				}
				System.out.println("");
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * {Code=688268, Name=华特气体, Rate=16.36, Price=62.6, CJE=240056491, Ratio=14.92, Speed=0.288369, SJLTP=1704416961, Tude=光刻胶、集成电路概念、新股与次新股、融资融券、广东其它、化学制品, Buy=39366521, Sell=-9985305, ZLJE=29381216, QJZF=13.59, Tag=}
	 * @return
	 */
	private KLine toKLine(Map<String, String> detailMap){
		String code = detailMap.get("Code");
		String symbol = StockUtil.code2Symbol(code);
		String name = detailMap.get("Name");
		double percent = Double.valueOf(detailMap.get("Rate"));
		double turnrate = Double.valueOf(detailMap.get("Ratio"));
		double close = Double.valueOf(detailMap.get("Price"));
		
		KLine kLine = new KLine();
//		kLine.setSymbol(symbol);
//		kLine.setYesterdayClose(Double.valueOf(yesterdayClose));
//		kLine.setVolume(vol);
//		kLine.setAmount(amount);
//		kLine.setOpen(openPrice);
//		kLine.setClose(dClose);
//		kLine.setHigh(Double.valueOf(high));
//		kLine.setLow(Double.valueOf(low));
//		kLine.setChangeAmount(Double.valueOf(chgAmt));
//		kLine.setPercent(percent);
//		kLine.setTurnrate(Double.valueOf(turnrate));
//		kLine.setTradeDate(tradeDate);
//		kLine.setGmtCreate(now);
		return kLine;
	}
	
	private String fetchResult(String tradeDate, String userId, String token){
		String url = "https://pchq.kaipanla.com/w1/api/index.php";
		Map<String, String> headerMap = new HashMap<String, String>(); 
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("c", "StockRanking");//排行榜
		paramMap.put("a", "RealRankingInfo");//实时排行
		paramMap.put("Date", tradeDate);//交易日起
		paramMap.put("RStart", "0925");//当天开始时间
		paramMap.put("REnd", "1500");//当天结束时间
		paramMap.put("Ratio", "6");//流通市值下拉框值
		paramMap.put("Type", "6");
		paramMap.put("Order", "1");//排序，1为按涨跌幅
		paramMap.put("index", "4");//数据下标，0开始
		paramMap.put("st", "2");//每页返回数据数量
		paramMap.put("UserId", userId);
		paramMap.put("Token", token);
		return HttpTool.post(url, headerMap, paramMap, Charset.forName("utf-8"));
	}
	
}
