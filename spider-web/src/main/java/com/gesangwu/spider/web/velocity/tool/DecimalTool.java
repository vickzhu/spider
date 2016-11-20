package com.gesangwu.spider.web.velocity.tool;

import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.config.ValidScope;

import com.gesangwu.spider.biz.common.DecimalUtil;

@DefaultKey("decimalTool")
@ValidScope(Scope.APPLICATION)
public class DecimalTool {

	public String toYi(double decimal){
		return DecimalUtil.format(decimal/100000000, 2).doubleValue()+"亿";
	}
}
