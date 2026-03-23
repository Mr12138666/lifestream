package com.sunrisejay.lifestream.auth.constant;

public class RedisKeyConstants {

    /**
     * 验证码 KEY 前缀
     */
    private static final String VERIFICATION_CODE_KEY_PREFIX = "verification_code:";
    public static final String LIFESTREAM_ID_GENERATOR_KEY = "lifestream_id_generator";
    private static final String USER_ROLES_KEY_PREFIX = "user:roles:";



    /**
     * 构建验证码 KEY
     * @param mail
     * @return
     */
    public static String buildVerificationCodeKey(String mail) {
        return VERIFICATION_CODE_KEY_PREFIX + mail;
    }
    /**
     * 构建用户-角色 Key
     * @param mail
     * @return
     */
    public static String buildUserRoleKey(String mail) {
        return USER_ROLES_KEY_PREFIX + mail;
    }
}