package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class LongHuDetail {
    private Long id;

    private String symbol;

    private Date tradeDate;

    private Integer isSr;

    private Integer secDeptCode;

    private String secDeptAddr;

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

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Integer getIsSr() {
        return isSr;
    }

    public void setIsSr(Integer isSr) {
        this.isSr = isSr;
    }

    public Integer getSecDeptCode() {
        return secDeptCode;
    }

    public void setSecDeptCode(Integer secDeptCode) {
        this.secDeptCode = secDeptCode;
    }

    public String getSecDeptAddr() {
        return secDeptAddr;
    }

    public void setSecDeptAddr(String secDeptAddr) {
        this.secDeptAddr = secDeptAddr == null ? null : secDeptAddr.trim();
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