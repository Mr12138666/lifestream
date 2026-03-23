package com.sunrisejay.lifestream.auth.service;

import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.auth.model.vo.verificationcode.SendVerificationCodeReqVO;

public interface VerificationCodeService {

    /**
     * 发送短信验证码
     *
     * @param sendVerificationCodeReqVO
     * @return
     */
    Response<?> send(SendVerificationCodeReqVO sendVerificationCodeReqVO);
}