package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class StockShareholder {
    private Long id;

    private String symbol;

    private Long shareholder;

    private String endDate;

    private Integer ranking;

    private Integer holdCount;

    private Double holdFloatRate;

    private Double holdRate;

    private Integer isNewHolder;

    private Integer chgCount;

    private Integer stockType;

    private String publishDate;

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

    public Long getShareholder() {
        return shareholder;
    }

    public void setShareholder(Long shareholder) {
        this.shareholder = shareholder;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Integer getHoldCount() {
        return holdCount;
    }

    public void setHoldCount(Integer holdCount) {
        this.holdCount = holdCount;
    }

    public Double getHoldFloatRate() {
        return holdFloatRate;
    }

    public void setHoldFloatRate(Double holdFloatRate) {
        this.holdFloatRate = holdFloatRate;
    }

    public Double getHoldRate() {
        return holdRate;
    }

    public void setHoldRate(Double holdRate) {
        this.holdRate = holdRate;
    }

    public Integer getIsNewHolder() {
        return isNewHolder;
    }

    public void setIsNewHolder(Integer isNewHolder) {
        this.isNewHolder = isNewHolder;
    }

    public Integer getChgCount() {
        return chgCount;
    }

    public void setChgCount(Integer chgCount) {
        this.chgCount = chgCount;
    }

    public Integer getStockType() {
        return stockType;
    }

    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate == null ? null : publishDate.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}