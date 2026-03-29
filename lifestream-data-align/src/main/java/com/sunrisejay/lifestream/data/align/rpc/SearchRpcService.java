package com.sunrisejay.lifestream.data.align.rpc;

import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.api.SearchFeignApi;
import com.sunrisejay.lifestream.dto.RebuildNoteDocumentReqDTO;
import com.sunrisejay.lifestream.dto.RebuildUserDocumentReqDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Search RPC facade for triggering document rebuild.
 */
@Service
@Slf4j
public class SearchRpcService {

    @Resource
    private SearchFeignApi searchFeignApi;

    /**
     * 调用重建用户文档接口
     *
     * @param userId 用户 ID
     */
    public void rebuildUserDocument(Long userId) {
        try {
            RebuildUserDocumentReqDTO reqDTO = RebuildUserDocumentReqDTO.builder()
                    .id(userId)
                    .build();
            Response<?> response = searchFeignApi.rebuildUserDocument(reqDTO);
            if (response == null || !response.isSuccess()) {
                log.warn("==> 调用 search 重建用户文档失败, userId: {}, rsp: {}", userId, response);
            }
        } catch (Exception e) {
            log.error("==> 调用 search 重建用户文档异常, userId: {}", userId, e);
        }
    }

    /**
     * 调用重建笔记文档接口
     *
     * @param noteId 笔记 ID
     */
    public void rebuildNoteDocument(Long noteId) {
        try {
            RebuildNoteDocumentReqDTO reqDTO = RebuildNoteDocumentReqDTO.builder()
                    .id(noteId)
                    .build();
            Response<?> response = searchFeignApi.rebuildNoteDocument(reqDTO);
            if (response == null || !response.isSuccess()) {
                log.warn("==> 调用 search 重建笔记文档失败, noteId: {}, rsp: {}", noteId, response);
            }
        } catch (Exception e) {
            log.error("==> 调用 search 重建笔记文档异常, noteId: {}", noteId, e);
        }
    }
}

