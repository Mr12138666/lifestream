package com.sunrisejay.lifestream.note.biz.service;

import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.note.biz.model.vo.PublishNoteReqVO;

public interface NoteService {

    /**
     * 笔记发布
     * @param publishNoteReqVO
     * @return
     */
    Response<?> publishNote(PublishNoteReqVO publishNoteReqVO);

}