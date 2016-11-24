package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class FiveRangeStatis {
    private Long id;

    private String symbol;

    private Integer bigSell;

    private Integer bigBuy;

    private String tradeDate;

    private Double activeMarketValue;

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

    public Integer getBigSell() {
        return bigSell;
    }

    public void setBigSell(Integer bigSell) {
        this.bigSell = bigSell;
    }

    public Integer getBigBuy() {
        return bigBuy;
    }

    public void setBigBuy(Integer bigBuy) {
        this.bigBuy = bigBuy;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate == null ? null : tradeDate.trim();
    }

    public Double getActiveMarketValue() {
        return activeMarketValue;
    }

    public void setActiveMarketValue(Double activeMarketValue) {
        this.activeMarketValue = activeMarketValue;
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