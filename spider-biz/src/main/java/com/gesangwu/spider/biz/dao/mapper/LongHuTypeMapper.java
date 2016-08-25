package com.gesangwu.spider.biz.dao.mapper;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.LongHuType;
import com.gesangwu.spider.biz.dao.model.LongHuTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LongHuTypeMapper extends BaseMapper {
    int countByExample(LongHuTypeExample example);

    int deleteByExample(LongHuTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LongHuType record);

    int insertSelective(LongHuType record);

    List<LongHuType> selectByExample(LongHuTypeExample example);

    LongHuType selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LongHuType record, @Param("example") LongHuTypeExample example);

    int updateByExample(@Param("record") LongHuType record, @Param("example") LongHuTypeExample example);

    int updateByPrimaryKeySelective(LongHuType record);

    int updateByPrimaryKey(LongHuType record);
}