package com.gesangwu.spider.web.velocity.tool;

import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.config.ValidScope;

import com.gandalf.framework.velocity.tool.AbstractTool;
import com.gesangwu.spider.web.util.SecDeptSeatCache;

@DefaultKey("secDeptTool")
@ValidScope(Scope.APPLICATION)
public class SecDeptTool extends AbstractTool {
	
	/**
	 * 所属席位
	 * @param deptCode
	 * @return
	 */
	public static String getSeat(String deptCode){
		return SecDeptSeatCache.getSeat(deptCode);
	}
	
}
