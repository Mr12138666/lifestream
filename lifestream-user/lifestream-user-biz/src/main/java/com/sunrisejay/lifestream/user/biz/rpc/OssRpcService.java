package com.sunrisejay.lifestream.user.biz.rpc;

import com.sunrisejay.framework.common.response.Response;
import com.sunrisejay.lifestream.oss.api.FileFeignApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @author: Sunrise_Jay
 * @email: sunrise_jay@yeah.net
 * @date: 2026/3/25 18:28
 */
@Component
public class OssRpcService {

    @Resource
    private FileFeignApi fileFeignApi;

    public String uploadFile(MultipartFile file) {
        Response<?> response = fileFeignApi.uploadFile(file);
        if (!response.isSuccess()) {
            return null;
        }
        return (String) response.getData();
    }


}
