package com.gesangwu.spider.web.velocity.tool;

import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.config.ValidScope;

import com.gandalf.framework.velocity.tool.AbstractTool;
import com.gesangwu.spider.biz.common.SecDeptType;

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
}
