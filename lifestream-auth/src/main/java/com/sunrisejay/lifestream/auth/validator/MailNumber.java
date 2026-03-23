package com.sunrisejay.lifestream.auth.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 邮箱格式校验注解
 */
@Documented
@Constraint(validatedBy = MailNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface MailNumber {

    String message() default "邮箱格式不正确，请检查并重试";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
