package com.gesangwu.spider.biz.dao.model;

public class LongHuType {
    private Long id;

    private String wyType;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWyType() {
        return wyType;
    }

    public void setWyType(String wyType) {
        this.wyType = wyType == null ? null : wyType.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}