package com.sunrisejay.lifestream.auth.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

/**
 * 邮箱格式校验器
 */
public class MailNumberValidator implements ConstraintValidator<MailNumber, String> {

    /**
     * 通用邮箱正则表达式
     * 支持 qq.com、163.com、gmail.com 等各种邮箱格式
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    @Override
    public void initialize(MailNumber constraintAnnotation) {
        // 初始化逻辑，可留空
    }

    @Override
    public boolean isValid(String mailNumber, ConstraintValidatorContext context) {
        // null 值不做校验，由 @NotBlank 处理
        if (mailNumber == null || mailNumber.isBlank()) {
            return true;
        }
        return EMAIL_PATTERN.matcher(mailNumber).matches();
    }
}
