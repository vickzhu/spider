package com.gesangwu.spider.biz.dao.model.ext;

import com.gesangwu.spider.biz.dao.model.StockShareHolder;

public class StockShareHolderExt extends StockShareHolder {
	
	private Long cliqueId;
	private String holderName;

	public Long getCliqueId() {
		return cliqueId;
	}

	public void setCliqueId(Long cliqueId) {
		this.cliqueId = cliqueId;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
}
