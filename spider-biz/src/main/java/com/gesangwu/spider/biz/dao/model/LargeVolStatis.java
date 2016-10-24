package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class LargeVolStatis {
    private Long id;

    private String symbol;

    private Integer sellTotal;

    private Integer buyTotal;

    private Integer equalTotal;

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

    public Integer getSellTotal() {
        return sellTotal;
    }

    public void setSellTotal(Integer sellTotal) {
        this.sellTotal = sellTotal;
    }

    public Integer getBuyTotal() {
        return buyTotal;
    }

    public void setBuyTotal(Integer buyTotal) {
        this.buyTotal = buyTotal;
    }

    public Integer getEqualTotal() {
        return equalTotal;
    }

    public void setEqualTotal(Integer equalTotal) {
        this.equalTotal = equalTotal;
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