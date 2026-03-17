package com.sunrisejay.framework.common.response;

import com.sunrisejay.framework.common.exception.BaseExceptionInterface;
import com.sunrisejay.framework.common.exception.BizException;
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
    public static <T> Response<T> success(){
        Response<T> response = new Response<>();
        return response;
    }

    //失败响应
    public static <T> Response<T> fail(){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        return response;
    }

    public static <T> Response<T> fail(String errorMessage){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(errorMessage);
        return response;
    }

    public static <T> Response<T> fail(String errorCode, String errorMessage){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(errorCode);
        response.setMessage(errorMessage);
        return response;
    }
    public static <T> Response<T> fail(BizException bizException){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(bizException.getErrorCode());
        response.setMessage(bizException.getMessage());
        return response;
    }
    public  static <T> Response<T> fail(BaseExceptionInterface baseException){
        Response<T> response = new Response<>();
        response.setSuccess(false);
        response.setErrorCode(baseException.getErrorCode());
        response.setMessage(baseException.getErrorMessage());
        return response;
    }

}
