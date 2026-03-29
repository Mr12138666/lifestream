package com.sunrisejay.lifestream.search.service;

import com.sunrisejay.framework.common.response.PageResponse;
import com.sunrisejay.lifestream.search.model.vo.SearchUserReqVO;
import com.sunrisejay.lifestream.search.model.vo.SearchUserRspVO;

/**
 * @author: 犬小哈
 * @date: 2024/4/7 15:41
 * @version: v1.0.0
 * @description: 用户搜索业务
 **/
public interface UserService {

    /**
     * 搜索用户
     * @param searchUserReqVO
     * @return
     */
    PageResponse<SearchUserRspVO> searchUser(SearchUserReqVO searchUserReqVO);
}
