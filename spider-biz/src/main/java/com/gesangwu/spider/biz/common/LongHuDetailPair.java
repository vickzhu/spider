package com.gesangwu.spider.biz.common;


import java.util.List;

import com.gesangwu.spider.biz.dao.model.ext.LongHuDetailDept;

public class LongHuDetailPair {

	private List<LongHuDetailDept> buyList;
	private List<LongHuDetailDept> sellList;
	
	public LongHuDetailPair(List<LongHuDetailDept> buyList, List<LongHuDetailDept> sellList){
		this.buyList = buyList;
		this.sellList = sellList;
	}
	
	public List<LongHuDetailDept> getBuyList() {
		return buyList;
	}
	
	public void setBuyList(List<LongHuDetailDept> buyList) {
		this.buyList = buyList;
	}
	
	public List<LongHuDetailDept> getSellList() {
		return sellList;
	}
	
	public void setSellList(List<LongHuDetailDept> sellList) {
		this.sellList = sellList;
	}
	
	
}
