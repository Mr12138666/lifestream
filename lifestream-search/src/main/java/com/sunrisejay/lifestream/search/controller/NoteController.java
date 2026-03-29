package com.sunrisejay.lifestream.search.controller;

import com.sunrisejay.framework.biz.operationlog.aspect.ApiOperationLog;
import com.sunrisejay.framework.common.response.PageResponse;
import com.sunrisejay.lifestream.search.model.vo.SearchNoteReqVO;
import com.sunrisejay.lifestream.search.model.vo.SearchNoteRspVO;
import com.sunrisejay.lifestream.search.service.NoteService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: 犬小哈
 * @date: 2024/4/4 13:22
 * @version: v1.0.0
 * @description: 笔记搜索
 **/
@RestController
@RequestMapping("/search")
@Slf4j
public class NoteController {

    @Resource
    private NoteService noteService;

    @PostMapping("/note")
    @ApiOperationLog(description = "搜索笔记")
    public PageResponse<SearchNoteRspVO> searchNote(@RequestBody @Validated SearchNoteReqVO searchNoteReqVO) {
        return noteService.searchNote(searchNoteReqVO);
    }

}
