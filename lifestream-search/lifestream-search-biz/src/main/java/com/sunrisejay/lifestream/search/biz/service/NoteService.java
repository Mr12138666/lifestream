package com.sunrisejay.lifestream.search.biz.service;

import com.sunrisejay.framework.common.response.PageResponse;
import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.dto.RebuildNoteDocumentReqDTO;
import com.sunrisejay.lifestream.search.biz.model.vo.SearchNoteReqVO;
import com.sunrisejay.lifestream.search.biz.model.vo.SearchNoteRspVO;

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

    /**
     * 重建笔记文档
     * @param rebuildNoteDocumentReqDTO
     * @return
     */
    Response<Long> rebuildDocument(RebuildNoteDocumentReqDTO rebuildNoteDocumentReqDTO);
}
