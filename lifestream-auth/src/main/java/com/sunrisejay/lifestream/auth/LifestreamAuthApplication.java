package com.sunrisejay.lifestream.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
@MapperScan("com.sunrisejay.lifestream.auth.domain.mapper")
@EnableFeignClients(basePackages = "com.sunrisejay.lifestream")
public class LifestreamAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifestreamAuthApplication.class, args);
    }

}
