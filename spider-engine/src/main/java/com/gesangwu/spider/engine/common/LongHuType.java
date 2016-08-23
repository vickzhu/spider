package com.gesangwu.spider.engine.common;
/**
 * 这个对应网易龙虎榜类型
 * http://quotes.money.163.com/old/#query=MRLHB
 * http://quotes.money.163.com/hs/marketdata/service/lhb.php?page=0&query=start:2016-08-22;end:2016-08-22&sort=TDATE&order=desc&count=150&type=query&req=21725
 * @author zhuxb
 *
 */
public enum LongHuType {
	ZFPLZ("01","涨幅偏离值达7%的证券"),
	DFPLZ("02","跌幅偏离值达7%的证券"),
	ZFZ("03","振幅值达15%的证券"),
	HSL("04","换手率达20%的证券"),
	LXSR("05","连续三个交易日内，涨幅偏离值累计达20%的证券"),
	WXZ("11","无价格涨跌幅限制的证券"),
	ST("24","连续三个交易日内，涨幅偏离值累计达到12%的ST证券、*ST证券和未完成股改证券"),
	RZMR("28","单只标的证券的当日融资买入数量达到当日该证券总交易量的50%以上");
	
	private String code;
	private String desc;
	private LongHuType(String code, String desc){
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public static String getDesc(String code){
		for(LongHuType type : LongHuType.values()){
			if(type.getCode().equals(code)){
				return type.getDesc();
			}
		}
		return null;
	}
}
