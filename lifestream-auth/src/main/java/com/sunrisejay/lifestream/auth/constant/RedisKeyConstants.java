package com.sunrisejay.lifestream.auth.constant;

public class RedisKeyConstants {

    /**
     * 验证码 KEY 前缀
     */
    private static final String VERIFICATION_CODE_KEY_PREFIX = "verification_code:";

    /**
     * 构建验证码 KEY
     * @param mail
     * @return
     */
    public static String buildVerificationCodeKey(String mail) {
        return VERIFICATION_CODE_KEY_PREFIX + mail;
    }
}