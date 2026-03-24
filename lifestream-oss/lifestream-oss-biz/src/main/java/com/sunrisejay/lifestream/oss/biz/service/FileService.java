package com.sunrisejay.lifestream.oss.biz.service;

import com.sunrisejay.framework.common.response.Response;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * 上传文件
     * 
     * @param file
     * @return
     */
    Response<?> uploadFile(MultipartFile file);
}