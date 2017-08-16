package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class LongHu {
    private Long id;

    private String symbol;

    private String stockName;

    private String tradeDate;

    private Double price;

    private Double chg;

    private Double chgPercent;

    private Double turnover;

    private Double amplitude;

    private Double totMktVal;

    private Double negMktVal;

    private Integer secDeptRelation;

    private Long operateClique;

    private String yrType;

    private String yrAmt;

    private String erType;

    private String erAmt;

    private String srType;

    private String srAmt;

    private String mainForce;

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

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate == null ? null : tradeDate.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getChg() {
        return chg;
    }

    public void setChg(Double chg) {
        this.chg = chg;
    }

    public Double getChgPercent() {
        return chgPercent;
    }

    public void setChgPercent(Double chgPercent) {
        this.chgPercent = chgPercent;
    }

    public Double getTurnover() {
        return turnover;
    }

    public void setTurnover(Double turnover) {
        this.turnover = turnover;
    }

    public Double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(Double amplitude) {
        this.amplitude = amplitude;
    }

    public Double getTotMktVal() {
        return totMktVal;
    }

    public void setTotMktVal(Double totMktVal) {
        this.totMktVal = totMktVal;
    }

    public Double getNegMktVal() {
        return negMktVal;
    }

    public void setNegMktVal(Double negMktVal) {
        this.negMktVal = negMktVal;
    }

    public Integer getSecDeptRelation() {
        return secDeptRelation;
    }

    public void setSecDeptRelation(Integer secDeptRelation) {
        this.secDeptRelation = secDeptRelation;
    }

    public Long getOperateClique() {
        return operateClique;
    }

    public void setOperateClique(Long operateClique) {
        this.operateClique = operateClique;
    }

    public String getYrType() {
        return yrType;
    }

    public void setYrType(String yrType) {
        this.yrType = yrType == null ? null : yrType.trim();
    }

    public String getYrAmt() {
        return yrAmt;
    }

    public void setYrAmt(String yrAmt) {
        this.yrAmt = yrAmt == null ? null : yrAmt.trim();
    }

    public String getErType() {
        return erType;
    }

    public void setErType(String erType) {
        this.erType = erType == null ? null : erType.trim();
    }

    public String getErAmt() {
        return erAmt;
    }

    public void setErAmt(String erAmt) {
        this.erAmt = erAmt == null ? null : erAmt.trim();
    }

    public String getSrType() {
        return srType;
    }

    public void setSrType(String srType) {
        this.srType = srType == null ? null : srType.trim();
    }

    public String getSrAmt() {
        return srAmt;
    }

    public void setSrAmt(String srAmt) {
        this.srAmt = srAmt == null ? null : srAmt.trim();
    }

    public String getMainForce() {
        return mainForce;
    }

    public void setMainForce(String mainForce) {
        this.mainForce = mainForce == null ? null : mainForce.trim();
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