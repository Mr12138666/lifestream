package com.sunrisejay.lifestream.user.biz.service.impl;

import com.alibaba.nacos.shaded.com.google.common.base.Preconditions;
import com.sunrisejay.framework.biz.context.holder.LoginUserContextHolder;
import com.sunrisejay.framework.common.enums.DeletedEnum;
import com.sunrisejay.framework.common.enums.StatusEnum;
import com.sunrisejay.framework.common.exception.BizException;
import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.framework.common.util.JsonUtils;
import com.sunrisejay.framework.common.util.ParamUtils;
import com.sunrisejay.lifestream.user.biz.constant.RedisKeyConstants;
import com.sunrisejay.lifestream.user.biz.constant.RoleConstants;
import com.sunrisejay.lifestream.user.biz.domain.dataobject.RoleDO;
import com.sunrisejay.lifestream.user.biz.domain.dataobject.UserDO;
import com.sunrisejay.lifestream.user.biz.domain.dataobject.UserRoleDO;
import com.sunrisejay.lifestream.user.biz.domain.mapper.RoleDOMapper;
import com.sunrisejay.lifestream.user.biz.domain.mapper.UserDOMapper;
import com.sunrisejay.lifestream.user.biz.domain.mapper.UserRoleDOMapper;
import com.sunrisejay.lifestream.user.biz.enums.ResponseCodeEnum;
import com.sunrisejay.lifestream.user.biz.enums.SexEnum;
import com.sunrisejay.lifestream.user.biz.model.vo.UpdateUserInfoReqVO;
import com.sunrisejay.lifestream.user.biz.rpc.OssRpcService;
import com.sunrisejay.lifestream.user.biz.service.UserService;
import com.sunrisejay.lifestream.user.dto.req.FindUserByMailReqDTO;
import com.sunrisejay.lifestream.user.dto.req.RegisterUserReqDTO;
import com.sunrisejay.lifestream.user.dto.resp.FindUserByMailRspDTO;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    @Resource
    private UserRoleDOMapper userRoleDOMapper;
    @Resource
    private RoleDOMapper roleDOMapper;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private UserDOMapper userDOMapper;
    @Resource
    private OssRpcService ossRpcService;
    /**
     * 更新用户信息
     *
     * @param updateUserInfoReqVO
     * @return
     */
    @Override
    public Response<?> updateUserInfo(UpdateUserInfoReqVO updateUserInfoReqVO) {
        UserDO userDO = new UserDO();
        // 设置当前需要更新的用户 ID
        userDO.setId(LoginUserContextHolder.getUserId());
        // 标识位：是否需要更新
        boolean needUpdate = false;

        // 头像
        MultipartFile avatarFile = updateUserInfoReqVO.getAvatar();

        if (Objects.nonNull(avatarFile)) {
            String avatar = ossRpcService.uploadFile(avatarFile);
            log.info("==> 调用 oss 服务成功，上传头像，url：{}", avatar);

            if (StringUtils.isBlank(avatar)) {
                throw new BizException(ResponseCodeEnum.UPLOAD_AVATAR_FAIL);
            }

            userDO.setAvatar(avatar);
            needUpdate = true;
        }

        // 昵称
        String nickname = updateUserInfoReqVO.getNickname();
        if (StringUtils.isNotBlank(nickname)) {
            Preconditions.checkArgument(ParamUtils.checkNickname(nickname), ResponseCodeEnum.NICK_NAME_VALID_FAIL);
            userDO.setNickname(nickname);
            needUpdate = true;
        }

        // 小哈书号
        String lifestreamId = updateUserInfoReqVO.getLifestreamId();
        if (StringUtils.isNotBlank(lifestreamId)) {
            Preconditions.checkArgument(ParamUtils.checklifestreamId(lifestreamId), ResponseCodeEnum.LIFESTREAM_ID_VALID_FAIL);
            userDO.setLifestreamId(lifestreamId);
            needUpdate = true;
        }

        // 性别
        Integer sex = updateUserInfoReqVO.getSex();
        if (Objects.nonNull(sex)) {
            Preconditions.checkArgument(SexEnum.isValid(sex), ResponseCodeEnum.SEX_VALID_FAIL);
            userDO.setSex(sex);
            needUpdate = true;
        }

        // 生日
        LocalDate birthday = updateUserInfoReqVO.getBirthday();
        if (Objects.nonNull(birthday)) {
            userDO.setBirthday(birthday);
            needUpdate = true;
        }

        // 个人简介
        String introduction = updateUserInfoReqVO.getIntroduction();
        if (StringUtils.isNotBlank(introduction)) {
            Preconditions.checkArgument(ParamUtils.checkLength(introduction, 100), ResponseCodeEnum.INTRODUCTION_VALID_FAIL);
            userDO.setIntroduction(introduction);
            needUpdate = true;
        }

        // 背景图
        MultipartFile backgroundImgFile = updateUserInfoReqVO.getBackgroundImg();
        if (Objects.nonNull(backgroundImgFile)) {
            String backgroundImg = ossRpcService.uploadFile(backgroundImgFile);
            log.info("==> 调用 oss 服务成功，上传背景图，url：{}", backgroundImg);

            if (StringUtils.isBlank(backgroundImg)) {
                throw new BizException(ResponseCodeEnum.UPLOAD_BACKGROUND_IMG_FAIL);
            }

            userDO.setBackgroundImg(backgroundImg);
            needUpdate = true;
        }

        if (needUpdate) {
            // 更新用户信息
            userDO.setUpdateTime(LocalDateTime.now());
            userDOMapper.updateByPrimaryKeySelective(userDO);
        }
        return Response.success();
    }

    /**
     * 用户注册
     *
     * @param registerUserReqDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response<Long> register(RegisterUserReqDTO registerUserReqDTO) {
        String mail = registerUserReqDTO.getMail();

        // 先判断该手机号是否已被注册
        UserDO userDO1 = userDOMapper.selectByMail(mail);

        log.info("==> 用户是否注册, phone: {}, userDO: {}", mail, JsonUtils.toJsonString(userDO1));

        // 若已注册，则直接返回用户 ID
        if (Objects.nonNull(userDO1)) {
            return Response.success(userDO1.getId());
        }

        // 否则注册新用户
        // 获取全局自增的小哈书 ID
        Long lifestreamId = redisTemplate.opsForValue().increment(RedisKeyConstants.LIFESTREAM_ID_GENERATOR_KEY);

        UserDO userDO = UserDO.builder()
                .mail(mail)
                .lifestreamId(String.valueOf(lifestreamId)) // 自动生成小红书号 ID
                .nickname("小溪流" + lifestreamId) // 自动生成昵称, 如：小红薯10000
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

        RoleDO roleDO = roleDOMapper.selectByPrimaryKey(RoleConstants.COMMON_USER_ROLE_ID);

        // 将该用户的角色 ID 存入 Redis 中
        List<String> roles = new ArrayList<>(1);
        roles.add(roleDO.getRoleKey());

        String userRolesKey = RedisKeyConstants.buildUserRoleKey(userId);
        redisTemplate.opsForValue().set(userRolesKey, JsonUtils.toJsonString(roles));

        return Response.success(userId);
    }

    @Override
    public Response<FindUserByMailRspDTO> findByMail(FindUserByMailReqDTO findUserByMailReqDTO) {
        String mail = findUserByMailReqDTO.getMail();

        // 根据手机号查询用户信息
        UserDO userDO = userDOMapper.selectByMail(mail);

        // 判空
        if (Objects.isNull(userDO)) {
            throw new BizException(ResponseCodeEnum.USER_NOT_FOUND);
        }

        // 构建返参
        FindUserByMailRspDTO findUserByMailRspDTO = FindUserByMailRspDTO.builder()
                .id(userDO.getId())
                .password(userDO.getPassword())
                .build();

        return Response.success(findUserByMailRspDTO);
    }


}