package com.sunrisejay.lifestream.oss.biz.strategy;

import org.springframework.web.multipart.MultipartFile;

public interface FileStrategy {

    /**
     * 文件上传
     *
     * @param file
     * @param bucketName
     * @return
     */
    String uploadFile(MultipartFile file, String bucketName);

    /**
     * 获取存储桶名称
     *
     * @return bucket name
     */
    String getBucketName();

}
