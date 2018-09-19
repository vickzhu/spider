package com.gesangwu.spider.engine.task;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.SimpleNodeIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gandalf.framework.net.HttpTool;
import com.gandalf.framework.util.StringUtil;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.HolderNum;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.HolderNumService;
import com.gesangwu.spider.engine.util.LittleCompanyHolder;
import com.gesangwu.spider.engine.util.UnicodeUtil;

/**
 * 股东人数
 * @author zhuxb
 *
 */
@Component
public class HolderNumTask {
	
	private static final Logger logger = LoggerFactory.getLogger(HolderNumTask.class);
	
	private static final String r="<div style=\"display\\:none\" id=\"gdrsData\" >\\[\\[(.*)\\]\\]</div>";
	private static final Pattern p = Pattern.compile(r);
	
	@Resource
	private CompanyService companyService;
	@Resource
	private HolderNumService hnService;
	
	@Scheduled(cron = "0 30 12 * * *")
	public void execute(){
		long start = System.currentTimeMillis();
		List<Company> companyList = LittleCompanyHolder.getCompanyList();
		List<HolderNum> list = new ArrayList<HolderNum>();
		int size = companyList.size();
		for (int i = 0; i < size; i++) {
			Company company = companyList.get(i);
			String code = company.getStockCode();
			String symbol = StockUtil.code2Symbol(code);
			String url = buildUrl(code);
			String result = HttpTool.get(url);
			if(StringUtil.isBlank(result)){//可能已退市
				continue;
			}
			List<HolderNum> hnList = buildTotailty(result);
			Map<String, Double> chgRateMap = buildChgRate(result);
			HolderNum latestHn = hnService.selectLatestBySymbol(symbol);
			for (int j = 0; j < hnList.size(); j++) {
				HolderNum holderNum = hnList.get(j);
				if(latestHn != null && holderNum.getEndDate().compareTo(latestHn.getEndDate()) < 1){
					break;
				}
				holderNum.setSymbol(symbol);
				if(chgRateMap == null || chgRateMap.get(holderNum.getEndDate())==null){
					holderNum.setChgRate(0d);
				} else {
					holderNum.setChgRate( chgRateMap.get(holderNum.getEndDate()));
				}
				list.add(holderNum);
			}
			if((i == size-1 && list.size() > 0) || list.size() >= 50){
				hnService.insertBatch(list);
				list.clear();
			}
		}	
		long end = System.currentTimeMillis();
		logger.info("Fetch holder number used:" + (end - start) + "ms!");
	}
	
	/**
	 * 构建人数
	 * @param result
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	private List<HolderNum> buildTotailty(String result){
		List<HolderNum> list = new ArrayList<HolderNum>();
		Matcher m = p.matcher(result);
		if(!m.find()){
			return list;
		}
		Date now = new Date();
		String content = m.group(1);
		content = content.replaceAll("\"", StringUtil.EMPTY);
		String[] arr = content.split("\\],\\[");
		for (String columns : arr) {
			String[] columnArr = columns.split(SymbolConstant.COMMA);
			String total = columnArr[1];
			String endDate = decodeDate(columnArr[0]);
			HolderNum hn = new HolderNum();
			hn.setEndDate(endDate);
			hn.setGmtCreate(now);
			hn.setTotality(Integer.valueOf(total));
			list.add(hn);
		}
		return list;
	}
	
	private String decodeDate(String date){
		date = UnicodeUtil.decodeUnicode(date);
		date = date.replace(" 一季报", "-03-31");
		date = date.replace(" 中报", "-06-30");
		date = date.replace(" 三季报", "-09-30");
		date = date.replace(" 年报", "-12-31");
		return date;
	}
	
	private static final String r3 = "<div class=\"swiper\\-container swiper\\-container2 swiper\\-hide\">([\\s\\S]*)<!\\-\\- Add Pagination \\-\\->";
	private static final Pattern p3 = Pattern.compile(r3);
	
	/**
	 * 构建股东人数对象
	 * @param result
	 * @return
	 */
	private Map<String, Double> buildChgRate(String result){
		Matcher m = p3.matcher(result);
		if(m.find()){
			String content = m.group(1);
			content = content.replaceAll("[\\r\\n\\t]|  ", StringUtil.EMPTY);
			try{
				Parser parser = Parser.createParser(content, "UTF-8");
				NodeList nodeList = parser.parse(null);
				return processNodeList(nodeList);
			}catch(Exception e){
				logger.error("Parser ChangeRate from 10jqk.com.cn failed!", e);
			}
		}
		return null;
	}
	
	private Map<String, Double> processNodeList(NodeList list) {
		Map<String, Double> chgRateMap = new HashMap<String, Double>();
		//迭代开始
		SimpleNodeIterator iterator = list.elements();
		while (iterator.hasMoreNodes()) {
			Node node = iterator.nextNode();
			if(node instanceof TagNode){
				TagNode tn = (TagNode)node;
				if("LI".equals(tn.getTagName())){
					String clz = tn.getAttribute("class");
					if(clz != null && clz.startsWith("c-")){
						String chgStr = tn.getFirstChild().getText();
						chgStr = chgStr.replace("%", StringUtil.EMPTY);
						String endDate = tn.getParent().getFirstChild().getFirstChild().getText();
						chgRateMap.put(endDate, Double.valueOf(chgStr));
					}
				}
			}
			//得到该节点的子节点列表
			NodeList childList = node.getChildren();
			if (null != childList){
				
				Map<String, Double> tmpMap = processNodeList(childList);
				if(tmpMap.size() > 0){
					chgRateMap.putAll(tmpMap);
				}
			}
		}//end wile
		return chgRateMap;
	}
	
	public static void main(String[] args){
		HolderNumTask task = new HolderNumTask();
		String result = HttpTool.get(task.buildUrl("002211"));
		task.buildChgRate(result);
	}
	
	private String buildUrl(String code){
		StringBuilder sb = new StringBuilder();
		sb.append("http://basic.10jqka.com.cn/mobile/");
		sb.append(code);
		sb.append("/holdern.html");
		return sb.toString();
	}
	
}
