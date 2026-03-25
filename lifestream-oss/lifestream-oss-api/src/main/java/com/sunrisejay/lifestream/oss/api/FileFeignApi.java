package com.sunrisejay.lifestream.oss.api;

import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.oss.constant.ApiConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = ApiConstants.SERVICE_NAME)
public interface FileFeignApi {

    String PREFIX = "/file";

    @PostMapping(value = PREFIX + "/test")
    Response<?> test();

}