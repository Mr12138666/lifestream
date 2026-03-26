package com.sunrisejay.lifestream.auth.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.google.common.base.Preconditions;
import com.sunrisejay.framework.biz.context.holder.LoginUserContextHolder;
import com.sunrisejay.framework.common.exception.BizException;
import com.sunrisejay.framework.common.response.Response;

import com.sunrisejay.lifestream.auth.constant.RedisKeyConstants;
import com.sunrisejay.lifestream.auth.domain.dataobject.UserDO;

import com.sunrisejay.lifestream.auth.domain.mapper.UserDOMapper;

import com.sunrisejay.lifestream.auth.enums.LoginTypeEnum;
import com.sunrisejay.lifestream.auth.enums.ResponseCodeEnum;
import com.sunrisejay.lifestream.auth.model.vo.user.UpdatePasswordReqVO;
import com.sunrisejay.lifestream.auth.model.vo.user.UserLoginReqVO;
import com.sunrisejay.lifestream.auth.rpc.UserRpcService;
import com.sunrisejay.lifestream.auth.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Objects;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    private UserRpcService userRpcService;
    @Resource
    private UserDOMapper userDOMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 登录与注册
     *
     * @param userLoginReqVO
     * @return
     */
    @Override
    public Response<String> loginAndRegister(UserLoginReqVO userLoginReqVO) {
        String mail = userLoginReqVO.getMail();
        Integer type = userLoginReqVO.getType();

        LoginTypeEnum loginTypeEnum = LoginTypeEnum.valueOf(type);

        Long userId = null;

        // 判断登录类型
        switch (loginTypeEnum) {
            case VERIFICATION_CODE: // 验证码登录
                String verificationCode = userLoginReqVO.getCode();

                Preconditions.checkArgument(StringUtils.isNotBlank(verificationCode), "验证码不能为空");

                // 构建验证码 Redis Key
                String key = RedisKeyConstants.buildVerificationCodeKey(mail);
                // 查询存储在 Redis 中该用户的登录验证码
                String sentCode = (String) redisTemplate.opsForValue().get(key);

                // 判断用户提交的验证码，与 Redis 中的验证码是否一致
                if (!StringUtils.equals(verificationCode, sentCode)) {
                    throw new BizException(ResponseCodeEnum.VERIFICATION_CODE_ERROR);
                }

                // RPC: 调用用户服务，注册用户
                Long userIdTmp = userRpcService.registerUser(mail);

                // 若调用用户服务，返回的用户 ID 为空，则提示登录失败
                if (Objects.isNull(userIdTmp)) {
                    throw new BizException(ResponseCodeEnum.LOGIN_FAIL);
                }

                userId = userIdTmp;
                break;
            case PASSWORD: // 密码登录
                String rawPassword = userLoginReqVO.getPassword();

                UserDO userDO1 = userDOMapper.selectByMail(mail);
                if (Objects.isNull(userDO1)) {
                    throw new BizException(ResponseCodeEnum.USER_NOT_FOUND);
                }
                String encodedPassword = userDO1.getPassword();
                if (!passwordEncoder.matches(rawPassword,encodedPassword)) {
                    throw new BizException(ResponseCodeEnum.MAIL_OR_PASSWORD_ERROR);
                }
                userId = userDO1.getId();
                break;
            default:
                break;
        }

        // SaToken 登录用户，并返回 token 令牌
        // SaToken 登录用户, 入参为用户 ID
        StpUtil.login(userId);

        // 获取 Token 令牌
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        // 返回 Token 令牌
        return Response.success(tokenInfo.tokenValue);

    }

    /**
     * 退出登录
     *
     * @return
     */
    @Override
    public Response<?> logout() {
        Long userId = LoginUserContextHolder.getUserId();
        // 退出登录 (指定用户 ID)
        StpUtil.logout(userId);

        return Response.success();
    }



    @Override
    public Response<?> updatePassword(UpdatePasswordReqVO updatePasswordReqVO) {
        // 新密码
        String newPassword = updatePasswordReqVO.getNewPassword();
        // 密码加密
        String encodePassword = passwordEncoder.encode(newPassword);

        // 获取当前请求对应的用户 ID
        Long userId = LoginUserContextHolder.getUserId();

        UserDO userDO = UserDO.builder()
                .id(userId)
                .password(encodePassword)
                .updateTime(LocalDateTime.now())
                .build();
        // 更新密码
        userDOMapper.updateByPrimaryKeySelective(userDO);

        return Response.success();
    }
}