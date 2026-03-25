package com.sunrisejay.lifestream.user.biz.service;

import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.user.biz.model.vo.UpdateUserInfoReqVO;

public interface UserService {

    /**
     * 更新用户信息
     *
     * @param updateUserInfoReqVO
     * @return
     */
    Response<?> updateUserInfo(UpdateUserInfoReqVO updateUserInfoReqVO);
}