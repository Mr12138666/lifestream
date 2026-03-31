package com.sunrisejay.lifestream.comment.biz.domain.mapper;

import com.sunrisejay.lifestream.comment.biz.domain.dataobject.NoteCountDO;
import org.apache.ibatis.annotations.Param;

public interface NoteCountDOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NoteCountDO record);

    int insertSelective(NoteCountDO record);

    NoteCountDO selectByPrimaryKey(Long id);

    /**
     * 查询笔记评论总数
     * @param noteId
     * @return
     */
    Long selectCommentTotalByNoteId(Long noteId);
    /**
     * 更新评论总数
     * @param noteId
     * @param count
     * @return
     */
    int updateCommentTotalByNoteId(@Param("noteId") Long noteId,
                                   @Param("count") int count);
    int updateByPrimaryKeySelective(NoteCountDO record);

    int updateByPrimaryKey(NoteCountDO record);
}