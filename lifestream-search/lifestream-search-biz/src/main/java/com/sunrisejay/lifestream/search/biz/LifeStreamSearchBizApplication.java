package com.sunrisejay.lifestream.search.biz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.sunrisejay.lifestream.search.biz.domain.mapper")
public class LifeStreamSearchBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifeStreamSearchBizApplication.class, args);
    }

}
