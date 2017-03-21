package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class StockNameInitial {
    private Long id;

    private String symbol;

    private String stockName;

    private String initialGroup;

    private Date gmtCreate;

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

    public String getInitialGroup() {
        return initialGroup;
    }

    public void setInitialGroup(String initialGroup) {
        this.initialGroup = initialGroup == null ? null : initialGroup.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}