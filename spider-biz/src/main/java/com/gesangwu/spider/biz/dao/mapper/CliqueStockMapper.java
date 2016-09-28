package com.gesangwu.spider.biz.dao.mapper;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.CliqueStock;
import com.gesangwu.spider.biz.dao.model.CliqueStockExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CliqueStockMapper extends BaseMapper {
    int countByExample(CliqueStockExample example);

    int deleteByExample(CliqueStockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(CliqueStock record);

    int insertSelective(CliqueStock record);

    List<CliqueStock> selectByExample(CliqueStockExample example);

    CliqueStock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") CliqueStock record, @Param("example") CliqueStockExample example);

    int updateByExample(@Param("record") CliqueStock record, @Param("example") CliqueStockExample example);

    int updateByPrimaryKeySelective(CliqueStock record);

    int updateByPrimaryKey(CliqueStock record);
}