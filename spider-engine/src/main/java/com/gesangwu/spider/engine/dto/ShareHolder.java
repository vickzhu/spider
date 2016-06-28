package com.gesangwu.spider.engine.dto;

import java.math.BigDecimal;
import java.util.List;

public class ShareHolder {
	private String enddate;
	private String publishdate;
	private List<Share> list;
	
	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getPublishdate() {
		return publishdate;
	}

	public void setPublishdate(String publishdate) {
		this.publishdate = publishdate;
	}

	public List<Share> getList() {
		return list;
	}

	public void setList(List<Share> list) {
		this.list = list;
	}

	class Share{
		private String publishdate;
		private String enddate;
		private String compcode;
		private String shholdercode;
		private String shholdername;
		private String shholdertype;
		private int rank1;
		private int rank2;
		private BigDecimal holderamt;
		private double holderrto;
		private double pctoffloatshares;
		private int sharestype;
		private String shholdernature;
		private String symbol;
		private String name;
		private int ishis;
		private double chg;
		private String chgtext;
		public String getPublishdate() {
			return publishdate;
		}
		public void setPublishdate(String publishdate) {
			this.publishdate = publishdate;
		}
		public String getEnddate() {
			return enddate;
		}
		public void setEnddate(String enddate) {
			this.enddate = enddate;
		}
		public String getCompcode() {
			return compcode;
		}
		public void setCompcode(String compcode) {
			this.compcode = compcode;
		}
		public String getShholdercode() {
			return shholdercode;
		}
		public void setShholdercode(String shholdercode) {
			this.shholdercode = shholdercode;
		}
		public String getShholdername() {
			return shholdername;
		}
		public void setShholdername(String shholdername) {
			this.shholdername = shholdername;
		}
		public String getShholdertype() {
			return shholdertype;
		}
		public void setShholdertype(String shholdertype) {
			this.shholdertype = shholdertype;
		}
		public int getRank1() {
			return rank1;
		}
		public void setRank1(int rank1) {
			this.rank1 = rank1;
		}
		public int getRank2() {
			return rank2;
		}
		public void setRank2(int rank2) {
			this.rank2 = rank2;
		}
		public BigDecimal getHolderamt() {
			return holderamt;
		}
		public void setHolderamt(BigDecimal holderamt) {
			this.holderamt = holderamt;
		}
		public double getHolderrto() {
			return holderrto;
		}
		public void setHolderrto(double holderrto) {
			this.holderrto = holderrto;
		}
		public double getPctoffloatshares() {
			return pctoffloatshares;
		}
		public void setPctoffloatshares(double pctoffloatshares) {
			this.pctoffloatshares = pctoffloatshares;
		}
		public int getSharestype() {
			return sharestype;
		}
		public void setSharestype(int sharestype) {
			this.sharestype = sharestype;
		}
		public String getShholdernature() {
			return shholdernature;
		}
		public void setShholdernature(String shholdernature) {
			this.shholdernature = shholdernature;
		}
		public String getSymbol() {
			return symbol;
		}
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getIshis() {
			return ishis;
		}
		public void setIshis(int ishis) {
			this.ishis = ishis;
		}
		public double getChg() {
			return chg;
		}
		public void setChg(double chg) {
			this.chg = chg;
		}
		public String getChgtext() {
			return chgtext;
		}
		public void setChgtext(String chgtext) {
			this.chgtext = chgtext;
		}

	}
}
