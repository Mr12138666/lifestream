package com.sunrisejay.lifestream.auth.sms;

import com.sunrisejay.lifestream.auth.config.MailProperties;
import com.sunrisejay.lifestream.auth.constant.MailKeyConstants;
import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 邮件帮助类
 * 对标 AliyunSmsHelper，负责业务层面的邮件发送逻辑
 * 包含验证码生成、存储、发送等完整流程
 */
@Slf4j
@Component
public class MailHelper {

    @Resource
    private JavaMailSender mailSender;

    @Resource
    private MailProperties mailProperties;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 发送邮箱验证码
     *
     * @param mail       目标邮箱
     * @param verifyCode 验证码
     * @return 是否发送成功
     */
    public boolean sendVerifyCode(String mail, String verifyCode) {
        String title = MailKeyConstants.MAIL_VERIFY_CODE_TITLE;
        String content = buildVerifyCodeContent(verifyCode);

        boolean success = sendEmail(Set.of(mail), title, content);

//        if (success) {
//            storeVerifyCode(mail, verifyCode);
//        }

        return success;
    }

    /**
     * 校验邮箱验证码
     *
     * @param mail       邮箱
     * @param verifyCode 用户输入的验证码
     * @return 验证码是否正确
     */
    public boolean verifyCode(String mail, String verifyCode) {
        String key = MailKeyConstants.getMailVerifyCodeKey(mail);
        String cachedCode = stringRedisTemplate.opsForValue().get(key);

        if (cachedCode == null) {
            log.warn("==> 验证码已过期或不存在, mail: {}", mail);
            return false;
        }

        if (!cachedCode.equals(verifyCode)) {
            log.warn("==> 验证码不匹配, mail: {}, input: {}, cached: {}", mail, verifyCode, cachedCode);
            return false;
        }

        // 验证成功后删除验证码（一次性使用）
        stringRedisTemplate.delete(key);
        log.info("==> 验证码校验成功并已删除, mail: {}", mail);
        return true;
    }

    /**
     * 发送普通邮件（不带验证码逻辑）
     *
     * @param email   目标邮箱
     * @param title   邮件标题
     * @param content 邮件内容（支持 HTML）
     * @return 是否发送成功
     */
    public boolean sendMail(String email, String title, String content) {
        return sendEmail(Set.of(email), title, content);
    }

    /**
     * 发送邮件
     */
    private boolean sendEmail(Set<String> emails, String title, String content) {
        if (emails == null || emails.isEmpty()) {
            log.warn("==> 发送邮件失败: 收件人列表为空");
            return false;
        }

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(mailProperties.getAccount(), mailProperties.getNickname());
            helper.setTo(emails.toArray(new String[0]));
            helper.setSubject(title);
            helper.setText(content, true);

            log.info("==> 开始发送邮件, recipients: {}, title: {}", emails, title);
            mailSender.send(message);
            log.info("==> 邮件发送成功, recipients: {}", emails);

            return true;
        } catch (MessagingException e) {
            log.error("==> 邮件发送失败, MessagingException: ", e);
            return false;
        } catch (Exception e) {
            log.error("==> 邮件发送失败, 系统错误: ", e);
            return false;
        }
    }

    /**
     * 存储验证码到 Redis
     */
    private void storeVerifyCode(String mail, String verifyCode) {
        String key = MailKeyConstants.getMailVerifyCodeKey(mail);
        stringRedisTemplate.opsForValue().set(
                key,
                verifyCode,
                MailKeyConstants.MAIL_VERIFY_CODE_EXPIRE_SECONDS,
                TimeUnit.SECONDS
        );
        log.info("==> 邮箱验证码已存储, mail: {}, expireSeconds: {}",
                mail, MailKeyConstants.MAIL_VERIFY_CODE_EXPIRE_SECONDS);
    }

    /**
     * 构建验证码邮件内容
     */
    private String buildVerifyCodeContent(String verifyCode) {
        long expireMinutes = MailKeyConstants.MAIL_VERIFY_CODE_EXPIRE_SECONDS / 60;
        return String.format("""
            <!DOCTYPE html>
            <html>
            <head>
                <meta charset="UTF-8">
                <style>
                    body { font-family: Arial, sans-serif; background-color: #f5f5f5; }
                    .container { max-width: 500px; margin: 50px auto; padding: 20px; background: white; border-radius: 10px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
                    .header { text-align: center; color: #333; }
                    .code-box { text-align: center; margin: 30px 0; padding: 20px; background: #f0f7ff; border-radius: 8px; }
                    .code { font-size: 32px; font-weight: bold; color: #1890ff; letter-spacing: 8px; }
                    .tip { text-align: center; color: #999; font-size: 14px; }
                    .footer { text-align: center; margin-top: 20px; color: #999; font-size: 12px; }
                </style>
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h2>【时光溪】验证码</h2>
                    </div>
                    <div class="code-box">
                        <span class="code">%s</span>
                    </div>
                    <p class="tip">验证码 %d 分钟内有效，请勿泄露给他人</p>
                    <div class="footer">
                        <p>此邮件由系统自动发送，请勿回复</p>
                    </div>
                </div>
            </body>
            </html>
            """, verifyCode, expireMinutes);
    }
}
