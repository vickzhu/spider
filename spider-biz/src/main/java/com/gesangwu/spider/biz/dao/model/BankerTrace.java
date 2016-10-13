package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class BankerTrace {
    private Long id;

    private String symbol;

    private String stockName;

    private String launchDate;

    private String msDate;

    private Integer msScore;

    private Integer scores;

    private Integer step;

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

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate == null ? null : launchDate.trim();
    }

    public String getMsDate() {
        return msDate;
    }

    public void setMsDate(String msDate) {
        this.msDate = msDate == null ? null : msDate.trim();
    }

    public Integer getMsScore() {
        return msScore;
    }

    public void setMsScore(Integer msScore) {
        this.msScore = msScore;
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
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