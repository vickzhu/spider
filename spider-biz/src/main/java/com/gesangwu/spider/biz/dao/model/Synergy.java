package com.gesangwu.spider.biz.dao.model;

import java.util.Date;

public class Synergy {
    private Long id;

    private Integer deptCount;

    private Integer total;

    private Integer sG;

    private Date gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDeptCount() {
        return deptCount;
    }

    public void setDeptCount(Integer deptCount) {
        this.deptCount = deptCount;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getsG() {
        return sG;
    }

    public void setsG(Integer sG) {
        this.sG = sG;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}