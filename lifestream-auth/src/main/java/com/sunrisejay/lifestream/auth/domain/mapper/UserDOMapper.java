package com.sunrisejay.lifestream.auth.domain.mapper;

import com.sunrisejay.lifestream.auth.domain.dataobject.UserDO;

/**
 * @description:
 * @author: Sunrise_Jay
 * @email: sunrise_jay@yeah.net
 * @date: 2026/3/17 22:46
 */
public interface UserDOMapper {

    /**
     * 根据主键 ID 查询
     * @param id
     * @return
     */
    UserDO selectByPrimaryKey(Long id);

    /**
     * 根据主键 ID 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入记录
     * @param record
     * @return
     */
    int insert(UserDO record);

    /**
     * 更新记录
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserDO record);
}