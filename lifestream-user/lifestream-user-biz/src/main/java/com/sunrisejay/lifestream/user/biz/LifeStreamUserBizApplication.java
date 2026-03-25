package com.sunrisejay.lifestream.user.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.sunrisejay.lifestream.user.biz.domain.mapper")
@EnableFeignClients(basePackages = "com.sunrisejay.lifestream")
public class LifeStreamUserBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifeStreamUserBizApplication.class, args);
    }

}