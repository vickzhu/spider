package com.gesangwu.spider.biz.dao.model;

public class CliqueDept {
    private Long id;

    private Long cliqueId;

    private String secDeptCode;

    private Integer isMainDept;

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

    public Integer getIsMainDept() {
        return isMainDept;
    }

    public void setIsMainDept(Integer isMainDept) {
        this.isMainDept = isMainDept;
    }
}