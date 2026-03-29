package com.sunrisejay.lifestream.data.align;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.sunrisejay.lifestream")
@MapperScan("com.sunrisejay.lifestream.data.align.domain.mapper")
public class LifeStreamDataAlignApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifeStreamDataAlignApplication.class, args);
    }

}
