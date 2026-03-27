package com.sunrisejay.lifestream.note.biz.domain.mapper;

import com.sunrisejay.lifestream.note.biz.domain.dataobject.NoteDO;

public interface NoteDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NoteDO record);

    int insertSelective(NoteDO record);

    NoteDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NoteDO record);
    int selectCountByNoteId(Long noteId);
    int updateByPrimaryKey(NoteDO record);
    int updateVisibleOnlyMe(NoteDO noteDO);
    int updateIsTop(NoteDO noteDO);

    /**
     * 查询笔记的发布者用户 ID
     * @param noteId
     * @return
     */
    Long selectCreatorIdByNoteId(Long noteId);
}