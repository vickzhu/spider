package com.gesangwu.spider.biz.dao.model;

import java.math.BigDecimal;
import java.util.Date;

public class LongHuDetail {
    private Long id;

    private Long longHuId;

    private String symbol;

    private String tradeDate;

    private BigDecimal buyAmt;

    private BigDecimal sellAmt;

    private BigDecimal netBuy;

    private String secDeptCode;

    private Integer dateType;

    private Long cliqueId;

    private Integer relation;

    private Date gmtCreate;

    private Date gmtUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLongHuId() {
        return longHuId;
    }

    public void setLongHuId(Long longHuId) {
        this.longHuId = longHuId;
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

    public String getSecDeptCode() {
        return secDeptCode;
    }

    public void setSecDeptCode(String secDeptCode) {
        this.secDeptCode = secDeptCode == null ? null : secDeptCode.trim();
    }

    public Integer getDateType() {
        return dateType;
    }

    public void setDateType(Integer dateType) {
        this.dateType = dateType;
    }

    public Long getCliqueId() {
        return cliqueId;
    }

    public void setCliqueId(Long cliqueId) {
        this.cliqueId = cliqueId;
    }

    public Integer getRelation() {
        return relation;
    }

    public void setRelation(Integer relation) {
        this.relation = relation;
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