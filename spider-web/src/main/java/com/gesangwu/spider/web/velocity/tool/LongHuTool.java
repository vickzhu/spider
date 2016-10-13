package com.gesangwu.spider.web.velocity.tool;

import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.config.ValidScope;

import com.gandalf.framework.util.StringUtil;
import com.gandalf.framework.velocity.tool.AbstractTool;
import com.gesangwu.spider.biz.common.SecDeptType;
import com.gesangwu.spider.biz.dao.cache.CliqueCache;
import com.gesangwu.spider.biz.dao.model.Clique;

@DefaultKey("longHuTool")
@ValidScope(Scope.APPLICATION)
public class LongHuTool extends AbstractTool {

	/**
	 * 获得营业部类型
	 * @param code
	 * @return
	 */
	public static String getDeptType(int code){
		return SecDeptType.getDesc(code);
	}
	
	public static String getClique(Long cliqueId){
		Clique clique = CliqueCache.getCliqueMap().get(cliqueId);
		return clique == null ? StringUtil.EMPTY : clique.getName();
	}
}
