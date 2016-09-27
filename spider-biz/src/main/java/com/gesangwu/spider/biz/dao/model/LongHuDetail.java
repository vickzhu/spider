package com.gesangwu.spider.biz.dao.model;

import java.math.BigDecimal;
import java.util.Date;

public class LongHuDetail {
    private Long id;

    private String symbol;

    private String tradeDate;

    private BigDecimal buyAmt;

    private BigDecimal sellAmt;

    private BigDecimal netBuy;

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

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate == null ? null : tradeDate.trim();
    }

    public BigDecimal getBuyAmt() {
        return buyAmt;
    }

    public void setBuyAmt(BigDecimal buyAmt) {
        this.buyAmt = buyAmt;
    }

    public BigDecimal getSellAmt() {
        return sellAmt;
    }

    public void setSellAmt(BigDecimal sellAmt) {
        this.sellAmt = sellAmt;
    }

    public BigDecimal getNetBuy() {
        return netBuy;
    }

    public void setNetBuy(BigDecimal netBuy) {
        this.netBuy = netBuy;
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