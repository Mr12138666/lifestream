package com.sunrisejay.lifestream.oss.biz.factory;
import org.apache.commons.lang3.StringUtils;
import com.sunrisejay.lifestream.oss.biz.strategy.FileStrategy;
import com.sunrisejay.lifestream.oss.biz.strategy.impl.AliyunOSSFileStrategy;
import com.sunrisejay.lifestream.oss.biz.strategy.impl.MinioFileStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class FileStrategyFactory {

    @Value("${storage.type}")
    private String strategyType;

    @Bean
    @RefreshScope
    public FileStrategy getFileStrategy() {
        if (StringUtils.equals(strategyType, "minio")) {
            return new MinioFileStrategy();
        } else if (StringUtils.equals(strategyType, "aliyun")) {
            return new AliyunOSSFileStrategy();
        }

        throw new IllegalArgumentException("不可用的存储类型");
    }

}