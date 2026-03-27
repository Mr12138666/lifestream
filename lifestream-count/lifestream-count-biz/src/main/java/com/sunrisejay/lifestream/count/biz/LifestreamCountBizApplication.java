package com.sunrisejay.lifestream.count.biz;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sunrisejay.lifestream.count.biz.domain.mapper")
public class LifestreamCountBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifestreamCountBizApplication.class, args);
    }

}
