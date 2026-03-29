package com.sunrisejay.lifestream.search.controller;

import com.sunrisejay.framework.biz.operationlog.aspect.ApiOperationLog;
import com.sunrisejay.framework.common.response.PageResponse;
import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.search.model.vo.SearchUserReqVO;
import com.sunrisejay.lifestream.search.model.vo.SearchUserRspVO;
import com.sunrisejay.lifestream.search.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author: 犬小哈
 * @date: 2024/4/4 13:22
 * @version: v1.0.0
 * @description: 用户搜索
 **/
@RestController
@RequestMapping("/search")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/user")
    @ApiOperationLog(description = "搜索用户")
    public PageResponse<SearchUserRspVO> searchUser(@RequestBody @Validated SearchUserReqVO searchUserReqVO) {
        return userService.searchUser(searchUserReqVO);
    }


}
