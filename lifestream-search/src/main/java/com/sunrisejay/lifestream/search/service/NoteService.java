package com.sunrisejay.lifestream.search.service;

import com.sunrisejay.framework.common.response.PageResponse;
import com.sunrisejay.lifestream.search.model.vo.SearchNoteReqVO;
import com.sunrisejay.lifestream.search.model.vo.SearchNoteRspVO;

/**
 * @author: 犬小哈
 * @date: 2024/4/7 15:41
 * @version: v1.0.0
 * @description: 笔记搜索业务
 **/
public interface NoteService {

    /**
     * 搜索笔记
     * @param searchNoteReqVO
     * @return
     */
    PageResponse<SearchNoteRspVO> searchNote(SearchNoteReqVO searchNoteReqVO);
}
