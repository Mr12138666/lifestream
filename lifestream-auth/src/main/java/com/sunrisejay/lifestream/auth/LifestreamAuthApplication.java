package com.sunrisejay.lifestream.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.sunrisejay.lifestream.auth.domain.mapper")
@SpringBootApplication
public class LifestreamAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifestreamAuthApplication.class, args);
    }

}
