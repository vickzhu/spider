package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class StockDiary {
    private Long id;

    private Long userId;

    private String stockCode;

    private String stockName;

    private String startDate;

    private String endDate;

    private Integer phase;

    private Double chipLeast;

    private String chipDate;

    private Integer status;

    private Long cliqueId;

    private Integer authority;

    private Date gmtCreate;

    private Date gmtUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode == null ? null : stockCode.trim();
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName == null ? null : stockName.trim();
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? null : startDate.trim();
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? null : endDate.trim();
    }

    public Integer getPhase() {
        return phase;
    }

    public void setPhase(Integer phase) {
        this.phase = phase;
    }

    public Double getChipLeast() {
        return chipLeast;
    }

    public void setChipLeast(Double chipLeast) {
        this.chipLeast = chipLeast;
    }

    public String getChipDate() {
        return chipDate;
    }

    public void setChipDate(String chipDate) {
        this.chipDate = chipDate == null ? null : chipDate.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCliqueId() {
        return cliqueId;
    }

    public void setCliqueId(Long cliqueId) {
        this.cliqueId = cliqueId;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
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