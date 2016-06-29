package com.gesangwu.spider.biz.dao.model;

import java.math.BigDecimal;
import java.util.Date;

public class Company {
    private Long id;

    private String symbol;

    private String stockCode;

    private String stockName;

    private String companyName;

    private Double marketValue;

    private Double floatMarketValue;

    private Double activeMarketValue;

    private BigDecimal lastPrice;

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

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }

    public Double getFloatMarketValue() {
        return floatMarketValue;
    }

    public void setFloatMarketValue(Double floatMarketValue) {
        this.floatMarketValue = floatMarketValue;
    }

    public Double getActiveMarketValue() {
        return activeMarketValue;
    }

    public void setActiveMarketValue(Double activeMarketValue) {
        this.activeMarketValue = activeMarketValue;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
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