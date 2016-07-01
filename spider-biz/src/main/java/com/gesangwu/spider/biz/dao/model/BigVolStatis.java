package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class BigVolStatis {
    private Long id;

    private String symbol;

    private Integer sellTotal = 0;

    private Integer buyTotal = 0;

    private Integer equalTotal = 0;

    private String date;

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

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }
}