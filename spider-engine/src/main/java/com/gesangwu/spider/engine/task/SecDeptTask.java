package com.gesangwu.spider.engine.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.gandalf.framework.net.HttpTool;
import com.gesangwu.spider.biz.dao.model.SecCompany;
import com.gesangwu.spider.biz.dao.model.SecDept;
import com.gesangwu.spider.biz.service.SecCompanyService;
import com.gesangwu.spider.biz.service.SecDeptService;
import com.gesangwu.spider.engine.util.ProvinceHolder;
/**
 * 抓取营业部，不过已经没用了
 * @author zhuxb
 *
 */
@Deprecated
public class SecDeptTask {
	
	@Resource
	private SecCompanyService secCompanyService;
	@Resource
	private SecDeptService secDeptService;

	public void execute(){
		List<SecCompany> secCompanyList = secCompanyService.selectByExample(null);
		for (SecCompany secCompany : secCompanyList) {
			String code = secCompany.getCode();
			fetchDept(1, code);
		}
	}
	
	private static final String r1 = "\"pages\"\\:([0-9]*),\"data\"\\:\\[\\{(.*)\\}\\],\"url\"\\:\"";
	private static final Pattern p1 = Pattern.compile(r1);
	
	private static final String r2 = "\"Province\"\\:\"([^\"]*)\",\"SalesCode\"\\:\"(\\d*)\",\"SalesName\"\\:\"([^\"]*)\",";
	private static final Pattern p2 = Pattern.compile(r2);
	
	private void fetchDept(int page, String companyCode){
		String url = buildUrl(page, companyCode);
		String result = HttpTool.get(url);
		Matcher matcher = p1.matcher(result);
		if(!matcher.find()){
			return;
		}
		int pages = Integer.valueOf(matcher.group(1));
		String content = matcher.group(2);
		List<SecDept> deptList = parse(content, companyCode);
		secDeptService.batchInsert(deptList);
		if(page >= pages){
			return;
		} else {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			fetchDept(++page, companyCode);
		}	
	}
	
	private List<SecDept> parse(String content, String companyCode){
		Matcher m = p2.matcher(content);
		Date now = new Date();
		List<SecDept> deptList = new ArrayList<SecDept>();
		while(m.find()){
			String provinceName = m.group(1);
			String deptCode = m.group(2);
			String deptAddr = m.group(3);
			String provinceCode = ProvinceHolder.getNameCodeMap().get(provinceName);
			
			SecDept dept = new SecDept();
			dept.setCode(deptCode);
			dept.setDeptAddr(deptAddr);
			dept.setProvince(provinceCode);
			dept.setSecCompany(companyCode);
			dept.setGmtCreate(now);
			
			deptList.add(dept);
		}
		return deptList;
	}
	
	private String buildUrl(int page, String companyCode){
		StringBuffer sb = new StringBuffer();
		sb.append("http://data.eastmoney.com/DataCenter_V3/stock2016/yybSearch.ashx?pagesize=50&page=");
		sb.append(page);
		sb.append("&param=&sortRule=-1&sortType=UpCount&typeCode=1&gpfw=0&code=");
		sb.append(companyCode);
		return sb.toString();
	}
	
	public static void main(String[] args){
		SecDeptTask task = new SecDeptTask();
		task.fetchDept(1, "80084302");
	}
	
}
