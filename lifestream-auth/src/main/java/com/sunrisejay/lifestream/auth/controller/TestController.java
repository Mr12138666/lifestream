package com.sunrisejay.lifestream.auth.controller;

import com.sunrisejay.framework.biz.operationlog.aspect.ApiOperationLog;
import com.sunrisejay.framework.common.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: Sunrise_Jay
 * @email: sunrise_jay@yeah.net
 * @date: 2026/3/17 19:44
 */
@RestController
public class TestController {

    @GetMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public Response<String>  test(){
        return  Response.success("success");
    }

    @GetMapping("/test2")
    @ApiOperationLog(description = "测试接口2")
    public Response<User>  test2(){
        return  Response.success(User.builder().nickname("haha").createTime(LocalDateTime.now()).build());
    }




}
