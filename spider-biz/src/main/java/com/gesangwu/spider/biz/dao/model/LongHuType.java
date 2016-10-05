package com.gesangwu.spider.biz.dao.model;

public class LongHuType {
    private Long id;

    private String lhType;

    private String lhDesc;

    private Integer dateType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLhType() {
        return lhType;
    }

    public void setLhType(String lhType) {
        this.lhType = lhType == null ? null : lhType.trim();
    }

    public String getLhDesc() {
        return lhDesc;
    }

    public void setLhDesc(String lhDesc) {
        this.lhDesc = lhDesc == null ? null : lhDesc.trim();
    }

    public Integer getDateType() {
        return dateType;
    }

    public void setDateType(Integer dateType) {
        this.dateType = dateType;
    }
}