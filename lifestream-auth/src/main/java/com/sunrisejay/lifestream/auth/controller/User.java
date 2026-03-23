package com.sunrisejay.lifestream.auth.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: Sunrise_Jay
 * @email: sunrise_jay@yeah.net
 * @date: 2026/3/17 21:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @NotBlank
    private String nickname;
    private LocalDateTime createTime;
}
