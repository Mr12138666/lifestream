package com.sunrisejay.lifestream.auth;

import com.sunrisejay.lifestream.auth.sms.MailHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class MailTests {

    @Resource
    private MailHelper mailHelper;

    /**
     * 测试发送邮件
     */
    @Test
    void testSendMail() {
        boolean success = mailHelper.sendVerifyCode("1243532088@qq.com", "123456");
        log.info("==> 邮件发送结果: {}", success ? "成功" : "失败");
    }

    /**
     * 测试发送简单邮件（不使用验证码）
     */
    @Test
    void testSendSimpleMail() {

        mailHelper.sendMail("2326953526@qq.com","该吃饭了幸运星小姐","密码的，人呢");
    }
}
