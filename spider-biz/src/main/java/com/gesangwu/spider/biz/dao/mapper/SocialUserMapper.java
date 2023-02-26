package com.gesangwu.spider.biz.dao.mapper;

import com.gandalf.framework.mybatis.BaseMapper;
import com.gesangwu.spider.biz.dao.model.SocialUser;
import com.gesangwu.spider.biz.dao.model.SocialUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SocialUserMapper extends BaseMapper {
    int countByExample(SocialUserExample example);

    int deleteByExample(SocialUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SocialUser record);

    int insertSelective(SocialUser record);

    List<SocialUser> selectByExample(SocialUserExample example);

    SocialUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SocialUser record, @Param("example") SocialUserExample example);

    int updateByExample(@Param("record") SocialUser record, @Param("example") SocialUserExample example);

    int updateByPrimaryKeySelective(SocialUser record);

    int updateByPrimaryKey(SocialUser record);
}