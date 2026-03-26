package com.sunrisejay.lifestream.user.api;

import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.user.constant.ApiConstants;
import com.sunrisejay.lifestream.user.dto.req.FindUserByMailReqDTO;
import com.sunrisejay.lifestream.user.dto.req.RegisterUserReqDTO;
import com.sunrisejay.lifestream.user.dto.req.UpdateUserPasswordReqDTO;
import com.sunrisejay.lifestream.user.dto.resp.FindUserByMailRspDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = ApiConstants.SERVICE_NAME)
public interface UserFeignApi {

    String PREFIX = "/user";

    /**
     * 用户注册
     *
     * @param registerUserReqDTO
     * @return
     */
    @PostMapping(value = PREFIX + "/register")
    Response<Long> registerUser(@RequestBody RegisterUserReqDTO registerUserReqDTO);
    /**
     * 更新密码
     *
     * @param updateUserPasswordReqDTO
     * @return
     */
    @PostMapping(value = PREFIX + "/password/update")
    Response<?> updatePassword(@RequestBody UpdateUserPasswordReqDTO updateUserPasswordReqDTO);
    /**
     * 根据手机号查询用户信息
     *
     * @param findUserByMailReqDTO
     * @return
     */
    @PostMapping(value = PREFIX + "/findByMail")
    Response<FindUserByMailRspDTO> findByMail(@RequestBody FindUserByMailReqDTO findUserByMailReqDTO);

}