package com.sunrisejay.lifestream.comment.biz.config;

import org.apache.rocketmq.spring.autoconfigure.RocketMQAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: Sunrise_Jay
 * @url: www.quanxiaoha.com
 * @date: 2024/8/30 11:16
 * @description: RocketMQ 配置
 **/
@Configuration
@Import(RocketMQAutoConfiguration .class)
public class RocketMQConfig {
}
