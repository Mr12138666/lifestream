package com.sunrisejay.lifestream.oss.api;

import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.oss.config.FeignFormConfig;
import com.sunrisejay.lifestream.oss.constant.ApiConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = ApiConstants.SERVICE_NAME,configuration = FeignFormConfig.class)
public interface FileFeignApi {

    String PREFIX = "/file";

    @PostMapping(value = PREFIX + "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response<?> uploadFile(@RequestPart(value = "file") MultipartFile file);

}