package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class LongHu {
    private Long id;

    private String symbol;

    private String stockName;

    private String tradeDate;

    private Double price;

    private Double chg;

    private Double chgPercent;

    private Double turnover;

    private Double amplitude;

    private Double totMktVal;

    private Double negMktVal;

    private String drLhType;

    private Double drBuyAmt;

    private Double drSellAmt;

    private Double drNetBuy;

    private String srLhType;

    private Double srBuyAmt;

    private Double srSellAmt;

    private Double srNetBuy;

    private Integer secDeptRelation;

    private Date gmtCreate;

    private Date gmtUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate == null ? null : tradeDate.trim();
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

    public String getDrLhType() {
        return drLhType;
    }

    public void setDrLhType(String drLhType) {
        this.drLhType = drLhType == null ? null : drLhType.trim();
    }

    public Double getDrBuyAmt() {
        return drBuyAmt;
    }

    public void setDrBuyAmt(Double drBuyAmt) {
        this.drBuyAmt = drBuyAmt;
    }

    public Double getDrSellAmt() {
        return drSellAmt;
    }

    public void setDrSellAmt(Double drSellAmt) {
        this.drSellAmt = drSellAmt;
    }

    public Double getDrNetBuy() {
        return drNetBuy;
    }

    public void setDrNetBuy(Double drNetBuy) {
        this.drNetBuy = drNetBuy;
    }

    public String getSrLhType() {
        return srLhType;
    }

    public void setSrLhType(String srLhType) {
        this.srLhType = srLhType == null ? null : srLhType.trim();
    }

    public Double getSrBuyAmt() {
        return srBuyAmt;
    }

    public void setSrBuyAmt(Double srBuyAmt) {
        this.srBuyAmt = srBuyAmt;
    }

    public Double getSrSellAmt() {
        return srSellAmt;
    }

    public void setSrSellAmt(Double srSellAmt) {
        this.srSellAmt = srSellAmt;
    }

    public Double getSrNetBuy() {
        return srNetBuy;
    }

    public void setSrNetBuy(Double srNetBuy) {
        this.srNetBuy = srNetBuy;
    }

    public Integer getSecDeptRelation() {
        return secDeptRelation;
    }

    public void setSecDeptRelation(Integer secDeptRelation) {
        this.secDeptRelation = secDeptRelation;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }
}