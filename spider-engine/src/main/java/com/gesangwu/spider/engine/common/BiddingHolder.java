package com.gesangwu.spider.engine.common;

import java.util.HashMap;
import java.util.Map;

import com.gesangwu.spider.biz.dao.model.Bidding;
/**
 * 
 * @author zhuxb
 *
 */
public class BiddingHolder {

	private static Map<String, Map<String, Bidding>> bidMap = new HashMap<String, Map<String, Bidding>>();

	public static Map<String, Map<String, Bidding>> getBidMap() {
		return bidMap;
	}

	public static void setBidMap(Map<String, Map<String, Bidding>> bidMap) {
		BiddingHolder.bidMap = bidMap;
	}

}
