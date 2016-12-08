package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class JdStatis {
    private Long id;

    private String symbol;

    private Double activeFloatMarket;

    private Integer jdTotal;

    private String tradeDate;

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

    public Double getActiveFloatMarket() {
        return activeFloatMarket;
    }

    public void setActiveFloatMarket(Double activeFloatMarket) {
        this.activeFloatMarket = activeFloatMarket;
    }

    public Integer getJdTotal() {
        return jdTotal;
    }

    public void setJdTotal(Integer jdTotal) {
        this.jdTotal = jdTotal;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate == null ? null : tradeDate.trim();
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