package com.sunrisejay.lifestream.user.biz.service;

import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.user.biz.model.vo.UpdateUserInfoReqVO;
import com.sunrisejay.lifestream.user.dto.req.*;
import com.sunrisejay.lifestream.user.dto.resp.FindUserByIdRspDTO;
import com.sunrisejay.lifestream.user.dto.resp.FindUserByMailRspDTO;

import java.util.List;

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
     * @param findUserByMailReqDTO
     * @return
     */
    Response<FindUserByMailRspDTO> findByMail(FindUserByMailReqDTO findUserByMailReqDTO);
    /**
     * 更新密码
     *
     * @param updateUserPasswordReqDTO
     * @return
     */
    Response<?> updatePassword(UpdateUserPasswordReqDTO updateUserPasswordReqDTO);
    /**
     * 根据用户 ID 查询用户信息
     *
     * @param findUserByIdReqDTO
     * @return
     */
    Response<FindUserByIdRspDTO> findById(FindUserByIdReqDTO findUserByIdReqDTO);
    /**
     * 批量根据用户 ID 查询用户信息
     *
     * @param findUsersByIdsReqDTO
     * @return
     */
    Response<List<FindUserByIdRspDTO>> findByIds(FindUsersByIdsReqDTO findUsersByIdsReqDTO);
}