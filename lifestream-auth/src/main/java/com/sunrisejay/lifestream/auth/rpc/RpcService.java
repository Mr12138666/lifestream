package com.sunrisejay.lifestream.auth.rpc;

import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.user.api.UserFeignApi;
import com.sunrisejay.lifestream.user.dto.req.FindUserByMailReqDTO;
import com.sunrisejay.lifestream.user.dto.req.RegisterUserReqDTO;
import com.sunrisejay.lifestream.user.dto.req.UpdateUserPasswordReqDTO;
import com.sunrisejay.lifestream.user.dto.resp.FindUserByMailRspDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class RpcService {

    @Resource
    private UserFeignApi userFeignApi;

    /**
     * 用户注册
     *
     * @param mail
     * @return
     */
    public Long registerUser(String mail) {
        RegisterUserReqDTO registerUserReqDTO = new RegisterUserReqDTO();
        registerUserReqDTO.setMail(mail);

        Response<Long> response = userFeignApi.registerUser(registerUserReqDTO);

        if (!response.isSuccess()) {
            return null;
        }

        return response.getData();
    }
    /**
     * 密码更新
     *
     * @param encodePassword
     */
    public void updatePassword(String encodePassword) {
        UpdateUserPasswordReqDTO updateUserPasswordReqDTO = new UpdateUserPasswordReqDTO();
        updateUserPasswordReqDTO.setEncodePassword(encodePassword);

        userFeignApi.updatePassword(updateUserPasswordReqDTO);
    }
    /**
     * 根据邮箱查询用户信息
     *
     * @param Mail
     * @return
     */
    public FindUserByMailRspDTO findUserByMail(String Mail) {
        FindUserByMailReqDTO findUserByMailReqDTO = new FindUserByMailReqDTO();
        findUserByMailReqDTO.setMail(Mail);

        Response<FindUserByMailRspDTO> response = userFeignApi.findByMail(findUserByMailReqDTO);

        if (!response.isSuccess()) {
            return null;
        }

        return response.getData();
    }

}