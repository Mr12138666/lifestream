package com.sunrisejay.lifestream.auth.rpc;

import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.user.api.UserFeignApi;
import com.sunrisejay.lifestream.user.dto.req.RegisterUserReqDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UserRpcService {

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

}