package com.sunrisejay.lifestream.oss.biz.service.impl;

import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.oss.biz.config.AliyunOSSProperties;
import com.sunrisejay.lifestream.oss.biz.service.FileService;
import com.sunrisejay.lifestream.oss.biz.strategy.FileStrategy;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Resource
    private FileStrategy fileStrategy;

    @Resource
    private AliyunOSSProperties aliyunOSSProperties;

    @Override
    public Response<?> uploadFile(MultipartFile file) {
        // 上传文件，使用配置中的 bucket name
        String url = fileStrategy.uploadFile(file, aliyunOSSProperties.getBucketName());

        return Response.success(url);
    }
}