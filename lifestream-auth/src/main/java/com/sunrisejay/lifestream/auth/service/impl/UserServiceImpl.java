package com.sunrisejay.lifestream.auth.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.google.common.collect.Lists;
import com.sunrisejay.framework.common.exception.BizException;
import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.framework.common.util.JsonUtils;
import com.sunrisejay.lifestream.auth.constant.RedisKeyConstants;
import com.sunrisejay.lifestream.auth.constant.RoleConstants;
import com.sunrisejay.lifestream.auth.domain.dataobject.UserDO;
import com.sunrisejay.lifestream.auth.domain.dataobject.UserRoleDO;
import com.sunrisejay.lifestream.auth.domain.mapper.UserDOMapper;
import com.sunrisejay.lifestream.auth.domain.mapper.UserRoleDOMapper;
import com.sunrisejay.lifestream.auth.enums.DeletedEnum;
import com.sunrisejay.lifestream.auth.enums.LoginTypeEnum;
import com.sunrisejay.lifestream.auth.enums.ResponseCodeEnum;
import com.sunrisejay.lifestream.auth.enums.StatusEnum;
import com.sunrisejay.lifestream.auth.model.vo.user.UserLoginReqVO;
import com.sunrisejay.lifestream.auth.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserDOMapper userDOMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private UserRoleDOMapper userRoleDOMapper;

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

                // 校验入参验证码是否为空
                if (StringUtils.isBlank(verificationCode)) {
                    return Response.fail(ResponseCodeEnum.PARAM_NOT_VALID.getErrorCode(), "验证码不能为空");
                }

                // 构建验证码 Redis Key
                String key = RedisKeyConstants.buildVerificationCodeKey(mail);
                // 查询存储在 Redis 中该用户的登录验证码
                String sentCode = (String) redisTemplate.opsForValue().get(key);

                // 判断用户提交的验证码，与 Redis 中的验证码是否一致
                if (!StringUtils.equals(verificationCode, sentCode)) {
                    throw new BizException(ResponseCodeEnum.VERIFICATION_CODE_ERROR);
                }

                // 通过邮箱号查询记录
                UserDO userDO = userDOMapper.selectByMail(mail);

                log.info("==> 用户是否注册, mail: {}, userDO: {}", mail, JsonUtils.toJsonString(userDO));

                // 判断是否注册
                if (Objects.isNull(userDO)) {
                    // 若此用户还没有注册，系统自动注册该用户
                    userId = registerUser(mail);
                    
                } else {
                	// 已注册，则获取其用户 ID
                    userId = userDO.getId();
                }
                break;
            case PASSWORD: // 密码登录
                // todo

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
     * 系统自动注册用户
     * @param mail
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long registerUser(String mail) {
        // 获取全局自增的时光溪 ID
        Long lifestreamId = redisTemplate.opsForValue().increment(RedisKeyConstants.LIFESTREAM_ID_GENERATOR_KEY);

        UserDO userDO = UserDO.builder()
                .mail(mail)
                .lifestreamId(String.valueOf(lifestreamId)) // 自动生成小红书号 ID
                .nickname("小溪流" + lifestreamId) // 自动生成昵称, 如：小溪流10000
                .status(StatusEnum.ENABLE.getValue()) // 状态为启用
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .isDeleted(DeletedEnum.NO.getValue()) // 逻辑删除
                .build();

        // 添加入库
        userDOMapper.insert(userDO);

        // 获取刚刚添加入库的用户 ID
        Long userId = userDO.getId();

        // 给该用户分配一个默认角色
        UserRoleDO userRoleDO = UserRoleDO.builder()
                .userId(userId)
                .roleId(RoleConstants.COMMON_USER_ROLE_ID)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .isDeleted(DeletedEnum.NO.getValue())
                .build();
        userRoleDOMapper.insert(userRoleDO);

        // 将该用户的角色 ID 存入 Redis 中
        List<Long> roles = Lists.newArrayList();
        roles.add(RoleConstants.COMMON_USER_ROLE_ID);
        String userRolesKey = RedisKeyConstants.buildUserRoleKey(mail);
        redisTemplate.opsForValue().set(userRolesKey, JsonUtils.toJsonString(roles));

        return userId;
    }

}