package com.sunrisejay.framework.biz.operationlog.aspect;

import java.lang.annotation.*;

/**
 * @description:
 * @author: Sunrise_Jay
 * @email: sunrise_jay@yeah.net
 * @date: 2026/3/17 20:08
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface ApiOperationLog {

    /**
     * API功能描述
     */
    String description() default "";
}
