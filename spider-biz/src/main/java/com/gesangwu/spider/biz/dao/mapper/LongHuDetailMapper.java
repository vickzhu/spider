package com.gesangwu.spider.biz.dao.mapper;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.LongHuDetail;
import com.gesangwu.spider.biz.dao.model.LongHuDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LongHuDetailMapper extends BaseMapper {
    int countByExample(LongHuDetailExample example);

    int deleteByExample(LongHuDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LongHuDetail record);

    int insertSelective(LongHuDetail record);

    List<LongHuDetail> selectByExample(LongHuDetailExample example);

    LongHuDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LongHuDetail record, @Param("example") LongHuDetailExample example);

    int updateByExample(@Param("record") LongHuDetail record, @Param("example") LongHuDetailExample example);

    int updateByPrimaryKeySelective(LongHuDetail record);

    int updateByPrimaryKey(LongHuDetail record);
}