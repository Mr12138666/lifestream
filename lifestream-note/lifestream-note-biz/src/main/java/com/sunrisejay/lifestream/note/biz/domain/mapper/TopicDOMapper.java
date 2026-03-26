package com.sunrisejay.lifestream.note.biz.domain.mapper;

import com.sunrisejay.lifestream.note.biz.domain.dataobject.TopicDO;

public interface TopicDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TopicDO record);

    int insertSelective(TopicDO record);

    TopicDO selectByPrimaryKey(Long id);
    String selectNameByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(TopicDO record);

    int updateByPrimaryKey(TopicDO record);
}