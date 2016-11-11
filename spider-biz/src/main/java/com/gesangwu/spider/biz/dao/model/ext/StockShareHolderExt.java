package com.gesangwu.spider.biz.dao.model.ext;

import com.gesangwu.spider.biz.dao.model.StockShareHolder;

public class StockShareHolderExt extends StockShareHolder {
	
	private String stockName;

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
}
