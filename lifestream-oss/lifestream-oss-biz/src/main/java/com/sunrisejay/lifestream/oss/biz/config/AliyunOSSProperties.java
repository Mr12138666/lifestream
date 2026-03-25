package com.sunrisejay.lifestream.oss.biz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "storage.aliyun.oss")
@Component
@Data
public class AliyunOSSProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    /**
     * Bucket名称
     */
    private String bucketName;

    /**
     * 文件访问域名（CDN或OSS域名）
     * 例如：https://your-bucket.oss-cn-hangzhou.aliyuncs.com
     * 或：https://cdn.yourdomain.com
     */
    private String domain;

    /**
     * 文件存储路径前缀
     * 例如：avatar/ 或 images/
     */
    private String pathPrefix = "avatar/";
}