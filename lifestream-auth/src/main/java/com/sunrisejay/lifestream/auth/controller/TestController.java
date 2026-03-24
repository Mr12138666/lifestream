package com.sunrisejay.lifestream.auth.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.sunrisejay.lifestream.auth.alarm.AlarmInterface;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Sunrise_Jay
 * @email: sunrise_jay@yeah.net
 * @date: 2026/3/24 11:11
 */
@RestController
@Slf4j
public class TestController {
    @Resource
    private AlarmInterface alarm;
    @NacosValue(value = "${rate-limit.api.limit}", autoRefreshed = true)
    private Integer limit;

    @GetMapping("/test")
    public String test(){
        return "当前限流阈值："+limit;
    }




    @GetMapping("/alarm")
    public String sendAlarm() {
        alarm.send("系统出错啦，速度上线解决问题！");
        return "alarm success";
    }


}
