package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class HolderNum {
    private Long id;

    private String symbol;

    private String endDate;

    private Integer totality;

    private Double price;

    private Double chgRate;

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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public Integer getTotality() {
        return totality;
    }

    public void setTotality(Integer totality) {
        this.totality = totality;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getChgRate() {
        return chgRate;
    }

    public void setChgRate(Double chgRate) {
        this.chgRate = chgRate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}