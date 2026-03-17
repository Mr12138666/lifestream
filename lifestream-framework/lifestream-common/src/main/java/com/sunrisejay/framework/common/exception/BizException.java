package com.sunrisejay.framework.common.exception;

/**
 * @description:
 * @author: SunriseJay
 * @email: sunrise_jay@yeah.net
 * @date: 2026/3/17 16:42
 */
public class BizException extends RuntimeException{
    //异常码
    private String errorCode;
    private String errorMessage;

    public BizException(BaseExceptionInterface baseExceptionInterface){
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }
}
