package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class Bidding {
    private Long id;

    private String symbol;

    private Double price;

    private Integer vol;

    private Integer sellSurplus;

    private Integer buySurplus;

    private String tradeTime;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getVol() {
        return vol;
    }

    public void setVol(Integer vol) {
        this.vol = vol;
    }

    public Integer getSellSurplus() {
        return sellSurplus;
    }

    public void setSellSurplus(Integer sellSurplus) {
        this.sellSurplus = sellSurplus;
    }

    public Integer getBuySurplus() {
        return buySurplus;
    }

    public void setBuySurplus(Integer buySurplus) {
        this.buySurplus = buySurplus;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime == null ? null : tradeTime.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}