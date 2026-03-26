package com.sunrisejay.lifestream.user.biz.service.impl;

import com.alibaba.nacos.shaded.com.google.common.base.Preconditions;
import com.sunrisejay.framework.biz.context.holder.LoginUserContextHolder;
import com.sunrisejay.framework.common.exception.BizException;
import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.framework.common.util.ParamUtils;
import com.sunrisejay.lifestream.oss.api.FileFeignApi;
import com.sunrisejay.lifestream.user.biz.domain.dataobject.UserDO;
import com.sunrisejay.lifestream.user.biz.domain.mapper.UserDOMapper;
import com.sunrisejay.lifestream.user.biz.enums.ResponseCodeEnum;
import com.sunrisejay.lifestream.user.biz.enums.SexEnum;
import com.sunrisejay.lifestream.user.biz.model.vo.UpdateUserInfoReqVO;
import com.sunrisejay.lifestream.user.biz.rpc.OssRpcService;
import com.sunrisejay.lifestream.user.biz.service.UserService;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


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

        // 时光溪号
        String lifestreamId
                = updateUserInfoReqVO.getLifestreamId();
        if (StringUtils.isNotBlank(lifestreamId
        )) {
            Preconditions.checkArgument(ParamUtils.checklifestreamId(lifestreamId
            ), ResponseCodeEnum.LIFESTREAM_ID_VALID_FAIL);
            userDO.setLifestreamId(lifestreamId
            );
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
}