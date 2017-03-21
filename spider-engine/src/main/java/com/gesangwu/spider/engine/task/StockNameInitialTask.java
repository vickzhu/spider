package com.gesangwu.spider.engine.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.springframework.stereotype.Component;

import com.gandalf.framework.web.tool.Page;
import com.gesangwu.spider.biz.dao.model.Company;
import com.gesangwu.spider.biz.dao.model.CompanyExample;
import com.gesangwu.spider.biz.dao.model.StockNameInitial;
import com.gesangwu.spider.biz.service.CompanyService;
import com.gesangwu.spider.biz.service.StockNameInitialService;

@Component
public class StockNameInitialTask {
	
	private static final String HZ_SCOPE = "[\\u4E00-\\u9FA5]+";
	
	@Resource
	private CompanyService companyService;
	@Resource
	private StockNameInitialService initialService;
	
	public void execute(){
		int cpp = 100;
		int count = companyService.countByExample(null);
		int totalPages = (count + cpp -1)/cpp;
		Date now = new Date();
		for(int cur = 1; cur <= totalPages; cur++){
			Page<Company> page = new Page<Company>(cur, cpp);
			companyService.selectByPagination(new CompanyExample(), page);
			List<Company> companyList = page.getRecords();
			List<StockNameInitial> initialList = new ArrayList<StockNameInitial>();
			for (Company company : companyList) {
				String stockName = company.getStockName();
				List<StringBuilder> sbList = getPinYin(stockName, Boolean.TRUE);
				for (StringBuilder sb : sbList) {
					StockNameInitial initial = new StockNameInitial();
					initial.setSymbol(company.getSymbol());
					initial.setInitialGroup(sb.toString());
					initial.setStockName(stockName);
					initial.setGmtCreate(now);
					initialList.add(initial);
				}
			}
			initialService.batchInsert(initialList);
		}
	}

	/** 
	 * 将汉字转换为拼音
	 * @param src	数据源
	 * @param onlyInitial	是否只要首字母
	 * @return
	 */
    public static List<StringBuilder> getPinYin(String src, boolean onlyInitial) {  
        char[] hzArr = src.toCharArray();
        int hzSize = hzArr.length;
        List<StringBuilder> sbList = new ArrayList<StringBuilder>();
        sbList.add(new StringBuilder());//初始化，保存一个空的sb进去
        for (int i = 0; i < hzSize; i++) {//遍历每一个字
        	try {
				assemble(hzArr[i], sbList, onlyInitial);
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
			}
        }
        return sbList;  
    }
    
    /**
     * 组装拼音
     * @param hz	汉字
     * @param sbList	拼音列表
     * @param onlyInitial 是否只要首字母
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    private static void assemble(char hz, List<StringBuilder> sbList, boolean onlyInitial) throws BadHanyuPinyinOutputFormatCombination{
    	// 判断是否为汉字字符  
        if (Character.toString(hz).matches(HZ_SCOPE)) {  
            String[] pyArr = PinyinHelper.toHanyuPinyinStringArray(hz, getPinyinFormat());//这里可能有多音字
            String[] ca = delDupli(pyArr, onlyInitial);//声调不同的多音字，去掉重复的
            List<StringBuilder> cpList = null;
            if(ca.length > 1){
            	cpList = new ArrayList<StringBuilder>();
            	for(StringBuilder sb : sbList){
            		cpList.add(new StringBuilder(sb));
            	}
            }
			for(int j = 0; j < ca.length; j++){
				if(j > 0) {//说明该字为多音字
					List<StringBuilder> sbTmpList = new ArrayList<StringBuilder>();
					for (StringBuilder sb : cpList) {
						sbTmpList.add(new StringBuilder(sb).append(ca[j]));
					}
					sbList.addAll(sbTmpList);
				} else {
					for (StringBuilder sb : sbList) {
						sb.append(ca[j]);
					}
				}
				
			}
        } else {
        	if(hz>=65 && hz<=90){//大写字母
        		for (StringBuilder sb : sbList) {
        			sb.append((char)(hz+32));//转换为小写
        		}
        	} else if(hz >= 97 && hz <=122) {
        		for (StringBuilder sb : sbList) {
        			sb.append(hz);
        		}
        	}
        }
    }
    
    /**
     * 设置汉字拼音输出的格式  
     * @return
     */
    private static HanyuPinyinOutputFormat getPinyinFormat(){
    	HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();  
    	format.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
    	format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
    	format.setVCharType(HanyuPinyinVCharType.WITH_V); 
        return format;
    }
    
    /**
     * 去掉重复
     * @return
     */
    private static String[] delDupli(String[] pyArr, boolean onlyInitial){
    	Set<String> pySet = new HashSet<String>();
        for(String py : pyArr){
        	if(onlyInitial){
        		pySet.add(String.valueOf(py.charAt(0)));
        	} else {
        		pySet.add(py);
        	}
        	
        }
        String[] result = new String[pySet.size()];
        return pySet.toArray(result);
    }
  
    public static void main(String[] args) {
        String cnStr = "龙大肉食";  
        List<StringBuilder> sbList = getPinYin(cnStr, Boolean.TRUE);
        System.out.println("原文：" + cnStr);
        for (StringBuilder sb : sbList) {
        	System.out.println(sb.toString());  
		}
    }  
}
