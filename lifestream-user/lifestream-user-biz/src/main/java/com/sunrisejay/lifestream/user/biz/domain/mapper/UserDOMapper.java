package com.sunrisejay.lifestream.user.biz.domain.mapper;

import com.sunrisejay.lifestream.user.biz.domain.dataobject.UserDO;

public interface UserDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Long id);

    /**
     * 根据手机号查询记录
     * @param mail
     * @return
     */
    UserDO selectByMail(String mail);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);
}