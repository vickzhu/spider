package com.gesangwu.spider.biz.common;


import java.util.List;

import com.gesangwu.spider.biz.dao.model.LongHuDetail;

public class LongHuDetailPair {

	private List<LongHuDetail> buyList;
	private List<LongHuDetail> sellList;
	
	public LongHuDetailPair(List<LongHuDetail> buyList, List<LongHuDetail> sellList){
		this.buyList = buyList;
		this.sellList = sellList;
	}
	
	public List<LongHuDetail> getBuyList() {
		return buyList;
	}
	
	public void setBuyList(List<LongHuDetail> buyList) {
		this.buyList = buyList;
	}
	
	public List<LongHuDetail> getSellList() {
		return sellList;
	}
	
	public void setSellList(List<LongHuDetail> sellList) {
		this.sellList = sellList;
	}
	
	
}
