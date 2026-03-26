package com.sunrisejay.lifestream.auth.constant;

public class RedisKeyConstants {

    /**
     * 验证码 KEY 前缀
     */
    private static final String VERIFICATION_CODE_KEY_PREFIX = "verification_code:";
    public static final String LIFESTREAM_ID_GENERATOR_KEY = "lifestream.id.generator";
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
    /**
     * 用户对应的角色集合 KEY
     * @param userId
     * @return
     */
    public static String buildUserRoleKey(Long userId) {
        return USER_ROLES_KEY_PREFIX + userId;
    }
    /**
     * 角色对应的权限集合 KEY 前缀
     */
    private static final String ROLE_PERMISSIONS_KEY_PREFIX = "role:permissions:";


    /**
     * 构建角色对应的权限集合 KEY
     * @param roleKey
     * @return
     */
    public static String buildRolePermissionsKey(String roleKey) {
        return ROLE_PERMISSIONS_KEY_PREFIX + roleKey;
    }
}