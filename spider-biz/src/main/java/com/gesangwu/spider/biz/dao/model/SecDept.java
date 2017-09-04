package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class SecDept {
    private Long id;

    private String deptAddr;

    private String deptAddrShort;

    private String code;

    private String province;

    private Integer deptType;

    private Integer activeDept;

    private Date gmtCreate;

    private Date gmtUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptAddr() {
        return deptAddr;
    }

    public void setDeptAddr(String deptAddr) {
        this.deptAddr = deptAddr == null ? null : deptAddr.trim();
    }

    public String getDeptAddrShort() {
        return deptAddrShort;
    }

    public void setDeptAddrShort(String deptAddrShort) {
        this.deptAddrShort = deptAddrShort == null ? null : deptAddrShort.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public Integer getDeptType() {
        return deptType;
    }

    public void setDeptType(Integer deptType) {
        this.deptType = deptType;
    }

    public Integer getActiveDept() {
        return activeDept;
    }

    public void setActiveDept(Integer activeDept) {
        this.activeDept = activeDept;
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