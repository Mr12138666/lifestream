package com.sunrisejay.lifestream.oss.biz.controller;

import com.sunrisejay.framework.biz.context.holder.LoginUserContextHolder;
import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.oss.biz.service.FileService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<?> uploadFile(@RequestPart(value = "file") MultipartFile file) {
        log.info("调用上传文件服务的当前用户id:{}", LoginUserContextHolder.getUserId());
        return fileService.uploadFile(file);
    }

}