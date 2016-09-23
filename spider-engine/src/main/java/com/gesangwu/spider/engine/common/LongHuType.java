package com.gesangwu.spider.engine.common;
/**
 * 这个对应网易龙虎榜类型
 * http://quotes.money.163.com/old/#query=MRLHB
 * http://quotes.money.163.com/hs/marketdata/service/lhb.php?page=0&query=start:2016-08-22;end:2016-08-22&sort=TDATE&order=desc&count=150&type=query&req=21725
 * @author zhuxb
 *
 */
public enum LongHuType {
	ZFPLZ(1, "01","涨幅偏离值达7%的证券"),
	DFPLZ(2, "02","跌幅偏离值达7%的证券"),
	ZFZ(3, "03","振幅值达15%的证券"),
	HSL(4, "04","换手率达20%的证券"),
	LXZF(5, "05","连续三个交易日内，涨幅偏离值累计达20%的证券"),
	LXDF(6, "06","连续三个交易日内，跌幅偏离值累计达20%的证券"),
	STZ15(7,"07","连续三个交易日内，涨幅偏离值累计达到15%的ST证券、*ST证券和未完成股改证券"),
	STD15(8,"08","连续三个交易日内，跌幅偏离值累计达到15%的ST证券、*ST证券和未完成股改证券"),
	D(9,"09","连续三个交易日内，日均换手率与前五个交易日的日均换手率的比值达到30倍，且换手率累计达20%的股票"),
	WXZ(9, "11","无价格涨跌幅限制的证券"),
	STZF(10, "24","连续三个交易日内，涨幅偏离值累计达到12%的ST证券、*ST证券和未完成股改证券"),
	RZMR(11, "28","单只标的证券的当日融资买入数量达到当日该证券总交易量的50%以上");
	
	private int code;
	private String wyCode;
	private String desc;
	private LongHuType(int code, String wyCode, String desc){
		this.code = code;
		this.wyCode = wyCode;
		this.desc = desc;
	}
	
	public int getCode() {
		return code;
	}

	public String getWyCode() {
		return wyCode;
	}

	public String getDesc() {
		return desc;
	}

	public static String getDesc(int code){
		for(LongHuType type : LongHuType.values()){
			if(type.getCode() == code){
				return type.getDesc();
			}
		}
		return null;
	}
	
	public static String getDesc(String wyCode){
		for(LongHuType type : LongHuType.values()){
			if(type.getWyCode().equals(wyCode)){
				return type.getDesc();
			}
		}
		return null;
	}
}
