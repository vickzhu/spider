package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class FiveRangeStatis {
    private Long id;

    private String symbol;

    private String stockName;

    private Integer bigSell = 0;

    private Integer bigBuy = 0;

    private String date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}