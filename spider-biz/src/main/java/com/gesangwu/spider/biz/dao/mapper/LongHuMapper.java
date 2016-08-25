package com.gesangwu.spider.biz.dao.mapper;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.LongHu;
import com.gesangwu.spider.biz.dao.model.LongHuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LongHuMapper extends BaseMapper {
    int countByExample(LongHuExample example);

    int deleteByExample(LongHuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LongHu record);

    int insertSelective(LongHu record);

    List<LongHu> selectByExample(LongHuExample example);

    LongHu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LongHu record, @Param("example") LongHuExample example);

    int updateByExample(@Param("record") LongHu record, @Param("example") LongHuExample example);

    int updateByPrimaryKeySelective(LongHu record);

    int updateByPrimaryKey(LongHu record);
}