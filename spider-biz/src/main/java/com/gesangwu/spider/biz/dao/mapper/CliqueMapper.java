package com.gesangwu.spider.biz.dao.mapper;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.Clique;
import com.gesangwu.spider.biz.dao.model.CliqueExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CliqueMapper extends BaseMapper {
    int countByExample(CliqueExample example);

    int deleteByExample(CliqueExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Clique record);

    int insertSelective(Clique record);

    List<Clique> selectByExample(CliqueExample example);

    Clique selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Clique record, @Param("example") CliqueExample example);

    int updateByExample(@Param("record") Clique record, @Param("example") CliqueExample example);

    int updateByPrimaryKeySelective(Clique record);

    int updateByPrimaryKey(Clique record);
}