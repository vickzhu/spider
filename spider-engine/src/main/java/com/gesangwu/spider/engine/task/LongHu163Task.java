package com.gesangwu.spider.engine.task;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.gandalf.framework.constant.SymbolConstant;
import com.gesangwu.spider.biz.common.StockUtil;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.engine.util.WangYiLongHuTool;

@Component
public class LongHu163Task extends LongHuTaskTemplate {

	@Override
	protected List<LongHu> getLongHuList(String tradeDate) {
		List<LongHu> longHuList = WangYiLongHuTool.getLongHuList(tradeDate);
		return longHuList;
	}

	@Override
	public List<LongHuDetail> getLongHuDetailList(int dateType, String lhType,
			LongHu longHu) {
		Date now = new Date();
		int index = lhType.indexOf(SymbolConstant.COMMA);
		if(index > 0){
			lhType = lhType.substring(0, index);
		}
		String code = StockUtil.symbol2Code(longHu.getSymbol());
		String tradeDate = longHu.getTradeDate();
		List<LongHuDetail> detailList = WangYiLongHuTool.fetchDetail(code, tradeDate, lhType);
		BigDecimal buyTotal = BigDecimal.ZERO;
		BigDecimal sellTotal = BigDecimal.ZERO;
		Map<String,LongHuDetail> detailMap = new HashMap<String,LongHuDetail>();
		for (LongHuDetail detail : detailList) {
			LongHuDetail lhd = detailMap.get(detail.getSecDeptCode());
			if(lhd != null){//已经存在了
				if(detail.getSellAmt().compareTo(lhd.getSellAmt()) > 0){
					lhd.setSellAmt(detail.getSellAmt());
					BigDecimal netBuy = lhd.getBuyAmt().subtract(lhd.getSellAmt());
					lhd.setNetBuy(netBuy);
					sellTotal = sellTotal.add(lhd.getSellAmt());
				}
				continue;
			} else {
				detailMap.put(detail.getSecDeptCode(), detail);
			}
			detail.setLongHuId(longHu.getId());
			detail.setDateType(dateType);
			detail.setGmtCreate(now);
			detail.setSymbol(StockUtil.code2Symbol(code));
			detail.setTradeDate(tradeDate);
			buyTotal = buyTotal.add(detail.getBuyAmt());
			sellTotal = sellTotal.add(detail.getSellAmt());
		}
		if(dateType == 1){
			longHu.setYrAmt(buildAmt(buyTotal, sellTotal));
		} else if(dateType == 2){
			longHu.setErAmt(buildAmt(buyTotal, sellTotal));
		} else if (dateType == 3){
			longHu.setSrAmt(buildAmt(buyTotal, sellTotal));
		}
		List<LongHuDetail> lhdList = new ArrayList<LongHuDetail>();
		for (LongHuDetail detail : detailMap.values()) {
			lhdList.add(detail);
		}
		return lhdList;
	}

}
