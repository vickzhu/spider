package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class UpperShadow {
    private Long id;

    private String symbol;

    private String tradeDate;

    private Double chgPercent;

    private Double maxChgPercent;

    private Double shadowPercent;

    private Date gmtCreate;

    private Double activeFloatMarket;

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

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate == null ? null : tradeDate.trim();
    }

    public Double getChgPercent() {
        return chgPercent;
    }

    public void setChgPercent(Double chgPercent) {
        this.chgPercent = chgPercent;
    }

    public Double getMaxChgPercent() {
        return maxChgPercent;
    }

    public void setMaxChgPercent(Double maxChgPercent) {
        this.maxChgPercent = maxChgPercent;
    }

    public Double getShadowPercent() {
        return shadowPercent;
    }

    public void setShadowPercent(Double shadowPercent) {
        this.shadowPercent = shadowPercent;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Double getActiveFloatMarket() {
        return activeFloatMarket;
    }

    public void setActiveFloatMarket(Double activeFloatMarket) {
        this.activeFloatMarket = activeFloatMarket;
    }
}