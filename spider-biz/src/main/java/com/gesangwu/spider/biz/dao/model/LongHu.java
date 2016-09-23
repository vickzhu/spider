package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class LongHu {
    private Long id;

    private String symbol;

    private String stockName;

    private String longHuType;

    private Date tradeDate;

    private Double price;

    private Double chg;

    private Double chgPercent;

    private Double turnover;

    private Double totMktVal;

    private Double negMktVal;

    private Double totalBuy;

    private Double totalSell;

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

    public String getLongHuType() {
        return longHuType;
    }

    public void setLongHuType(String longHuType) {
        this.longHuType = longHuType == null ? null : longHuType.trim();
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
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

    public Double getTotalBuy() {
        return totalBuy;
    }

    public void setTotalBuy(Double totalBuy) {
        this.totalBuy = totalBuy;
    }

    public Double getTotalSell() {
        return totalSell;
    }

    public void setTotalSell(Double totalSell) {
        this.totalSell = totalSell;
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