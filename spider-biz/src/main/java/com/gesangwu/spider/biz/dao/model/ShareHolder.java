package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class ShareHolder {
    private Long id;

    private String stockCode;

    private String endDate;

    private String holderName;

    private String holderCode;

    private Integer ranking;

    private Double holdCount;

    private Double holdRate;

    private Double holdFloatRate;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName == null ? null : holderName.trim();
    }

    public String getHolderCode() {
        return holderCode;
    }

    public void setHolderCode(String holderCode) {
        this.holderCode = holderCode == null ? null : holderCode.trim();
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Double getHoldCount() {
        return holdCount;
    }

    public void setHoldCount(Double holdCount) {
        this.holdCount = holdCount;
    }

    public Double getHoldRate() {
        return holdRate;
    }

    public void setHoldRate(Double holdRate) {
        this.holdRate = holdRate;
    }

    public Double getHoldFloatRate() {
        return holdFloatRate;
    }

    public void setHoldFloatRate(Double holdFloatRate) {
        this.holdFloatRate = holdFloatRate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}