package com.gesangwu.spider.biz.dao.model;

public class SynergyDetail {
    private Long id;

    private Integer sG;

    private String dept;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getsG() {
        return sG;
    }

    public void setsG(Integer sG) {
        this.sG = sG;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept == null ? null : dept.trim();
    }
}