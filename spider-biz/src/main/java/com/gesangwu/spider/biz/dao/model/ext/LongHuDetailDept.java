package com.gesangwu.spider.biz.dao.model.ext;

import com.gesangwu.spider.biz.dao.model.LongHuDetail;

/**
 * 龙虎榜详情和营业部
 * @author zhuxb
 *
 */
public class LongHuDetailDept extends LongHuDetail {
	
	private String deptAddr;

    private String code;

    private Integer deptType;

	public String getDeptAddr() {
		return deptAddr;
	}

	public void setDeptAddr(String deptAddr) {
		this.deptAddr = deptAddr;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getDeptType() {
		return deptType;
	}

	public void setDeptType(Integer deptType) {
		this.deptType = deptType;
	}
	
}
