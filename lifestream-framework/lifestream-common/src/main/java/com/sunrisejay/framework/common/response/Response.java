package com.sunrisejay.framework.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: SunriseJay
 * @email: sunrise_jay@yeah.net
 * @date: 2026/3/17 16:43
 */
@Data
public class Response<T> implements Serializable {

    //是否成功，默认为true
    private boolean success = true;
    private String errorCode;
    private String message;
    private T data;

    //响应成功
    public static <T> Response<T> success(T data){
        Response<T> response = new Response<>();
        response.setData(data);
        return response;
    }




}
