package com.sunrisejay.lifestream.auth.sms.config;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * 邮件客户端配置类
 * 对标 AliyunSmsClientConfig，负责创建邮件发送客户端
 */
@Configuration
@Slf4j
public class MailClientConfig {

    @Resource
    private MailProperties mailProperties;

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // 设置邮件协议
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", mailProperties.getProtocol());
        properties.setProperty("mail.smtp.host", mailProperties.getSmtpHost());
        properties.setProperty("mail.smtp.port", mailProperties.getSmtpPort());
        properties.setProperty("mail.smtp.auth", "true");

        // SSL 配置
        if (Boolean.TRUE.equals(mailProperties.getSslEnable())) {
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        }

        // 超时配置
        if (mailProperties.getTimeout() != null) {
            properties.setProperty("mail.smtp.connectiontimeout", String.valueOf(mailProperties.getTimeout()));
            properties.setProperty("mail.smtp.timeout", String.valueOf(mailProperties.getTimeout()));
            properties.setProperty("mail.smtp.writetimeout", String.valueOf(mailProperties.getTimeout()));
        }

        mailSender.setJavaMailProperties(properties);
        mailSender.setUsername(mailProperties.getAccount());
        mailSender.setPassword(mailProperties.getPassword());

        log.info("==> 邮件客户端初始化完成, smtpHost: {}, smtpPort: {}, account: {}",
                mailProperties.getSmtpHost(), mailProperties.getSmtpPort(), mailProperties.getAccount());

        return mailSender;
    }
}
