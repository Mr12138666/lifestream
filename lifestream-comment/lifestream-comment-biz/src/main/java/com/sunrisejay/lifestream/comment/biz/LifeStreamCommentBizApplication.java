package com.sunrisejay.lifestream.comment.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@MapperScan("com.sunrisejay.lifestream.comment.biz.domain.mapper")
@EnableRetry
@EnableFeignClients(basePackages = "com.sunrisejay.lifestream")
public class LifeStreamCommentBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifeStreamCommentBizApplication.class, args);
    }

}
