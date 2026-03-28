package com.sunrisejay.lifestream.data.align;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sunrisejay.lifestream.data.align.domain.mapper")
public class LifeStreamDataAlignApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifeStreamDataAlignApplication.class, args);
    }

}

