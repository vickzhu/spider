package com.gesangwu.spider.web.velocity.tool;

import org.apache.velocity.tools.Scope;
import org.apache.velocity.tools.config.DefaultKey;
import org.apache.velocity.tools.config.ValidScope;

import com.gandalf.framework.velocity.tool.AbstractTool;
import com.gesangwu.spider.web.util.StockNameCache;

@DefaultKey("companyTool")
@ValidScope(Scope.APPLICATION)
public class CompanyTool extends AbstractTool  {

	public static String getStockName(String symbol){
		return StockNameCache.getStockName(symbol);
	}
	
}
