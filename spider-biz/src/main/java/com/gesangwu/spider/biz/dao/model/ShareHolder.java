package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class ShareHolder {
    private Long id;

    private String holderName;

    private String holderCode;

    private Integer holderType;

    private Long cliqueId;

    private String remark;

    private Date gmtCreate;

    private Date gmtUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getHolderType() {
        return holderType;
    }

    public void setHolderType(Integer holderType) {
        this.holderType = holderType;
    }

    public Long getCliqueId() {
        return cliqueId;
    }

    public void setCliqueId(Long cliqueId) {
        this.cliqueId = cliqueId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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