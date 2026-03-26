package com.sunrisejay.lifestream.auth;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication

@EnableFeignClients(basePackages = "com.sunrisejay.lifestream")
public class LifestreamAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifestreamAuthApplication.class, args);
    }

}
