package com.sunrisejay.lifestream.auth.sms.constant;

/**
 * 邮件相关常量
 * 对标 RedisKeyConstants，定义邮件验证码相关的 Key 前缀和过期时间
 */
public class MailKeyConstants {

    /**
     * 邮件验证码 Key 前缀
     * 完整格式: mail:verify_code:{mail}
     */
    public static final String MAIL_VERIFY_CODE_PREFIX = "mail:verify_code:";

    /**
     * 邮件验证码有效期（秒）
     * 默认 5 分钟
     */
    public static final Long MAIL_VERIFY_CODE_EXPIRE_SECONDS = 300L;

    /**
     * 邮件验证码长度
     */
    public static final int VERIFY_CODE_LENGTH = 6;

    /**
     * 邮件验证码格式：纯数字
     */
    public static final String VERIFY_CODE_PATTERN = "\\d{" + VERIFY_CODE_LENGTH + "}";

    /**
     * 邮件验证码标题
     */
    public static final String MAIL_VERIFY_CODE_TITLE = "【时光溪】您的验证码";

    /**
     * 获取邮件验证码的 Redis Key
     *
     * @param mail 邮箱地址
     * @return 完整的 Redis Key
     */
    public static String getMailVerifyCodeKey(String mail) {
        return MAIL_VERIFY_CODE_PREFIX + mail;
    }
}
