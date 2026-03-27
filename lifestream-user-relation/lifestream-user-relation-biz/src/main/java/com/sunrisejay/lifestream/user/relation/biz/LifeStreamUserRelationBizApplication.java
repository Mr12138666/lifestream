package com.sunrisejay.lifestream.user.relation.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.sunrisejay.lifestream.user.relation.biz.domain.mapper")
@EnableFeignClients(basePackages = "com.sunrisejay.lifestream")
public class LifeStreamUserRelationBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifeStreamUserRelationBizApplication.class, args);
    }

}