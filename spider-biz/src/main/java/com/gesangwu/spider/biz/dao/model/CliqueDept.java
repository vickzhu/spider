package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class CliqueDept {
    private Long id;

    private Long cliqueId;

    private String secDeptCode;

    private String secDeptName;

    private Integer deptType;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCliqueId() {
        return cliqueId;
    }

    public void setCliqueId(Long cliqueId) {
        this.cliqueId = cliqueId;
    }

    public String getSecDeptCode() {
        return secDeptCode;
    }

    public void setSecDeptCode(String secDeptCode) {
        this.secDeptCode = secDeptCode == null ? null : secDeptCode.trim();
    }

    public String getSecDeptName() {
        return secDeptName;
    }

    public void setSecDeptName(String secDeptName) {
        this.secDeptName = secDeptName == null ? null : secDeptName.trim();
    }

    public Integer getDeptType() {
        return deptType;
    }

    public void setDeptType(Integer deptType) {
        this.deptType = deptType;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}