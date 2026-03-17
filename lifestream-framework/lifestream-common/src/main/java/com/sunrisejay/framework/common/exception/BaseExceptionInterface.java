package com.sunrisejay.framework.common.exception;

/**
 * @description:
 * @author: SunriseJay
 * @email: sunrise_jay@yeah.net
 * @date: 2026/3/17 16:42
 */
public interface BaseExceptionInterface {

    //获取异常码
    String getErrorCode();

    //获取异常信息
    String getErrorMessage();
}
