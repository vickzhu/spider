package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class Bidding {
    private Long id;

    private String symbol;

    private Double sellPrice;

    private Integer sellVol;

    private Integer sellSurplus;

    private Double buyPrice;

    private Integer buyVol;

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

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getSellVol() {
        return sellVol;
    }

    public void setSellVol(Integer sellVol) {
        this.sellVol = sellVol;
    }

    public Integer getSellSurplus() {
        return sellSurplus;
    }

    public void setSellSurplus(Integer sellSurplus) {
        this.sellSurplus = sellSurplus;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Integer getBuyVol() {
        return buyVol;
    }

    public void setBuyVol(Integer buyVol) {
        this.buyVol = buyVol;
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