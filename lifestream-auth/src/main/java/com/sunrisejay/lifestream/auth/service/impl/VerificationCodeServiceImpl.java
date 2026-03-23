package com.sunrisejay.lifestream.auth.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.sunrisejay.framework.common.exception.BizException;
import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.auth.constant.RedisKeyConstants;
import com.sunrisejay.lifestream.auth.enums.ResponseCodeEnum;
import com.sunrisejay.lifestream.auth.model.vo.verificationcode.SendVerificationCodeReqVO;
import com.sunrisejay.lifestream.auth.service.VerificationCodeService;
import com.sunrisejay.lifestream.auth.sms.MailHelper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private MailHelper mailHelper;

    /**
     * 发送短信验证码
     *
     * @param sendVerificationCodeReqVO
     * @return
     */
    @Override
    public Response<?> send(SendVerificationCodeReqVO sendVerificationCodeReqVO) {
        // 邮箱
        String mail = sendVerificationCodeReqVO.getMail();

        // 构建验证码 redis key
        String key = RedisKeyConstants.buildVerificationCodeKey(mail);

        // 判断是否已发送验证码
        boolean isSent = redisTemplate.hasKey(key);
        if (isSent) {
            // 若之前发送的验证码未过期，则提示发送频繁
            throw new BizException(ResponseCodeEnum.VERIFICATION_CODE_SEND_FREQUENTLY);
        }

        // 生成 6 位随机数字验证码
        String verificationCode = RandomUtil.randomNumbers(6);

        mailHelper.sendVerifyCode(mail, verificationCode);

        log.info("==> 邮箱: {}, 已发送验证码：【{}】", mail, verificationCode);

        // 存储验证码到 redis, 并设置过期时间为 3 分钟
        redisTemplate.opsForValue().set(key, verificationCode, 3, TimeUnit.MINUTES);

        return Response.success();
    }
}