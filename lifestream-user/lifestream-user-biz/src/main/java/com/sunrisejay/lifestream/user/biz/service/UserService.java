package com.sunrisejay.lifestream.user.biz.service;

import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.user.biz.model.vo.UpdateUserInfoReqVO;
import com.sunrisejay.lifestream.user.dto.req.FindUserByMailReqDTO;
import com.sunrisejay.lifestream.user.dto.req.RegisterUserReqDTO;
import com.sunrisejay.lifestream.user.dto.resp.FindUserByMailRspDTO;

public interface UserService {

    /**
     * 更新用户信息
     *
     * @param updateUserInfoReqVO
     * @return
     */
    Response<?> updateUserInfo(UpdateUserInfoReqVO updateUserInfoReqVO);

    /**
     * 用户注册
     *
     * @param registerUserReqDTO
     * @return
     */
    Response<Long> register(RegisterUserReqDTO registerUserReqDTO);
    /**
     * 根据手机号查询用户信息
     *
     * @param findUserByMailRspDTO
     * @return
     */
    Response<FindUserByMailRspDTO> findByMail(FindUserByMailReqDTO findUserByMailReqDTO);
}