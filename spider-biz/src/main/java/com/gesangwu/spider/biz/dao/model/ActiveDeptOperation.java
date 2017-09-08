package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class ActiveDeptOperation {
    private Long operationId;

    private String tradeDate;

    private Integer buyDept;

    private Integer sellDept;

    private Double totalBuyAmount;

    private Integer totalBuyStock;

    private Double totalSellAmount;

    private Integer totalSellStock;

    private Double net;

    private Date gmtCreate;

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate == null ? null : tradeDate.trim();
    }

    public Integer getBuyDept() {
        return buyDept;
    }

    public void setBuyDept(Integer buyDept) {
        this.buyDept = buyDept;
    }

    public Integer getSellDept() {
        return sellDept;
    }

    public void setSellDept(Integer sellDept) {
        this.sellDept = sellDept;
    }

    public Double getTotalBuyAmount() {
        return totalBuyAmount;
    }

    public void setTotalBuyAmount(Double totalBuyAmount) {
        this.totalBuyAmount = totalBuyAmount;
    }

    public Integer getTotalBuyStock() {
        return totalBuyStock;
    }

    public void setTotalBuyStock(Integer totalBuyStock) {
        this.totalBuyStock = totalBuyStock;
    }

    public Double getTotalSellAmount() {
        return totalSellAmount;
    }

    public void setTotalSellAmount(Double totalSellAmount) {
        this.totalSellAmount = totalSellAmount;
    }

    public Integer getTotalSellStock() {
        return totalSellStock;
    }

    public void setTotalSellStock(Integer totalSellStock) {
        this.totalSellStock = totalSellStock;
    }

    public Double getNet() {
        return net;
    }

    public void setNet(Double net) {
        this.net = net;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}