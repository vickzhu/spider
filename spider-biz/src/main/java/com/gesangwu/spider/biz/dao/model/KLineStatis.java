package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class KLineStatis {
    private Long id;

    private String tradeDate;

    private Integer zt;

    private Integer dt;

    private Integer hc;

    private Integer sz;

    private Integer xd;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate == null ? null : tradeDate.trim();
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getHc() {
        return hc;
    }

    public void setHc(Integer hc) {
        this.hc = hc;
    }

    public Integer getSz() {
        return sz;
    }

    public void setSz(Integer sz) {
        this.sz = sz;
    }

    public Integer getXd() {
        return xd;
    }

    public void setXd(Integer xd) {
        this.xd = xd;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}