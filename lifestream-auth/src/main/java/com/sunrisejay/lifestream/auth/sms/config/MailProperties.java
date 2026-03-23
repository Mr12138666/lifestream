package com.sunrisejay.lifestream.auth.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 邮件配置属性类
 * 对标 AliyunAccessKeyProperties，负责从配置文件读取邮件相关配置
 */
@Data
@Component
@ConfigurationProperties(prefix = "lifestream.mail")
public class MailProperties {

    /**
     * 发件人邮箱地址
     */
    private String account;

    /**
     * 发件人邮箱授权码
     */
    private String password;

    /**
     * SMTP 服务器地址
     */
    private String smtpHost;

    /**
     * SMTP 端口号
     */
    private String smtpPort;

    /**
     * 是否启用 SSL
     */
    private Boolean sslEnable = true;

    /**
     * 发送超时时间（毫秒）
     */
    private Integer timeout = 10000;

    /**
     * 发件人昵称
     */
    private String nickname;

    /**
     * 邮件协议
     */
    private String protocol = "smtp";
}
