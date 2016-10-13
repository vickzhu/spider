package com.gesangwu.spider.biz.dao.cache;

import java.util.HashMap;
import java.util.Map;

import com.gesangwu.spider.biz.dao.model.Clique;
/**
 * 缓存帮派信息
 * @author zhuxb
 *
 */
public class CliqueCache {
	
	private static Map<Long, Clique> cliqueMap = new HashMap<Long, Clique>();

	public static Map<Long, Clique> getCliqueMap() {
		return cliqueMap;
	}

	public static void setCliqueMap(Map<Long, Clique> cliqueMap) {
		CliqueCache.cliqueMap = cliqueMap;
	}
	
	public static Clique get(Long cliqueId){
		return cliqueMap.get(cliqueId);
	}

	public static void add(Clique clique) {
		cliqueMap.put(clique.getId(), clique);		
	}

	public static void delete(Clique clique) {
		cliqueMap.remove(clique.getId());		
	}

}
