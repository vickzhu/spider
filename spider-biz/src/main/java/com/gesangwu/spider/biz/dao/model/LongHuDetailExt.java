package com.gesangwu.spider.biz.dao.model;


public class LongHuDetailExt extends LongHuDetail {

    private String stockName;

    private Double price;

    private Double chg;

    private Double chgPercent;

    private Double turnover;

    private Double amplitude;

    private Double totMktVal;

    private Double negMktVal;

    private Long operateClique;

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getChg() {
		return chg;
	}

	public void setChg(Double chg) {
		this.chg = chg;
	}

	public Double getChgPercent() {
		return chgPercent;
	}

	public void setChgPercent(Double chgPercent) {
		this.chgPercent = chgPercent;
	}

	public Double getTurnover() {
		return turnover;
	}

	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}

	public Double getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(Double amplitude) {
		this.amplitude = amplitude;
	}

	public Double getTotMktVal() {
		return totMktVal;
	}

	public void setTotMktVal(Double totMktVal) {
		this.totMktVal = totMktVal;
	}

	public Double getNegMktVal() {
		return negMktVal;
	}

	public void setNegMktVal(Double negMktVal) {
		this.negMktVal = negMktVal;
	}

	public Long getOperateClique() {
		return operateClique;
	}

	public void setOperateClique(Long operateClique) {
		this.operateClique = operateClique;
	}
    
}