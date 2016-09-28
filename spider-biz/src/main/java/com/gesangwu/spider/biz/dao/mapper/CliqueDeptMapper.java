package com.gesangwu.spider.biz.dao.mapper;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.CliqueDept;
import com.gesangwu.spider.biz.dao.model.CliqueDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CliqueDeptMapper extends BaseMapper {
    int countByExample(CliqueDeptExample example);

    int deleteByExample(CliqueDeptExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CliqueDept record);

    int insertSelective(CliqueDept record);

    List<CliqueDept> selectByExample(CliqueDeptExample example);

    CliqueDept selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CliqueDept record, @Param("example") CliqueDeptExample example);

    int updateByExample(@Param("record") CliqueDept record, @Param("example") CliqueDeptExample example);

    int updateByPrimaryKeySelective(CliqueDept record);

    int updateByPrimaryKey(CliqueDept record);
}