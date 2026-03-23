package com.sunrisejay.lifestream.auth.service;

import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.auth.model.vo.user.UserLoginReqVO;

public interface UserService {

    /**
     * 登录与注册
     * @param userLoginReqVO
     * @return
     */
    Response<String> loginAndRegister(UserLoginReqVO userLoginReqVO);
}
